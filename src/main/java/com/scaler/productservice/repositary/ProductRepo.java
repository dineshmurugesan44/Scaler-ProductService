package com.scaler.productservice.repositary;

import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.repositary.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    /**
     *  i will write a hql query
     *
     *  USecase: GetProductNameByTitle
     */

    @Query("select p from Product p where p.title = :title")
    ProductProjection getProductNameByTitle(@Param("title") String title);

    @Query("select p from Product p where p.title = :title and p.id = :id")
    ProductProjection getProductByTitleAndById(@Param("title") String title, @Param("id") Integer id );

    @Query("select p.title, p.description from Product p where p.id>10")
    List<ProductProjection> getProductsByIdGreaterThanIdValue(@Param("id") Integer id);




}
