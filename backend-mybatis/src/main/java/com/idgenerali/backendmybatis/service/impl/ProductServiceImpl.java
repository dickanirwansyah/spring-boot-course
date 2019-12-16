package com.idgenerali.backendmybatis.service.impl;

import com.idgenerali.backendmybatis.exception.ResourceConflictException;
import com.idgenerali.backendmybatis.exception.ResourceNotFoundException;
import com.idgenerali.backendmybatis.mapper.ProductMapper;
import com.idgenerali.backendmybatis.model.Product;
import com.idgenerali.backendmybatis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ArrayList<Product> listProducts() {
        ArrayList<Product> data = new ArrayList<>();
        List<Product> products = productMapper.listProduct();
        for (Product product : products){
            data.add(product);
        }
        return data;
    }

    @Override
    public Product findById(int id) {
        return productMapper.findProductById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id with "+id+" not found"));
    }

    @Override
    public Product findByName(String name) {
        return productMapper.findProductByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("name with "+name+" not found"));
    }

    @Override
    public boolean save(Product product) {
        boolean valid = false;
        Optional<Product> productByName = productMapper.findProductByName(product.getName());
        Optional<Product> productById = productMapper.findProductById(product.getId());
        if (productByName.isPresent()){
            throw new ResourceConflictException("product with name "+product.getName()+" is existing");
        }
        if (productById.isPresent()){
            throw new ResourceNotFoundException("product with id "+product.getId()+" is existing");
        }

        Product entityProduct = new Product();
        entityProduct.setId(product.getId());
        entityProduct.setName(product.getName());
        entityProduct.setStock(product.getStock());
        entityProduct.setPrice(product.getPrice());
        entityProduct.setCategory(product.getCategory());
        entityProduct.setCreatedAt(new Date());
        entityProduct.setUpdatedAt(new Date());
        productMapper.saveProduct(entityProduct);
        valid = true;
        return valid;
    }

    @Override
    public boolean update(Product product) {
        boolean valid = false;
        Optional<Product> productByName = productMapper.findProductByName(product.getName());
        Optional<Product> productById = productMapper.findProductById(product.getId());
        if (productByName.isPresent()){
            throw new ResourceConflictException("product with name "+product.getName()+" is existing");
        }
        if (!productById.isPresent()){
            throw new ResourceNotFoundException("product id "+product.getId()+" not found");
        }

        Product entityProduct = new Product();
        entityProduct.setId(product.getId());
        entityProduct.setName(product.getName());
        entityProduct.setStock(product.getStock());
        entityProduct.setPrice(product.getPrice());
        entityProduct.setCategory(product.getCategory());
        entityProduct.setCreatedAt(new Date());
        entityProduct.setUpdatedAt(new Date());
        productMapper.updateProduct(entityProduct);
        valid = true;
        return valid;
    }

    @Override
    public boolean handleSaveOrUpdate(Product product) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        boolean valid = false;
        Optional<Product> productId = productMapper.findProductById(id);
        if (!productId.isPresent()){
            throw new ResourceNotFoundException("product with id "+id+" not found");
        }

        valid = true;
        productMapper.deleteProduct(id);
        return valid;
    }
}
