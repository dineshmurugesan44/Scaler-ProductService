package com.scaler.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity

public class Category extends BaseModel implements Serializable {
    private String title;


    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Product> products;





}