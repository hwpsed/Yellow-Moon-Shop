/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import truongtq.connection.MyConnection;
import truongtq.dtos.LogDTO;

/**
 *
 * @author harry
 */
public class LogDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    public boolean addLog(String username, int proId, String date) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        try {
            String sql = "Insert INTO Logs(UserId, ProductId, Date) values (?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setInt(2, proId);
            preStm.setString(3, date);

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
