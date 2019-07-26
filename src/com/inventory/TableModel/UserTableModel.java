/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.TableModel;

import com.inventory.Model.User;
import java.sql.RowId;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Hexters
 */
public class UserTableModel extends AbstractTableModel {

    private final ArrayList<User> lists = new ArrayList<>();
    private final String[] headers = { "Nama", "Email", "Gender", "Status" };

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
        User item = lists.get(rowIndex);
        switch(columnIndex) {
            case 0: return item.getName();
            case 1: return item.getEmail();
            case 2: return item.getGender();
            case 3: return item.getState();
            default: return null;
        }
    }
    
    public Object getRowAt(int rowIndex) {
        return lists.get(rowIndex);
    }
    
    public void addRow(User user) {
        lists.add(user);
        fireTableRowsInserted(lists.size() - 1, lists.size() - 1);
    }
    
    public void all(User user) {
        user.getAll().forEach((item) -> {
            this.addRow(item);
        });
    }
    
    public void updateRow(int rowIndex) {
        System.out.println("Row index " + rowIndex);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}
