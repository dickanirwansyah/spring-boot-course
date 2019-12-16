package com.idgenerali.backendmybatis.service;

import com.idgenerali.backendmybatis.model.Product;

import java.util.ArrayList;

public interface ProductService {
    ArrayList<Product> listProducts();
    Product findById(int id);
    Product findByName(String name);
    boolean save(Product product);
    boolean update(Product product);
    boolean handleSaveOrUpdate(Product product);
    boolean delete(int id);
}
