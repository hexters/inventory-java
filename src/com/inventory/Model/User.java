/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Model;

import com.inventory.View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 *
 * @author tay
 */
public class User extends Model {
    
    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String state;
    private String type;
    private Date created_at;
    private Date updated_at;
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) throws java.lang.Exception {
        if(name.equals("")){
            throw new Exception("Nama tidak boleh kosong");
        }
        this.name = name;
    }

    public void setEmail(String email) throws java.lang.Exception {
        if(email.equals("")){
            throw new Exception("Email tidak boleh kosong");
        } else if(!this.emailValidate(email)) {
            throw new Exception("Format Email salah!");
        }
        this.email = email;
    }

    public void setPassword(String password) throws java.lang.Exception {
        if(password.equals("")){
            throw new Exception("Password tidak boleh kosong");
        }
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
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
    
    public void auth(AuthPanelView authPanel) {
        try {
            
            String Query = "SELECT * FROM users WHERE email = ? AND password = ? AND state = 'active';";
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, getEmail());
            prepare.setString(2, getPassword());
            result = prepare.executeQuery();
            
            
            while(result.next()) {
                System.out.println(result.getString("type"));
                switch(result.getString("type")) {
                    case "com.inventory.Model.Admin":
                        authPanel.mainFrame.setTitle("UAS - Halaman Admin");
                        authPanel.mainFrame.showPanel("adminView");
                        break;
                    case "com.inventory.Model.User":
                        authPanel.mainFrame.setTitle("UAS - Halaman User");                        
                        authPanel.mainFrame.showPanel("userView");
                        break;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error User : " + e.getMessage());
        }
        
    }

    public User create() throws Exception {
        
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        
        
        try {
            String Query = "INSERT INTO users (name, email, password, gender, state, type, address, created_at, updated_at)";
            Query += "VALUES(?,?,?,?,?,?,?,?,?);";
            System.out.println(Query);
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, getName());
            prepare.setString(2, getEmail());
            prepare.setString(3, getPassword());
            prepare.setString(4, getGender());
            prepare.setString(5, getState());
            prepare.setString(6, getType());
            prepare.setString(7, getAddress());
            prepare.setString(8, date);
            prepare.setString(9, date);
            prepare.executeUpdate();
            
            return this;
            
        } catch (SQLException e) {
            System.out.println("Error when created user " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }
    
    public User update() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        try {
            String Query = "UPDATE users SET name =?, email =?, password =?, gender =?, state =?, type =?, address =?, updated_at =? WHERE id =?;";
            System.out.println(Query);
            System.out.println("ID User " + getId());
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, getName());
            prepare.setString(2, getEmail());
            prepare.setString(3, getPassword());
            prepare.setString(4, getGender());
            prepare.setString(5, getState());
            prepare.setString(6, getType());
            prepare.setString(7, getAddress());
            prepare.setString(8, date);
            prepare.setInt(9, getId());
            prepare.executeUpdate();
            
            return this;
            
        } catch (SQLException e) {
            System.out.println("Error when updated user " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }
    
    public User inactivated() throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatDate.format(new Date());
        try {
            String Query = "UPDATE users SET state =?, updated_at =? WHERE id =?;";
            System.out.println(Query);
            System.out.println("ID User " + getId());
            PreparedStatement prepare = db.prepareStatement(Query);
            prepare.setString(1, "inactive");
            prepare.setString(2, date);
            prepare.setInt(3, getId());
            prepare.executeUpdate();
            
            return this;
            
        } catch (SQLException e) {
            System.out.println("Error when updated user " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }
    
    public ArrayList<User> getAll() {
        
        ArrayList<User> lists = new ArrayList<>();
        try {
            String Query = "SELECT * FROM users WHERE state = 'active'";
            PreparedStatement prepare = db.prepareStatement(Query);
            result = prepare.executeQuery();
            
            while(result.next()) {
                try {
                    User user = new User();
                    user.setId(result.getInt("id"));
                    user.setName(result.getString("name"));
                    user.setEmail(result.getString("email"));
                    user.setPassword(result.getString("password"));
                    user.setGender(result.getString("gender"));
                    user.setState(result.getString("state"));
                    user.setType(result.getString("type"));
                    user.setAddress(result.getString("address"));
                    lists.add(user);
                } catch (Exception ex) {
                    System.out.println("Error add select users " + ex.getMessage());
                }
            }
            return lists;
        } catch (SQLException e) {
            System.out.println("Error select users " + e.getMessage());
            return null;
        }
    }
    
    
    
    public static boolean emailValidate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    
}
