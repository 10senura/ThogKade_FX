package Controller.Order;

import Controller.Customer.CustomerController;
import Controller.Customer.CustomerService;
import Controller.Item.ItemController;
import Model.Customer;
import Model.Item;
import com.jfoenix.controls.JFXComboBox;
import db.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    public TableColumn clmAddress;
    public TableColumn clmStock;
    public TableColumn clmPrice;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtStock;
    public JFXComboBox <String>cmbCustId;
    public TextField txtHandOnStock;
    public Label lblTime;
    public Label lblDate;
    public JFXComboBox <String> cmbItemCode;
    @FXML
    private TableColumn<?, ?> clmCustname;

    @FXML
    private TableColumn<?, ?> clmDescription;

    @FXML
    private TableColumn<?, ?> clmHandOnStock;

    @FXML
    private TableColumn<?, ?> clmOrderName;

    @FXML
    private TableColumn<?, ?> clmQty;

    @FXML
    private TableColumn<?, ?> clmclmStockSalary2;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {

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

        cmbCustId.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) ->{
            if (newValue!=null){
                searchCustomer(newValue);
            }
        } );

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) ->{
            if (t1!=null){
                SearchItem(t1);
            }
        });

    }

    public void loadCustomerIds(){
        cmbCustId.setItems(CustomerController.getInstance().getCustomerId());
    }

    public void loadItemCodes(){
        cmbItemCode.setItems(ItemController.getInstance().getItemId());
    }

    private void searchCustomer(String customerId){
        Customer customer = CustomerController.getInstance().SearchCustomer(customerId);
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
    }

    private void SearchItem(String itemcode){
        Item item = ItemController.getInstance().SearchItem(itemcode);
        txtDescription.setText(item.getDescription());
        txtHandOnStock.setText(String.valueOf(item.getQtyOnHand()));
        txtStock.setText(String.valueOf(item.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(item.getPrice()));

    }

}