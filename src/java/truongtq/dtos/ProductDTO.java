/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.dtos;

import java.io.Serializable;

/**
 *
 * @author harry
 */
public class ProductDTO implements Serializable {

    int id, quantity, categoryId, statusId, stock;
    String name, creDate, expDate, category, status, image;
    float price;

//    public ProductDTO(int quantity, int categoryId, int statusId, String name, String creDate, String expDate, String category, String image, float price) {
//        this.quantity = quantity;
//        this.categoryId = categoryId;
//        this.statusId = statusId;
//        this.name = name;
//        this.creDate = creDate;
//        this.expDate = expDate;
//        this.category = category;
//        this.image = image;
//        this.price = price;
//    }

    public ProductDTO(int statusId, int categoryId, String name, String creDate, String expDate, String image, float price, int quantity) {
        this.statusId = statusId;
        this.categoryId = categoryId;
        this.name = name;
        this.creDate = creDate;
        this.expDate = expDate;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }
    public ProductDTO(int categoryId, String name, String creDate, String expDate, String image, float price, int quantity) {
        this.categoryId = categoryId;
        this.name = name;
        this.creDate = creDate;
        this.expDate = expDate;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }
    
    public ProductDTO(int id, int categoryId, int statusId, String name, String creDate, String expDate, String image, float price, int quantity) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.creDate = creDate;
        this.expDate = expDate;
        this.image = image;
        this.price = price;
        this.statusId = statusId;
        this.quantity = quantity;
    }

    public ProductDTO(int id, int quantity, String name, String creDate, String expDate, String category, String status, float price, String image) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.creDate = creDate;
        this.expDate = expDate;
        this.category = category;
        this.status = status;
        this.price = price;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreDate() {
        return creDate;
    }

    public void setCreDate(String creDate) {
        this.creDate = creDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
