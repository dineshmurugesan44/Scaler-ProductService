package com.scaler.productservice.service;

import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.repositary.CategoryRepo;
import com.scaler.productservice.repositary.ProductRepo;
import com.scaler.productservice.repositary.projection.ProductProjection;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }


    @Override
    public Product getProductById(Integer id) {
        //select * from products where id = inputId //instead pf we can use jpa
       Optional<Product> response = productRepo.findById(id);

       return response.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {

        //ProductProjection response = productRepo.getProductNameByTitle("phone samsung");
        //System.out.println("Fetched product : " + response.getDescription()+ " " + response.getTitle());
        return productRepo.findAll(); //return all product

    }

    @Override
    public Product createProduct(String title, String imageURL, String catTitle, String description) {
        //Step1:
        /*This is your internal function to make sure:

        None of the fields are null/empty

        Possibly check string length or format*/
        validateInputRequest(title, imageURL, catTitle, description);
        //Steo2:

        Product product = new Product();
        product.setTitle(title); // From Postman
        product.setImageURL(imageURL); // From Postman
        product.setDescription(description); // From Postman
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());

        Optional<Category> existingCategoryOptional = categoryRepo.findByTitle(catTitle);

        Category category;
        if (existingCategoryOptional.isPresent()) {
            // ✅ Category already exists — reuse it
            category = existingCategoryOptional.get();
        } else {
            // ❌ Not found — create and save a new one
            category = new Category();
            category.setTitle(catTitle);
            category.setCreatedAt(new Date()); // Optional: Set timestamps if not done automatically
            category.setUpdatedAt(new Date());
            category = categoryRepo.save(category); // Persist new category
        }

        // Step 4: Set category to the product
        product.setCategory(category);

        // Step 5: Save product to DB
        Product savedProduct = productRepo.save(product);

        // Step 6: Return saved product (with ID, timestamps, and category)
        return savedProduct;
    }




    private void validateInputRequest(String title, String imageURL, String catTitle, String description) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null");
        }
    }
}

