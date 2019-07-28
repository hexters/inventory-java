/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Controller;

import com.inventory.Model.User;
import com.inventory.TableModel.UserTableModel;
import com.inventory.View.MasterEmployView;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Hexters
 */
public class UserController extends Controller {
    
    private MasterEmployView view;
    private User user;
    public UserTableModel table;
    public String method = "create";
    public int UserId = 0;
    public int rowIndex = 0;
    
    public UserController(MasterEmployView view) {
        this.view = view;
        this.user = new User();
        table = new UserTableModel();
        view.getTbl().setModel(table);
        table.all(user);
    }
    
    public void submit() {
        System.out.println(method);
        if(this.UserId > 0) {
            this.update();
        } else {
            this.create();
        }
        
    }
    
    public void update() {
        try {
            user.setName(view.getValueName().getText());
            user.setEmail(view.getValueEmail().getText());
            user.setPassword(String.valueOf(view.getValuePassword().getPassword()));
            if(view.getValuePria().isSelected() && !view.getValueWanita().isSelected()){
                user.setGender("L");
            } else if(!view.getValuePria().isSelected() && view.getValueWanita().isSelected()) {
                user.setGender("P");
            }
            if(view.getValueRole().getSelectedItem().equals("User")) {
                user.setType("com.inventory.Model.User");
            } else if(view.getValueRole().getSelectedItem().equals("Admin")) {
                user.setType("com.inventory.Model.Admin");
            }
            if(view.getValueState().getSelectedItem().toString().equals("Active")) {
                user.setState("active");
            } else if(view.getValueState().getSelectedItem().toString().equals("Inactive")) {
                user.setState("inactive");
            }
            user.setAddress(view.getValueAlamat().getText());
            user.setId(this.UserId);
            
            User result = user.update();
            view.clean();
            UserId = 0;
            
            table.updateRow(rowIndex);
            
            JOptionPane.showMessageDialog(null, "Karyawan berhasil diperbaharui", "Berhasil", JOptionPane.PLAIN_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Peringatan!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void inactivated(int id) {
         try {
            
             user.setId(id);
             user.inactivated();
             view.clean();
             table.updateRow(rowIndex);
             JOptionPane.showMessageDialog(null, "Karyawan berhasil di non aktif kan", "Berhasil", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Peringatan!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void create() {
        try {
            user.setName(view.getValueName().getText());
            user.setEmail(view.getValueEmail().getText());
            user.setPassword(String.valueOf(view.getValuePassword().getPassword()));
            if(view.getValuePria().isSelected() && !view.getValueWanita().isSelected()){
                user.setGender("L");
            } else if(!view.getValuePria().isSelected() && view.getValueWanita().isSelected()) {
                user.setGender("P");
            }
            if(view.getValueRole().getSelectedItem().equals("User")) {
                user.setType("com.inventory.Model.User");
            } else if(view.getValueRole().getSelectedItem().equals("Admin")) {
                user.setType("com.inventory.Model.Admin");
            }
            if(view.getValueRole().getSelectedItem().toString().equals("Active")) {
                user.setState("active");
            } else if(view.getValueRole().getSelectedItem().toString().equals("Inactive")) {
                user.setState("inactive");
            }
            user.setAddress(view.getValueAlamat().getText());
            User result = user.create();
            view.clean();
            table.addRow(user);
            
            JOptionPane.showMessageDialog(null, "Karyawan berhasil ditambahkan", "Berhasil", JOptionPane.PLAIN_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Peringatan!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
