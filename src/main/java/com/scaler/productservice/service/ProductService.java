package com.scaler.productservice.service;

import com.scaler.productservice.model.Product;

import java.util.List;

/***
 * this ia an interface
 */

public interface ProductService {

    Product getProductById(Integer id);

    List<Product> getAllProducts();

    Product createProduct(String title, String imageURL, String catTitle, String description);


}
