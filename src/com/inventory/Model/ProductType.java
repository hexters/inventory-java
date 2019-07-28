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
public class ProductType extends Model {
    
    private int id;
    private String name;
    private String state;
    private String type;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
        
            String Query = "INSERT INTO product_types(name, state, created_at, updated_at)";
            Query += "VALUES(?, 'active', NOW(),NOW());";
            
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, getName());
            prepare.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error when create product_types " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    public void update() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        try {
            String Query = "UPDATE product_types SET name=?, updated_at = NOW() WHERE id =?;";
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, getName());
            prepare.setInt(2, getId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error when updated product_types " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void inactivated() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        try {
            String Query = "UPDATE product_types SET state =?, updated_at = NOW() WHERE id =?;";
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, "inactive");
            prepare.setInt(2, getId());
            prepare.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error when updated product_types " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }
    
    public ArrayList<ProductType> getAll() {

        ArrayList<ProductType> lists = new ArrayList<>();
        try {
            String Query = "SELECT * FROM product_types WHERE state = 'active'";
            PreparedStatement prepare = db.prepareStatement(Query);
            result = prepare.executeQuery();

            while(result.next()) {
                try {
                    ProductType item = new ProductType();
                    item.setId(result.getInt("id"));
                    item.setName(result.getString("name"));
                    lists.add(item);
                } catch (Exception ex) {
                    System.out.println("Error add select product_types " + ex.getMessage());
                }
            }
            return lists;
        } catch (SQLException e) {
            System.out.println("Error select product_types " + e.getMessage());
            return null;
        }
    }
}
