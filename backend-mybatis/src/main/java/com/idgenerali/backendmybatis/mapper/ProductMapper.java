package com.idgenerali.backendmybatis.mapper;

import com.idgenerali.backendmybatis.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    List<Product> listProduct();
    Optional<Product> findById(@Param("id")int id);
    Optional<Product> findProductById(@Param("id") int id);
    Optional<Product> findByName(@Param("name")String name);
    Optional<Product> findProductByName(@Param("name")String name);
    boolean saveProduct(@Param("product") Product product);
    boolean updateProduct(@Param("product") Product product);
    void deleteProduct(@Param("id")int id);
}
