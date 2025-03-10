package com.scaler.productservice.service;

import com.scaler.productservice.dto.FakeStoreResponseDTO;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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