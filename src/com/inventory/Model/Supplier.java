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
import javax.swing.JOptionPane;

/**
 *
 * @author tay
 */
public class Supplier extends Model {
    private int id;
    private String pic_name;
    private String company_name;
    private String address;
    private String state;
    private String type;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return id;
    }

    public String getPic_name() {
        return pic_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getAddress() {
        return address;
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

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setAddress(String address) {
        this.address = address;
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
        
            String Query = "INSERT INTO suppliers(pic_name, company_name, address, state, created_at, updated_at)";
            Query += "VALUES(?,?,?, 'active', NOW(),NOW());";
            
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, getPic_name());
            prepare.setString(2, getCompany_name());
            prepare.setString(3, getAddress());
            prepare.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error when create supplier " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    public void update() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        try {
            String Query = "UPDATE suppliers SET pic_name =?, company_name =?, address =?, updated_at = NOW() WHERE id =?;";
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, getPic_name());
            prepare.setString(2, getCompany_name());
            prepare.setString(3, getAddress());
            prepare.setInt(4, getId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error when updated supplier " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void inactivated() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        try {
            String Query = "UPDATE suppliers SET state =?, updated_at =? WHERE id =?;";
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, "inactive");
            prepare.setString(2, date);
            prepare.setInt(3, getId());
            prepare.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error when updated supplier " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    public ArrayList<Supplier> getAll() {

        ArrayList<Supplier> lists = new ArrayList<>();
        try {
            String Query = "SELECT * FROM suppliers WHERE state = 'active'";
            PreparedStatement prepare = db.prepareStatement(Query);
            result = prepare.executeQuery();

            while(result.next()) {
                try {
                    Supplier supplier = new Supplier();
                    supplier.setId(result.getInt("id"));
                    supplier.setPic_name(result.getString("pic_name"));
                    supplier.setCompany_name(result.getString("company_name"));
                    supplier.setAddress(result.getString("address"));
                    lists.add(supplier);
                } catch (Exception ex) {
                    System.out.println("Error add select supplier " + ex.getMessage());
                }
            }
            return lists;
        } catch (SQLException e) {
            System.out.println("Error select supplier " + e.getMessage());
            return null;
        }
    }
    
}
