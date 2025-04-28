package com.scaler.productservice.service;

import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.repositary.CategoryRepo;
import com.scaler.productservice.repositary.ProductRepo;
import com.scaler.productservice.repositary.projection.ProductProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        System.out.println("Inside getProductById.....");
        //select * from products where id = inputId //instead pf we can use jpa
        Optional<Product> response = productRepo.findById(id);
        if (!response.isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }
        System.out.println("Fetched product : " + response);

        /**
         * some sample test code below to test CAT REpo.
         */
        List<Category> categories = categoryRepo.findAll();
        System.out.println("Fetched List of Categories....");

        List<Product> productList = categories.get(0).getProducts();

        return response.get();
    }

    @Override
    public List<Product> getAllProducts() {

        //ProductProjection response = productRepo.getProductNameByTitle("phone samsung");
        //System.out.println("Fetched product : " + response.getDescription()+ " " + response.getTitle());
        return productRepo.findAll(); //return all product

    }
    @Override
    @Transactional
    public Product createProduct(String title, String imageURL, String catTitle, String description,String createdByUserName) {
        //Step1:
        validateInputRequest(title, imageURL, catTitle, description);

        // Step2:
        Product product = new Product();
        product.setImageURL(imageURL);
        product.setTitle(title);
        product.setDescription(description);



        // Step3: check if cat exists in the DB
        Optional<Category> existingCategory = categoryRepo.findByTitle(catTitle);
        if (existingCategory.isEmpty()) {
            Category category = new Category();
            category.setTitle(catTitle);
            category = categoryRepo.save(category);  // Save the new category
            product.setCategory(category); // Set the new category to the product
        } else {
            // If category exists, set it to the product
            product.setCategory(existingCategory.get());
        }


        //Step 4: The `isDeleted` field is already defaulted to false in BaseModel
        //    // No need to set `isDeleted` unless you want to override it for this particular product
        //    product.setIsDeleted(false);  // This is redundant since it's set in BaseModel


        // Step 4: Handle 'createdByUserName'
        if (createdByUserName != null && !createdByUserName.isEmpty()) {
            product.setCreatedByUserName(createdByUserName);  // Use provided value if available
        } else {
            product.setCreatedByUserName("dinesh");  // Default value if not provided
        }


        // Finally save to the DB.
        // Step 4: Save the Product to the DB (createdAt and updatedAt will be handled by @PrePersist and @PreUpdate)
        Product response = productRepo.save(product);
        // Step 5: Return the saved Product
        return response;
    }

    @Override
    public Page<Product> getPaginatedProducts(int pageNo, int pageSize) {
      // pageNo:1 & pageSize: 10
      Pageable pageable = PageRequest.of(pageNo, pageSize);

      String sortBy = "title";

      Pageable pageableWithSort = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, sortBy);
      Page<Product> productPage = productRepo.findAll(pageableWithSort);

        return productPage;
    }



    //function called in create all products
    private void validateInputRequest(String title, String imageURL, String catTitle, String description) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null");
        }
    }



}