package com.scaler.productservice.repositary;

import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    //JPA QUERY

//select * from product where id = id;
    Optional<Product> findById(Integer id);


    //
    Optional<Product> findByCategory(Category c);

    //select * from product where id = id and category = c;
    Optional<Product> findByIdAndCategory(Integer id, Category c);

    Optional<List<Product>> findAllByCategory(Category c);

    void deleteById(Integer id);
    void deleteAllByCategory(Category c);

}
