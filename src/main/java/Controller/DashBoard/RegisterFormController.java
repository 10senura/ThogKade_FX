package Controller.DashBoard;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterFormController {

    @FXML
    private PasswordField txtConformePassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtname;

    @FXML
    void btnRegisterFormAction(ActionEvent event) throws SQLException {

        if(txtemail.getText().equals(txtConformePassword.getText())){
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email="+"'"+txtemail.getText()+ "'");
            if (!resultSet.next()) {
                String sql = "INSERT INTO users(username,email,password) VALUES (?,?,?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, txtname.getText());
                pstm.setObject(2, txtemail.getText());
                pstm.setObject(3, txtPassword.getText());
                pstm.executeUpdate();
            }else{
                System.out.println(false);
            }
         }else{
            System.out.println(false);
        }
    }
}
