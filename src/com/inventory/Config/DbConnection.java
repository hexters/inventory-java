/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Config;

import java.sql.*;

/**
 *
 * @author tay
 */
public class DbConnection {
    
    protected String host = "localhost";
    protected String port = "3306";
    protected String database = "inventory_15111263";
    protected String user = "";
    protected String password = "";
    /**
     * - - - - - - - - - - - - - - - - - - - 
     */
    protected static DbConnection connection;
    protected static Statement statement;
    
    public DbConnection getNewConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (DbConnection) DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
            return connection;
        } catch (Exception e) {
            return null;
        }
    }
    
}
