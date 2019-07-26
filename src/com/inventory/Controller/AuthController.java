/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Controller;

import com.inventory.Model.User;
import com.inventory.View.AuthPanelView;
import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author tay
 */
public class AuthController extends Controller {
    
    private final AuthPanelView authPanel;
    private final User user;
    
    public AuthController(AuthPanelView authPanel) {
        this.authPanel = authPanel;
        user = new User();
    }
    
    public void login() {
        try {
            user.setEmail(authPanel.formEmail().getText());
            user.setPassword(String.valueOf(authPanel.formPassword().getPassword()));
            user.auth(authPanel);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Peringatan!", 1);
        }
    }
    
    
}
