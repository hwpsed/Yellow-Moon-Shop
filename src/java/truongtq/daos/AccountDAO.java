/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.daos;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import truongtq.connection.MyConnection;
import truongtq.dtos.AccountDTO;

/**
 *
 * @author harry
 */
public class AccountDAO {
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
    
    private enum Role {
        ADMIN, USER, GUEST
    }

    public AccountDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        AccountDTO dto = null;
        try {
            String sql = "Select Name, (SELECT RoleName from Roles WHERE Roles.Id = Users.RoleId) as Role , Phone, Address FROM Users Where Username = ? and Password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String role = rs.getString("Role");
                String name = rs.getString("Name");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                dto = new AccountDTO(username, name, phone ,address, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public AccountDTO getUserById(String username) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        AccountDTO dto = null;
        try {
            String sql = "Select Name, (SELECT RoleName from Roles WHERE Roles.Id = Users.RoleId) as Role , Phone, Address FROM Users Where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);

            rs = preStm.executeQuery();
            if (rs.next()) {
                String role = rs.getString("Role");
                String name = rs.getString("Name");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                dto = new AccountDTO(username, name, phone ,address, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
