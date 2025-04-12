package com.scaler.productservice.repositary;

import com.scaler.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//jpa gets data type of model and data typ of id.
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    //JPA QUERY.

    Optional<Category> findById(Integer id);

    //JPA QUERY.
    Optional<Category> findByTitle(String title);
}
