package com.scaler.productservice.service;

import com.scaler.productservice.dto.CategoryRequestDTO;
import com.scaler.productservice.dto.FakeStoreResponseDTO;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductById(Integer id) {

        Product product = new Product();


        // 1. Call Fakestore API --> Use RestTemplate.

        ResponseEntity<FakeStoreResponseDTO> fakeStoreResponse =
        restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreResponseDTO.class);


        // 2. Get the response
        FakeStoreResponseDTO response = fakeStoreResponse.getBody();
        if (response == null) {
            throw new IllegalArgumentException("FakeStoreProduct not found!!");
        }



        //3. Convert the response to product model
        product = convertFakeStoreResponseToProduct(response);


        return product;
    }

    private Product convertFakeStoreResponseToProduct(FakeStoreResponseDTO response) {
        Product product = new Product();
        Category category = new Category();
        category.setTitle(response.getCategory());

        product.setId(response.getId());
        product.setCategory(category);
        product.setDescription(response.getDescription());
        product.setImageURL(response.getImage());
        product.setTitle(response.getTitle());

        return product;
    }
    @Override
    public List<Product> getAllProducts() {
        List<Product> response = new ArrayList<>();  // Create an empty list
        // Fetch all products from API and add them to the list

        // 1. Call Fakestore API --> Use RestTemplate.

        //rest tenplate is external 3rd party library. to make http call

        ResponseEntity<FakeStoreResponseDTO[]> fakeStoreProducts =
                restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreResponseDTO[].class);

        // 2. Get the response
        //3. Convert the response to product model
        for(FakeStoreResponseDTO fakeStoreDTO : fakeStoreProducts.getBody() ) {
            response.add(convertFakeStoreResponseToProduct(fakeStoreDTO));
        }

        return response;

    }

    @Override

    public Product createProduct(String title, String imageURL, String catTitle, String description) {
        Product response = new Product();

        //CREATING FAKESTORE REQUEST BODY TO GIVE DATA.

        FakeStoreResponseDTO requestBody = new FakeStoreResponseDTO();
        requestBody.setTitle(catTitle);
        requestBody.setDescription(description);
        requestBody.setTitle(title);
        requestBody.setImage(imageURL);



        // 1. Call Fakestore API --> Use RestTemplate.
        ResponseEntity <FakeStoreResponseDTO> fakeStoreResponse =
        restTemplate.postForEntity("https://fakestoreapi.com/products", requestBody, FakeStoreResponseDTO.class);

        //STATUS CODE
        System.out.println("Status code: " + fakeStoreResponse.getStatusCode());

        //covert
        response = convertFakeStoreResponseToProduct(fakeStoreResponse.getBody());


        return response;

    }
}

/**
 * @RestController
 * @Service
 * @Repository
 * @Component
 * @Controller
 * @Bean
 *
 *
 *
 *
 *
 * {
 * id:1,
 * title:'...',
 * price:'...', XX
 * category:'...', // String
 * description:'...',
 * image:'...'
 * }
 *
 */