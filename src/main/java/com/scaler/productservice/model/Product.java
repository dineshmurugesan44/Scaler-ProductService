package com.scaler.productservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//use annotaion to coonect model wit our system database rows.

@Entity


public class Product extends BaseModel {
    private String title;
    private String description;
    private String imageURL;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Category category;
}
