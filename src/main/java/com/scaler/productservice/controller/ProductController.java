package com.scaler.productservice.controller;

import com.scaler.productservice.dto.CreateProductRequestDto;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.service.FakeStoreProductService;
import com.scaler.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService service;

    public ProductController(@Qualifier("selfProductService") ProductService inputService) {

        this.service = inputService;
    }




    @GetMapping("/products/{id}")
    @Cacheable(value = "product", key = "#id")
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
    @Cacheable(value = "products")
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
    @CachePut(value = "product", key = "#result.id")
    public Product createProduct(@RequestBody CreateProductRequestDto request) {
        // you can do validations
        if (request.getDescription() == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (request.getTitle() == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }

        return service.createProduct(request.getTitle(), request.getImageURL(), request.getCategory().getTitle(),
                request.getDescription(),request.getCreatedByUserName());
    }





    @PutMapping("/products/{id}")
    public void UpdateProduct(@PathVariable("id") Integer id) {

    }

    @DeleteMapping("/products")
    public void deleteProduct() {

    }

    //creating pagenated api to return limeited product in a page

    @GetMapping("/products/{pageNo}/{pageSize}")
    public ResponseEntity<Page<Product>> getPaginatedProducts(@PathVariable("pageNo") int pageNo,
            @PathVariable("pageSize") int pageSize) {

        Page<Product> products = service.getPaginatedProducts(pageNo, pageSize);



        return ResponseEntity.ok(products);
    }






}
