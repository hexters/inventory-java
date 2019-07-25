/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Dao;

import com.inventory.Model.User;
import java.sql.*;

/**
 *
 * @author tay
 */
public class UserDao extends Dao {

    
    public void create() throws SQLException {
        String query = "INSERT INTO users(name, email, password, gender, state, type, address, created_at, updated_at) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = this.connect().prepareStatement(query);
        
    }
    
    public void update() {
        
    }
    
    public void delete() {
        
    }
    
    
}
