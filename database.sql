-- Author: Asep Saepuloh Sahidin
-- NPM: 15111263
-- K17B CIDRM
-- //////////////////////////////////

-- Create database
CREATE DATABASE inventory_15111263;

-- Select database
USE inventory_15111263;

-- Create Table User

CREATE TABLE IF NOT EXISTS users (
  id int(11) AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(200) NOT NULL,
  password VARCHAR(50) NOT NULL,
  gender VARCHAR(10) NOT NULL,
  address VARCHAR(255) NOT NULL,
  state VARCHAR(100) NULL,
  type VARCHAR(100) NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY (id)
);

-- Create Table Supplier

CREATE TABLE IF NOT EXISTS suppliers (
  id int(11) AUTO_INCREMENT,
  pic_name VARCHAR(50) NOT NULL,
  company_name VARCHAR(200) NOT NULL,
  address VARCHAR(255) NOT NULL,
  state VARCHAR(100) NULL,
  type VARCHAR(100) NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY (id)
);


-- Create Table Product

CREATE TABLE IF NOT EXISTS products (
  id int(11) AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  sku VARCHAR(200) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  description TEXT NULL,
  product_type_id INT NOT NULL,
  state VARCHAR(100) NULL,
  type VARCHAR(100) NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY (id)
);

-- Create Table ProductType

CREATE TABLE IF NOT EXISTS product_types (
  id int(11) AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  state VARCHAR(100) NULL,
  type VARCHAR(100) NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY (id)
);


-- Create Table Transaction

CREATE TABLE IF NOT EXISTS transactions (
  id int(11) AUTO_INCREMENT,
  user_id INT NOT NULL,
  supplier_id INT NOT NULL,
  product_id INT NOT NULL,
  qty_in INT NOT NULL,
  qty_out INT NOT NULL,
  state VARCHAR(100) NULL,
  type VARCHAR(100) NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY (id)
);

-- Insert data user 
INSERT INTO users (name, email, password, gender, address, state, type, created_at, updated_at) VALUES 
("Irwan Riswandi", "user@email.com", "password", "L", "Majalaya", "active", "com.inventory.Model.User", NOW(), NOW()),
("Asep Saepuloh Sahidin", "admin@email.com", "password", "L", "Pangalengan", "active", "com.inventory.Model.Admin", NOW(), NOW());
