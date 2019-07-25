/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Dao;

import com.inventory.Config.DbConnection;
import java.sql.*;

/**
 *
 * @author tay
 */
public class Dao {
    
    public Connection connect() {
        return (Connection) new DbConnection().getNewConnection();
    }
    
}
