package db;

import Model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    private static DBConnection instance;

    private Connection CustumerList;

    private DBConnection() throws SQLException {

        this.CustumerList= DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234" );
    }

   public Connection getConnection(){
        return CustumerList;
   }

    public static DBConnection getInstance() throws SQLException {
        if (instance==null){
            return instance= new DBConnection();
        }
        return instance;
    }
}
