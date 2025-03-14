package com.scaler.productservice.controller;

import com.scaler.productservice.dto.CreateProductRequestDto;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.service.FakeStoreProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private FakeStoreProductService service;

    public ProductController(FakeStoreProductService inputService) {
        this.service = inputService;
    }




    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        // validations
        if(id == 10000) {
            throw new IllegalArgumentException("id cannot be 10000");
        }

        //product not found exception // CREATE OUR CUSTOM EXCEPTION.
        Product product = service.getProductById(id); // service = new SelfProductService()
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        return product;
    }



    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        //validations
        // Step 2: Call the service layer to get all products
        /*
        Returning ResponseEntity directly from the controller helps avoid extra handling in the service layer for setting status codes and headers.
✅ ResponseEntity contains the HTTP response details:

Headers (e.g., Content-Type, custom headers)
Body (actual data, like List<Product>)
Status Code (e.g., 200 OK, 404 Not Found)
         */
        List<Product> products = service.getAllProducts();
        // throwing run time exception to checking:
        // -> creating own exception : throw new RuntimeException();

        return ResponseEntity.ok(products); //http status code is 200

        /*
        Setting a Different Status Code (201 Created)
return ResponseEntity.status(HttpStatus.CREATED).body(products);

if (products.isEmpty()) {
    return ResponseEntity.noContent().build();
}
return ResponseEntity.ok(products); 204 no content
         */


    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto request) {
        /*
    private String title;
    private String imageURL;
    private String description;
    private CategoryRequestDTO category;
         */
        // you can do validations
        if (request.getDescription() == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (request.getTitle() == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }

        return service.createProduct(request.getTitle(), request.getImageURL(),
        request.getCategory().getTitle(), request.getDescription());




    }





    @PutMapping("/products/{id}")
    public void UpdateProduct(@PathVariable("id") Integer id) {

    }

    @DeleteMapping("/products")
    public void deleteProduct() {

    }






}
