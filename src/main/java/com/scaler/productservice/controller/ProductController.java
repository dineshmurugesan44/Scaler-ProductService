package com.scaler.productservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {


    @GetMapping("/products/{id}")
    public void getProductById(@PathVariable("id") Integer id) {

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
