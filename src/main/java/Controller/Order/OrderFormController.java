package Controller.Order;

import service.custom.CustomerService;
import Model.Customer;
import Model.Item;
import Model.Order;
import Model.OrderDetail;
import com.jfoenix.controls.JFXComboBox;
import db.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ItemServiceImpl;
import service.custom.impl.OrderController;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    public TableColumn clmPrice;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtStock;
    public JFXComboBox <String>cmbCustId;
    public TextField txtHandOnStock;
    public Label lblTime;
    public Label lblDate;
    public JFXComboBox <String> cmbItemCode;
    public TextField txtQty;
    public TableColumn clmItemCode;
    public TableView  tblOrders;
    public Label lblnettotal;
    public TableColumn clmtotal;
    public TextField txtOrderId;

    @FXML
    private TableColumn<?, ?> clmDescription;

    @FXML
    private TableColumn<?, ?> clmQty;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    CustomerService Service= CustomerServiceImpl.getInstance();


    @FXML
    public void btnPlaceOrderAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        LocalDate orderDate = LocalDate.now();
        String customerId = cmbCustId.getValue();

        List<OrderDetail> orderDetails = new ArrayList<>();

        cartTms.forEach(obj->{
            orderDetails.add(new OrderDetail(orderId,obj.getItemCode(),obj.getQty(),0.0));
        });
        Order order = new Order(orderId, orderDate, customerId, orderDetails);
        try {
            new OrderController().placeOrder(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void LodeTimeAndDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String DateFormet= simpleDateFormat.format(date);
        lblDate.setText(DateFormet);

        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO,e->{
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());
        }),
          new KeyFrame(Duration.seconds(1))
          );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LodeTimeAndDate();
        loadCustomerIds();
        loadItemCodes();

        cmbCustId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal!=null){
                searchCustomer(newVal);
            }
        });
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal!=null){
                SearchItem(newVal);
            }
        });
    }


    public void loadCustomerIds(){
        cmbCustId.setItems(CustomerServiceImpl.getInstance().getCustomerId());
    }

    public void loadItemCodes(){
        cmbItemCode.setItems(CustomerServiceImpl.getInstance().getCustomerId());
    }

    private void searchCustomer(String customerId){
        Customer customer = CustomerServiceImpl.getInstance().SearchCustomer(customerId);
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
    }

    private void SearchItem(String itemcode){
        Item item = ItemServiceImpl.getInstance().SearchItem(itemcode);
        txtDescription.setText(item.getDescription());
        txtHandOnStock.setText(String.valueOf(item.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(item.getPrice()));
    }

    ObservableList<dto.CartTM> cartTms = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

        clmItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        clmtotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        String itemcode = cmbItemCode.getValue();
        String description =txtDescription.getText();
        Integer qty = Integer.parseInt(txtQty.getText());
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        Double total = unitPrice*qty;

        if (Integer.parseInt(txtHandOnStock.getText())<qty){
            new Alert(Alert.AlertType.WARNING,"OutOfStock").show();
        }else{
            cartTms.add(new dto.CartTM(itemcode,description,qty,unitPrice,total));
            calcNetTotal();
        }

        tblOrders.setItems(cartTms);

    }

    private void calcNetTotal(){
        Double total=0.0;
        for (dto.CartTM cartTM: cartTms){
            total+=cartTM.getTotal();
        }
        lblnettotal.setText(total.toString()+"/=");
    }

    public void btnCommitOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
