package com.scaler.productservice.controller;

import com.scaler.productservice.model.Product;
import com.scaler.productservice.service.FakeStoreProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private FakeStoreProductService service;

    public ProductController(FakeStoreProductService inputService) {
        this.service = inputService;
    }




    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        // validations
        if(id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        return service.getProductById(id);

    }
    @PostMapping("/products")
    public void createProduct() {

    }
    @GetMapping("/products")
    public void getAllProducts() {

    }
    @PutMapping("/products/{id}")
    public void UpdateProduct(@PathVariable("id") Integer id) {

    }

    @DeleteMapping("/products")
    public void deleteProduct() {

    }






}
