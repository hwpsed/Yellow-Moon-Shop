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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import truongtq.connection.MyConnection;
import truongtq.dtos.ProductDTO;

/**
 *
 * @author harry
 */
public class ProductDAO {
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
        ACTIVE, DEACTIVE
    }
    
    private enum Category {
        BANH_NUONG, BANH_DEO, BANH_CHAY
    }
    
    
    public int findTotalPage(String name) throws SQLException, NamingException, ClassNotFoundException {
        int total = 0;
        try {
            String sql = "SELECT COUNT(Id) as Total from Products where ExpDate >= GETDATE() AND (Name LIKE ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + name + "%");
           
            rs = preStm.executeQuery();
            if (rs.next()) 
                total = rs.getInt("Total");
        } finally {
            closeConnection();
        }
        return total;
    }
    public int findTotalPagePrice(String price1, String price2) throws SQLException, NamingException, ClassNotFoundException {
        int total = 0;
        try {
            String sql = "SELECT COUNT(Id) as Total from Products where ExpDate >= GETDATE() AND (Price BETWEEN ? AND ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            float priceLow = Float.parseFloat(price1);
            float priceHigh = Float.parseFloat(price2);

            if (priceLow > priceHigh) {
                preStm.setFloat(1, priceHigh);
                preStm.setFloat(2, priceLow);
            } else {
                preStm.setFloat(1, priceLow);
                preStm.setFloat(2, priceHigh);
            }
            rs = preStm.executeQuery();
            if (rs.next()) 
                total = rs.getInt("Total");
        } finally {
            closeConnection();
        }
        return total;
    }
    
    public List<ProductDTO> findProductByName(String search, int page) throws SQLException, NamingException, ClassNotFoundException {
        List<ProductDTO> result = null;
        String name = null;
        String image = null;
        float price = 0;
        String category = null;
        int quantity = 0;
        String status = null;
        String creDate = null;
        String expDate = null;
        ProductDTO dto = null;
        int id = 0;
        try {
//            String sql = "Select Products.Id, Products.Name, Products.Image, Price, Quantity, CreDate, ExpDate,"
//                    + " (Select Categories.Name FROM Categories WHERE Categories.Id = Products.CategoryId) as Category,"
//                    + " (Select StatusName FROM Status WHERE Status.Id = Products.StatusId) as Status"
//                    + " FROM Products Where Products.StatusId = ? AND (Name LIKE ? OR Category LIKE ?)";
            String sql = "SELECT * FROM (" +
                         " SELECT ROW_NUMBER() OVER (ORDER BY [Id]) as rownumber, Products.Id as Ids, Products.Name as Names, Products.Image as Images, Price, Quantity, CreDate, ExpDate," +
                    "						 (Select Categories.Name FROM Categories WHERE Categories.Id = Products.CategoryId) as Category," +
                    "						 (Select StatusName FROM Status WHERE Status.Id = Products.StatusId) as Status" +
                    " FROM Products WHERE Products.StatusId = ? AND (Name LIKE ? OR (Select Categories.Name FROM Categories WHERE Categories.Id = Products.CategoryId) LIKE ?)) as ProductsPaging Where rownumber > ? AND rownumber <= ? AND (ExpDate >= GETDATE())";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, Status.ACTIVE.ordinal() + 1);
            preStm.setInt(4, page * 20 - 20);
            preStm.setInt(5, page * 20);
            preStm.setString(2, "%" + search + "%");
            preStm.setString(3, "%" + search + "%");
            rs = preStm.executeQuery();

            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("Ids");
                name = rs.getString("Names");
                image = rs.getString("Images");
                price = rs.getFloat("Price");
                creDate = rs.getString("CreDate");
                expDate = rs.getString("ExpDate");
                status = rs.getString("Status");
                quantity = rs.getInt("Quantity");
                category = rs.getString("Category");
                dto = new ProductDTO(id, quantity, name, creDate, expDate, category, status, price, image);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<ProductDTO> findProductByPrice(String price1, String price2, int page) throws SQLException, NamingException, ClassNotFoundException {
        List<ProductDTO> result = null;
        String name = null;
        String image = null;
        float price = 0;
        String category = null;
        int quantity = 0;
        String status = null;
        String creDate = null;
        String expDate = null;
        ProductDTO dto = null;
        int id = 0;
        try {
            String sql = "SELECT * FROM (" +
                         " SELECT ROW_NUMBER() OVER (ORDER BY [Id]) as rownumber, Products.Id as Ids, Products.Name as Names, Products.Image as Images, Price, Quantity, CreDate, ExpDate," +
                    "						 (Select Categories.Name FROM Categories WHERE Categories.Id = Products.CategoryId) as Category," +
                    "						 (Select StatusName FROM Status WHERE Status.Id = Products.StatusId) as Status" +
                    " FROM Products WHERE Products.StatusId = ? AND (Price BETWEEN ? AND ?)) as ProductsPaging Where rownumber > ? AND rownumber <= ?  AND (ExpDate >= GETDATE())";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, Status.ACTIVE.ordinal() + 1);
            preStm.setInt(4, page * 20 - 20);
            preStm.setInt(5, page * 20);
            float priceLow = Float.parseFloat(price1);
            float priceHigh = Float.parseFloat(price2);

            if (priceLow > priceHigh) {
                preStm.setFloat(2, priceHigh);
                preStm.setFloat(3, priceLow);
            } else {
                preStm.setFloat(2, priceLow);
                preStm.setFloat(3, priceHigh);
            }
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("Ids");
                name = rs.getString("Names");
                image = rs.getString("Images");
                price = rs.getFloat("Price");
                creDate = rs.getString("CreDate");
                expDate = rs.getString("ExpDate");
                status = rs.getString("Status");
                quantity = rs.getInt("Quantity");
                category = rs.getString("Category");
                dto = new ProductDTO(id, quantity, name, creDate, expDate, category, status, price, image);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean addProduct(ProductDTO dto) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        try {
            String sql = "Insert INTO Products(Name, Price, Quantity, CategoryId, StatusId, Image, CreDate, ExpDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getName());
            preStm.setFloat(2, dto.getPrice());
            preStm.setInt(3, dto.getQuantity());
            preStm.setInt(4, dto.getCategoryId());
            preStm.setInt(5, Status.ACTIVE.ordinal() + 1);
            preStm.setString(6, dto.getImage());
            preStm.setString(7, dto.getCreDate());
            preStm.setString(8, dto.getExpDate());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean updateProduct(ProductDTO dto, int id) throws SQLException, NamingException, ClassNotFoundException, RuntimeException {
        boolean check = false;
        try {
            String sql = "Update Products Set Name = ?, Image = ?, Price = ?, CategoryId = ?, Quantity = ?, CreDate = ?, ExpDate = ?, StatusId = ? Where Id = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getName());
            preStm.setString(2, dto.getImage());
            preStm.setFloat(3, dto.getPrice());
            preStm.setInt(4, dto.getCategoryId());
            preStm.setFloat(5, dto.getQuantity());
            preStm.setString(6, dto.getCreDate());
            preStm.setString(7, dto.getExpDate());
            preStm.setInt(8, dto.getStatusId());
            preStm.setInt(9, id);
            
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean removeProducts(int id) throws SQLException, NamingException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "UPDATE Products SET StatusId = ? WHERE Id = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, Status.DEACTIVE.ordinal() + 1);
            preStm.setInt(2, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public ProductDTO getProductById(int id) throws SQLException, ClassNotFoundException, NamingException {
        String name = null;
        String image = null;
        String creDate = null;
        String expDate = null;
        int statusId = 0;
        int categoryId = 0;
        float price = 0;
        int quantity = 0;
        ProductDTO dto = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select Products.Id as Ids, Products.Name as Names , Products.Image as Images, CreDate, ExpDate, StatusId, CategoryId, Price, Quantity"
                    + " From Products JOIN Status ON Status.Id = Products.StatusId"
                    + "             JOIN Categories ON Categories.Id = Products.CategoryId"
                    + " Where Products.Id = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                name = rs.getString("Names");
                image = rs.getString("Images");
                creDate = rs.getString("CreDate");
                expDate = rs.getString("ExpDate");
                statusId = rs.getInt("StatusId");
                categoryId = rs.getInt("CategoryId");
                price = rs.getFloat("Price");
                quantity = rs.getInt("Quantity");
                id = rs.getInt("Ids");
                dto = new ProductDTO(id, categoryId, statusId, name, creDate, expDate, image, price, quantity);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public int findIdByTitle(String title) throws Exception {
        int id = 0;
        try {
            String sql = "Select Id From Products Where Name = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, title);
            rs = preStm.executeQuery();
            while (rs.next()) {
                id = rs.getInt("Id");
            }
        } finally {
            closeConnection();
        }
        return id;
    }
    
}
