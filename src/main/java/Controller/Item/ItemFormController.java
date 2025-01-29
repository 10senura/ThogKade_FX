package Controller.Item;

import Controller.util.ServiceType;
import Model.Item;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.ToString;
import service.ServiceFactory;
import service.custom.ItemService;

import java.net.URL;
import java.util.ResourceBundle;
@ToString

public class ItemFormController implements Initializable {

    @FXML
    private TableColumn  clmCode;

    @FXML
    private TableColumn clmDescription;

    @FXML
    private TableColumn clmQtyOnHand;

    @FXML
    private TableColumn clmUnitPrice;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    ItemService Service = ServiceFactory.getInsterns().getServiceType(ServiceType.ITEM);

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        Item item = new Item(
                txtCode.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQty.getText())

        );

        if(Service.AddItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Added Successfully!");
            lodetabel();
        }else{
            new Alert(Alert.AlertType.ERROR,"Added Not Successfully ?");
        }
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        if(Service.DeleteItem(txtCode.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Item Deleted !").show();
            lodetabel();
        }else{
            new Alert(Alert.AlertType.ERROR,"Item Not Deleted !").show();
        }
    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {
        Item item=Service.SearchItem(txtCode.getText());
        setTextToValues(item);
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        Item item = new Item(
                txtCode.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );
        if (Service.UpdateItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Update Successfully !");
        }else{
            new Alert(Alert.AlertType.ERROR,"Update NOT Successfully !");

        }
        lodetabel();
    }

@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblItem.getSelectionModel().selectedItemProperty().addListener((((observableValue, oldValue, newValue) ->{
           if (newValue!=null){
               setTextToValues(newValue);
           }
        })));

        clmCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        lodetabel();
    }

    private void setTextToValues(Item newValue) {
        txtCode.setText(newValue.getCode());
        txtDescription.setText(newValue.getDescription());
        txtPrice.setText(String.valueOf(newValue.getPrice()));
        txtQty.setText(String.valueOf(newValue.getQtyOnHand()));
    }

    public void lodetabel(){
        ObservableList<Item> ItemObservableList =Service.getAll();
        tblItem.setItems(ItemObservableList);
    }

}
