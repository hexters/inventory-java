/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Controller;

import com.inventory.Model.Supplier;
import com.inventory.TableModel.SupplierTableModel;
import com.inventory.View.MasterSupplierView;
import javax.swing.JOptionPane;

/**
 *
 * @author Hexters
 */
public class SupplierController {
    
    private MasterSupplierView view;
    private Supplier supplier;
    public int ID = 0;
    public SupplierTableModel table;

    public SupplierController(MasterSupplierView view) {
        this.view = view;
        supplier = new Supplier();
        this.table = new SupplierTableModel();
        view.getTblItem().setModel(table);
        table.all(supplier);
    }
    
    public void submit() {
        if(this.ID > 0) {
            this.update();
        } else {
            this.save();
        }
    }
    
    private void save() {
        try {
            supplier.setPic_name(view.getInputPic().getText());
            supplier.setCompany_name(view.getInputPerusahaan().getText());
            supplier.setAddress(view.getInputAlamat().getText());
            supplier.create();
            
            view.clean();
            table.addRow(supplier);
           
            JOptionPane.showMessageDialog(null, "Supplier berhasil ditambahkan", "Peringatan!", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Peringatan!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void update() {
        try {
            supplier.setPic_name(view.getInputPic().getText());
            supplier.setCompany_name(view.getInputPerusahaan().getText());
            supplier.setAddress(view.getInputAlamat().getText());
            supplier.setId(this.ID);
            supplier.update();
            view.clean();
            JOptionPane.showMessageDialog(null, "Supplier berhasil diperbaharui", "Peringatan!", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Peringatan!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void inactive() {
        try {
            supplier.setId(this.ID);
            supplier.update();
            view.clean();
            JOptionPane.showMessageDialog(null, "Supplier berhasil dihapus", "Peringatan!", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Peringatan!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
