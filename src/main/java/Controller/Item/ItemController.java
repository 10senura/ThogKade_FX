package Controller.Item;

import Model.Item;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemService{

    @Override
    public boolean AddItem(Item item) {
        try {
            String sql="INSERT INTO item values (?,?,?,?)";
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement pstm= connection.prepareStatement(sql);
            pstm.setObject(1,item.getCode());
            pstm.setObject(2,item.getDescription());
            pstm.setObject(3,item.getPrice());
            pstm.setObject(4,item.getQtyOnHand());
            return pstm.executeUpdate()>0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"ERROR"+e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean UpdateItem(Item item) {
        String sql="UPDATE item SET description=?,unitPrice=?,qtyOnHand=? WHERE code=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            psTm.setString(1,item.getDescription());
            psTm.setDouble(2,item.getPrice());
            psTm.setInt(3,item.getQtyOnHand());
            psTm.setString(4,item.getCode());

            return psTm.executeUpdate()> 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean DeleteItem(String code) {
        String sql="DELETE FROM item WHERE code='"+code+"'";
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate(sql)>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item SearchItem(String code) {
        String sql="SELECT * FROM item WHERE code='"+code+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                return  new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt( 4)
                );
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"ERROR"+e.getMessage()).show();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ObservableList<Item> getAll() {
        ObservableList<Item> CustomerObservableList = FXCollections.observableArrayList();
        try {
            String sql="select * from item";

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("code"));
                System.out.println(resultSet.getString("description"));
                System.out.println(resultSet.getString("qtyOnHand"));
                System.out.println(resultSet.getString("qtyOnHand"));

                Item item = new Item(
                        resultSet.getString("Code"),
                        resultSet.getString("Description"),
                        resultSet.getDouble("unitPrice"),
                        resultSet.getInt("qtyOnHand")
                );
                System.out.println(item);
                CustomerObservableList.add(item);
            }
            return CustomerObservableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
