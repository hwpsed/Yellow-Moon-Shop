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
import truongtq.dtos.OrderDetailDTO;

/**
 *
 * @author harry
 */
public class OrderDetailDAO {
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
    
    public boolean ceateOrderDetail(OrderDetailDTO dto) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        try {
            String sql = "Insert INTO tblOrderDetails(OrderId, ProductId, Price, Quantity) values (?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getOrderId());
            preStm.setInt(2, dto.getProductId());
            preStm.setFloat(3, dto.getPrice());
            preStm.setInt(4, dto.getQuantity());
            
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public int findOrderId(String name, float total, String address, String phone) throws SQLException, NamingException, ClassNotFoundException {
        int oderId = 0;
        try {
                String sql = "Select Id From Orders Where Name = ? AND Total = ? AND Address = ? AND Phone = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, name);
            preStm.setFloat(2, total);
            preStm.setString(3, address);
            preStm.setString(4, phone);
            rs = preStm.executeQuery();
            while (rs.next()) {
                oderId = rs.getInt("Id");
            }
        } finally {
            closeConnection();
        }
        return oderId;
    }
}
