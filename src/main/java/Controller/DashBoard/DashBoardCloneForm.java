package Controller.DashBoard;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardCloneForm implements Initializable {
    public Label lblCustomer;
    public Label lblOrders;
    public Label lblItem;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblItem.setText("Customer");
    }
}
