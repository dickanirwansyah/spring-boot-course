package com.idgenerali.backendmybatis.controller;


import com.idgenerali.backendmybatis.model.Product;
import com.idgenerali.backendmybatis.service.ProductService;
import com.idgenerali.backendmybatis.util.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private Map<String, String> hashMapValidation;

    @GetMapping(value = "")
    public ResponseEntity<ResponseApi<ArrayList<Product>>> listProducts(){
        ArrayList<Product> products = productService.listProducts();

        ResponseApi responseApi = new ResponseApi();

        if (products.size() < 1){
            responseApi.setTimestamp(new Date());
            responseApi.setMessages("data not found");
            responseApi.setStatus(HttpStatus.NOT_FOUND.value());
            responseApi.setData(null);
            return ResponseEntity.badRequest().body(responseApi);
        }

        responseApi.setData(products);
        responseApi.setMessages("success");
        responseApi.setTimestamp(new Date());
        responseApi.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(responseApi);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseApi<Boolean>> createProduct(@RequestBody @Valid Product product,
                                                              BindingResult bindingResult){

        ResponseApi responseApi = null;
        if (bindingResult.hasErrors()){
            hashMapValidation = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                responseApi = execute(false, HttpStatus.BAD_REQUEST.value(), fieldError.getField());
            }
            return new ResponseEntity<>(responseApi, HttpStatus.BAD_REQUEST);
        }

        boolean valid = productService.save(product);
        if (valid == true){
            responseApi = execute(valid, HttpStatus.OK.value(), "success");
        }
        return ResponseEntity.ok().body(responseApi);
    }

    @PostMapping(value = "/create-or-update")
    public ResponseEntity<ResponseApi<Boolean>> handleCreateOrSave(@RequestBody @Valid Product product,
                                                                   BindingResult bindingResult){

        ResponseApi responseApi = null;
        if (bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                responseApi = execute(false, HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(responseApi, HttpStatus.BAD_REQUEST);
        }

        if (product.getId() == -1){
            boolean valid = productService.save(product);
            if (valid == true){
                responseApi = execute(valid, HttpStatus.OK.value(), "success");
                return ResponseEntity.ok().body(responseApi);
            }
        }else{
            boolean valid = productService.update(product);
            if (valid == true){
                responseApi = execute(valid, HttpStatus.OK.value(), "success");
                return ResponseEntity.ok().body(responseApi);
            }
        }

        return ResponseEntity.ok().body(responseApi);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<ResponseApi<Boolean>> updateProduct(@PathVariable("id")int id,
                                                              @RequestBody @Valid Product product,
                                                              BindingResult bindingResult){
        ResponseApi responseApi = null;
        if (bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                responseApi = execute(false, HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(responseApi, HttpStatus.BAD_REQUEST);
        }

        product.setId(id);
        boolean valid = productService.update(product);
        if (valid == true){
            responseApi = execute(valid, HttpStatus.OK.value(), "success");
        }
        return ResponseEntity.ok().body(responseApi);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseApi<Product>> findById(@PathVariable("id")int id){
        Product product = productService.findById(id);
        ResponseApi responseApi = execute(product, HttpStatus.OK.value(), "success");
        return ResponseEntity.ok().body(responseApi);
    }

    @PostMapping(value = "/by")
    public ResponseEntity<ResponseApi<Product>> findByName(@RequestParam("name")String name){
        Product product = productService.findByName(name);
        ResponseApi responseApi = execute(product, HttpStatus.OK.value(), "success");
        return ResponseEntity.ok().body(responseApi);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseApi<Boolean>> deleteProduct(@PathVariable("id")int id){
        boolean valid = productService.delete(id);
        ResponseApi responseApi = execute(valid, HttpStatus.OK.value(), "success");
        return ResponseEntity.ok().body(responseApi);
    }

    private ResponseApi execute(Object data, int status, String messages){
        ResponseApi responseApi = null;
        switch (status){
            case 200 :
                responseApi = new ResponseApi();
                responseApi.setStatus(HttpStatus.OK.value());
                responseApi.setData(data);
                responseApi.setMessages(messages);
                responseApi.setTimestamp(new Date());
                break;
            case 400 :
                responseApi = new ResponseApi();
                responseApi.setStatus(HttpStatus.BAD_REQUEST.value());
                responseApi.setData(data);
                responseApi.setMessages(messages);
                responseApi.setTimestamp(new Date());
                break;
           default:return null;
        }

        return responseApi;
    }
}
