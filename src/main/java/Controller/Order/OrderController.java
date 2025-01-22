package Controller.Order;

import Controller.Item.ItemController;
import Model.Order;
import db.DBConnection;
import javafx.scene.control.Alert;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {
    public boolean placeOrder(Order order) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

            String SQL = "INSERT INTO orders VALUES(?,?,?)";

            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, order.getId());
            psTm.setObject(2, order.getDate());
            psTm.setObject(3, order.getCustomerId());
            return psTm.executeUpdate() > 0;
        }
}