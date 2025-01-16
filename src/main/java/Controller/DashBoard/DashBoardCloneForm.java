package Controller.DashBoard;

import db.DBConnection;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DashBoardCloneForm implements Initializable {
    public Label lblCustomer; // Label to show the customer count
    public Label lblItem;
    public Label lblOrders;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch data and update labels
        try {
            // Get database connection
            Connection connection = DBConnection.getInstance().getConnection();

            // Get the number of customers
            int customerCount = getCount(connection, "SELECT COUNT(*) FROM customer");
            lblCustomer.setText("    " + customerCount);

            // Get the number of items
            int itemCount = getCount(connection, "SELECT COUNT(*) FROM item");
            lblItem.setText("    " + itemCount);

            int orderCount= getCount(connection,"SELECT COUNT(*) FROM orders");
            lblOrders.setText("    "+ orderCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to execute a query and return the count
    private int getCount(Connection connection, String query) {
        int count = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
