package Controller.Customer;

import Model.Customer;
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

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCustomerFormController implements Initializable {

    public TableView <Customer> tblCustomer;
    public TextField txtName;
    public TextField txtId;
    public TextField txtAddress;
    public TextField txtSalary;

    @FXML
    private TableColumn clmAddress;

    @FXML
    private TableColumn clmId;

    @FXML
    private TableColumn clmSalary;

    @FXML
    private TableColumn clmName;

    CustomerService Service= CustomerController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((((observableValue, o, newValue) ->{
            setTextToValues(newValue);
        })));

        clmId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        clmSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));

        lodetabel();
    }

    private void setTextToValues(Customer newValue) {

        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
    }

    public void lodetabel(){
        ObservableList<Customer> CustomerObservableList =Service.getAll();
        tblCustomer.setItems(CustomerObservableList);
    }


    public void btnAddCustomerOnAction(ActionEvent actionEvent) {

        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        if(Service.AddCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"Added Successfully!");
            lodetabel();
        }else{
            new Alert(Alert.AlertType.ERROR,"Added Not Successfully ?");
        }
    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent) {
            if(Service.DeleteCustomer(txtId.getText())){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted !").show();
                lodetabel();
            }else{
                new Alert(Alert.AlertType.ERROR,"Customer Not Deleted !").show();
        }
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        Customer customer=Service.SearchCustomer(txtId.getText());
        setTextToValues(customer);
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent){
        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );
            if (Service.UpdateCustomer(customer)){
                new Alert(Alert.AlertType.INFORMATION,"Update Successfully !");
            }else{
                new Alert(Alert.AlertType.ERROR,"Update NOT Successfully !");

            }
            lodetabel();
    }
}
