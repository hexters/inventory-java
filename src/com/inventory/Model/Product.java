/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author tay
 */
public class Product extends Model {
    
    private int id;
    private String name;
    private String sku;
    private float price;
    private String description;
    private int product_type_id;private String state;
    private String type;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getProduct_type_id() {
        return product_type_id;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProduct_type_id(int product_type_id) {
        this.product_type_id = product_type_id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    
    public void create() throws Exception {
        
        try {
            
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatDate.format(new Date());
        
            String Query = "INSERT INTO products(name, sku, price, description, product_type_id, state, created_at, updated_at)";
            Query += "VALUES(?,?,?,?,?,'active', NOW(),NOW());";
            
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, getName());
            prepare.setString(2, getSku());
            prepare.setFloat(3, getPrice());
            prepare.setString(4, getDescription());
            prepare.setInt(5, getProduct_type_id());
            prepare.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error when create product " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    public void update() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        try {
            String Query = "UPDATE products SET name=?, sku=?, price=?, description=?, product_type_id=?, updated_at =? WHERE id =?;";
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, getName());
            prepare.setString(2, getSku());
            prepare.setFloat(3, getPrice());
            prepare.setString(4, getDescription());
            prepare.setInt(5, getProduct_type_id());
            prepare.setInt(6, getId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error when updated product " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void inactivated() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        try {
            String Query = "UPDATE products SET state =?, updated_at =? WHERE id =?;";
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, "inactive");
            prepare.setString(2, date);
            prepare.setInt(3, getId());
            prepare.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error when updated product " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }
    
    
    public ArrayList<Product> getAll() {

        ArrayList<Product> lists = new ArrayList<>();
        try {
            String Query = "SELECT * FROM products WHERE state = 'active'";
            PreparedStatement prepare = db.prepareStatement(Query);
            result = prepare.executeQuery();

            while(result.next()) {
                try {
                    Product item = new Product();
                    item.setId(result.getInt("id"));
                    item.setName(result.getString("name"));
                    item.setSku(result.getString("sku"));
                    item.setPrice(Float.parseFloat(result.getString("price")));
                    item.setDescription(result.getString("description"));
                    item.setProduct_type_id(Integer.parseInt(result.getString("product_type_id")));
                    lists.add(item);
                } catch (Exception ex) {
                    System.out.println("Error add select products " + ex.getMessage());
                }
            }
            return lists;
        } catch (SQLException e) {
            System.out.println("Error select products " + e.getMessage());
            return null;
        }
    }
}
