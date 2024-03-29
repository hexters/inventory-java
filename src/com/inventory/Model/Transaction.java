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
public class Transaction extends Model {
 
    private int id;
    private int product_id;
    private int qty_in;
    private int qty_out;
    private String state;
    private String type;
    private Date created_at;
    private Date updated_at;
    
    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setQty_in(int qty_in) {
        this.qty_in = qty_in;
    }

    public void setQty_out(int qty_out) {
        this.qty_out = qty_out;
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
    private int user_id;
    private int supplier_id;

    public int getId() {
        return id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public int getQty_in() {
        return qty_in;
    }

    public int getQty_out() {
        return qty_out;
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
    
    public void create() throws Exception {
        
        try {
            
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatDate.format(new Date());
        
            String Query = "INSERT INTO transactions(product_id, supplier_id, user_id, qty_in, qty_out, state, created_at, updated_at)";
            Query += "VALUES(?,?,?,?,?, 'active', NOW(),NOW());";
            
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setInt(1, getProduct_id());
            prepare.setInt(2, getSupplier_id());
            prepare.setInt(3, getUser_id());
            prepare.setInt(4, getQty_in());
            prepare.setInt(5, getQty_out());
            prepare.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error when create transactions " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    public void update() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        try {
            String Query = "UPDATE transactions SET product_id=?, supplier_id=?, user_id=?, qty_in=?, qty_out=?, updated_at = NOW() WHERE id =?;";
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setInt(1, getProduct_id());
            prepare.setInt(2, getSupplier_id());
            prepare.setInt(3, getUser_id());
            prepare.setInt(4, getQty_in());
            prepare.setInt(5, getQty_out());
            prepare.setInt(6, getId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error when updated transactions " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void inactivated() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        try {
            String Query = "UPDATE transactions SET state =?, updated_at = NOW() WHERE id =?;";
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, "inactive");
            prepare.setInt(2, getId());
            prepare.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error when updated transactions " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }
}
