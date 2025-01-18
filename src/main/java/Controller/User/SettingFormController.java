package Controller.User;

import Controller.Customer.CustomerController;
import Controller.Customer.CustomerService;
import Model.Customer;
import Model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> clmEmail;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableView <User> tblUsers;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    UserService Service= UserController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblUsers.getSelectionModel().selectedItemProperty().addListener((((observableValue, o, newValue) ->{
            setTextToValues(newValue);
        })));

        clmName.setCellValueFactory(new PropertyValueFactory<>("username"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        lodetabel();
    }
    private void setTextToValues(User newValue) {

        txtId.setText(newValue.getId());
        txtName.setText(newValue.getUsername());
        txtEmail.setText(newValue.getEmail());
        txtPassword.setText(newValue.getPassword());
    }
    public void lodetabel(){
        ObservableList<User> UserObserverList =Service.getAll();
        tblUsers.setItems(UserObserverList);
    }

    @FXML
    void btnAddUserOnAction(ActionEvent event) {
        User user = new User(
                txtId.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        );
        if(Service.AddUser(user)){
            new Alert(Alert.AlertType.INFORMATION,"Added Successfully!");
            lodetabel();
        }else{
            new Alert(Alert.AlertType.ERROR,"Added Not Successfully ?");
        }
    }

    @FXML
    void btnDeleteUserOnAction(ActionEvent event) {
        if(Service.DeleteUser(txtId.getText())){
            new Alert(Alert.AlertType.INFORMATION,"User Deleted !").show();
            lodetabel();
        }else{
            new Alert(Alert.AlertType.ERROR,"User Not Deleted !").show();
        }
    }

    @FXML
    void btnSearchUserOnAction(ActionEvent event) {
        User user=Service.SearchUser(txtId.getText());
        setTextToValues(user);
    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) {
        User user = new User(
                txtId.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        );
        if (Service.UpdateUser(user)){
            new Alert(Alert.AlertType.INFORMATION,"Update Successfully !");
        }else{
            new Alert(Alert.AlertType.ERROR,"Update NOT Successfully !");

        }
        lodetabel();
    }
}
