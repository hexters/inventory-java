/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.TableModel;

import com.inventory.Model.Supplier;
import com.inventory.Model.User;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Hexters
 */
public class SupplierTableModel extends AbstractTableModel {

     private final ArrayList<Supplier> lists = new ArrayList<>();
    private final String[] headers = { "Nama PIC", "Nama Perusahaan", "Alamat" };
    
    @Override
    public int getRowCount() {
        return lists.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Supplier item = lists.get(rowIndex);
        switch(columnIndex) {
            case 0: return item.getPic_name();
            case 1: return item.getCompany_name();
            case 2: return item.getAddress();
            default: return null;
        }
    }
    
    public Object getRowAt(int rowIndex) {
        return lists.get(rowIndex);
    }
    
    public void addRow(Supplier item) {
        lists.add(item);
        fireTableRowsInserted(lists.size() - 1, lists.size() - 1);
    }
    
    public void all(Supplier item) {
        item.getAll().forEach((itm) -> {
            this.addRow(itm);
        });
    }
    
    public void updateRow(int rowIndex) {
        System.out.println("Row index " + rowIndex);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
    
}
