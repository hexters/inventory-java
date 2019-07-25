/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Model;

import java.util.*;

/**
 *
 * @author tay
 */
public class Supplier extends Model {
    private int id;
    private String pic_name;
    private String company_name;
    private String address;
    private String state;
    private String type;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return id;
    }

    public String getPic_name() {
        return pic_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    
    
    
}
