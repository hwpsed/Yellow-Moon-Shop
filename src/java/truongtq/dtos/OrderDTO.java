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
public class OrderDTO implements Serializable{
    int id, status, paymentId;
    String userId, name, date, address, phone;
    float total;

    
    public OrderDTO(String name, String date, String address, String phone, float total, int paymentId) {
        this.name = name;
        this.date = date;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.paymentId = paymentId;
    }

    

    public OrderDTO(String userId, String name, String date, String address, String phone, float total, int paymentId) {
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.paymentId = paymentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
