package com.idgenerali.backendmybatis.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class Product implements Serializable {

    private int id;

    @NotBlank(message = "name cannot be null.")
    private String name;

    @NotNull(message = "price cannot be null.")
    private double price;

    @NotNull(message = "stock cannot be null.")
    private int stock;

    @NotBlank(message = "category cannot be null.")
    private String category;
    private Date createdAt;
    private Date updatedAt;

}
