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
import truongtq.dtos.OrderDTO;

/**
 *
 * @author harry
 */
public class OrderDAO {

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

    private enum Status {
        PAID, UNPAID;
    }

    public boolean createOrderGuest(OrderDTO dto) throws SQLException, NamingException {
        boolean check = false;
        try {
            String sql = "Insert INTO Orders(Date, Name, Address, Phone, Total, PaymentId, StatusId) values (?, ?, ?, ?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getDate());
            preStm.setString(2, dto.getName());
            preStm.setString(3, dto.getAddress());
            preStm.setString(4, dto.getPhone());
            preStm.setFloat(5, dto.getTotal());
            preStm.setInt(6, dto.getPaymentId());
            preStm.setInt(7, Status.UNPAID.ordinal() + 3);

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

//    public boolean createOrderUser(OrderDTO dto) throws SQLException, NamingException, ClassNotFoundException {
//        boolean check = false;
//        try {
//            String sql = "Insert INTO Orders(Date, Name, Address, Phone, Total, UserId, PaymentId, StatusId) values (?, ?, ?, ?, ?, ?, ?, ?)";
//            conn = MyConnection.getMyConnection();
//            preStm = conn.prepareStatement(sql);
//            preStm.setString(1, dto.getDate());
//            preStm.setString(2, dto.getName());
//            preStm.setString(3, dto.getAddress());
//            preStm.setString(4, dto.getPhone());
//            preStm.setFloat(5, dto.getTotal());
//            preStm.setString(6, dto.getUserId());
//            preStm.setInt(7, dto.getPaymentId());
//            preStm.setInt(8, Status.UNPAID.ordinal() + 3);
//            
//            check = preStm.executeUpdate() > 0;
//        } finally {
//            closeConnection();
//        }
//        return check;
//    }
    
    public boolean createOrderUser(OrderDTO dto) throws SQLException, NamingException {
        boolean check = false;
        try {
            String sql = "Insert INTO Orders(Date, Name, Address, Phone, Total, PaymentId, StatusId, UserId) values (?, ?, ?, ?, ?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getDate());
            preStm.setString(2, dto.getName());
            preStm.setString(3, dto.getAddress());
            preStm.setString(4, dto.getPhone());
            preStm.setFloat(5, dto.getTotal());
            preStm.setInt(6, dto.getPaymentId());
            preStm.setInt(7, Status.UNPAID.ordinal() + 3);
            preStm.setString(8, dto.getUserId());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }


}
