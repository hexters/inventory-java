/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.config;
import java.sql.*;

/**
 *
 * @author Hexters
 */
public class DBConnection {
    
    private final String host = "localhost";
    private final String port = "3306";
    private final String database = "inventory_15111263";
    private final String user = "root";
    private final String password = "";
    
    
    public Connection db;
    public ResultSet result;
    public DBConnection () {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            db = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
