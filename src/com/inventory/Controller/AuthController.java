/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Controller;

import com.inventory.Model.User;
import com.inventory.View.AuthView;

/**
 *
 * @author tay
 */
public class AuthController {
    
    private AuthView view;
    private User user;
    
    public AuthController() {
        view = new AuthView();
        user = new User();
    }
    
    public void login() {
        try {
            user.setEmail(view.FormEmail().getText().toString());
            user.setPassword(view.FormPassword().getPassword().toString());

            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}
