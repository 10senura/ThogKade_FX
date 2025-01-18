package Controller.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SettingFormController {

    @FXML
    private TableColumn<?, ?> clmEmail;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableView<?> tblUsers;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnAddUserOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteUserOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchUserOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) {

    }

}
