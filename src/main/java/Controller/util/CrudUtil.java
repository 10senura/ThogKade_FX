package Controller.util;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CrudUtil {
    public static <T> T execute(String SQL, Object... val) throws SQLException {

        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        for (int i = 0; i < val.length; i++) {
            psTm.setObject(i + 1, val[i]);
        }
        if (SQL.startsWith("SELECT") || SQL.startsWith("select")) {
            return (T) psTm.executeQuery();
        } else {

            return (T) (Boolean) (psTm.executeUpdate() > 0);
        }
    }
}