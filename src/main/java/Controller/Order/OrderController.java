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
        connection.setAutoCommit(false);
        try {
            String SQL = "INSERT INTO orders VALUE(?,?,?)";
            connection.setAutoCommit(false);
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, order.getId());
            psTm.setObject(2, order.getDate());
            psTm.setObject(3, order.getCustomerId());
            boolean isOrderAdd = psTm.executeUpdate() > 0;
            if (isOrderAdd) {
                boolean isOrderDetailAdd = new OrderDetailController().addOrderDetail(order.getOrderDetails());
                if (isOrderDetailAdd) {
                    boolean isUpdateStock = ItemController.getInstance().updateStock(order.getOrderDetails());
                    if (isUpdateStock) {
                        connection.commit();
                        new Alert(Alert.AlertType.INFORMATION, "Order Placed !!").show();
                    }
                }
            }
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}