/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.main;

import com.inventory.View.*;

/**
 *
 * @author Hexters
 */
public class Main extends javax.swing.JFrame {
    
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            MainFrameView mainFrame = new MainFrameView();
            mainFrame.showPanel("authView");
            mainFrame.setVisible(true);
        });
    }
}
