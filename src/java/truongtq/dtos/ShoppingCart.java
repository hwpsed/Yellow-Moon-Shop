/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.dtos;

import java.util.HashMap;

/**
 *
 * @author harry
 */
public class ShoppingCart {

    private String customerName;
    private HashMap<String, ProductDTO> cart;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, ProductDTO> getCart() {
        return cart;
    }

    public ShoppingCart() {
        this.customerName = "guest";
        this.cart = new HashMap();
    }

    public ShoppingCart(String customerName) {
        this.customerName = customerName;
        this.cart = new HashMap();
    }

    public void addToCart(ProductDTO dto) throws Exception {
        if (this.cart.containsKey(dto.getName())) {
            int quantity = this.cart.get(dto.getName()).getStock() + 1;
            dto.setStock(quantity);
        }
        this.cart.put(dto.getName(), dto);
    }

    public void remove(String title) throws Exception {
        if (this.cart.containsKey(title)) {
            this.cart.remove(title);
        }
    }

    public void updateCart(String name, int quantity) throws Exception {
        if (this.cart.containsKey(name)) {
            this.cart.get(name).setStock(quantity);
        }
    }

//    public float updatePrice(String discount) throws Exception{
//        float result = 0;
//        DiscountDAO disDAO = new DiscountDAO();
//        DiscountDTO disDTO = disDAO.getDiscountValue(discount);
//        for (ProductDTO dto : this.cart.values()) {
//            result += dto.getPrice() * dto.getQuantity();
//        }
//        return result - result * disDTO.getDiscountPercent()/100;
//    }
    public float getTotal() {
        float result = 0;
        for (ProductDTO dto : this.cart.values()) {
            result += dto.getPrice() * dto.getStock();
        }
        return result;
    }
}
