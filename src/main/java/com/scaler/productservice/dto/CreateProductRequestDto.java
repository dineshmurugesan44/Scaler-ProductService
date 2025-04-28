package com.scaler.productservice.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateProductRequestDto {
    private String title;
    private String imageURL;
    private String description;


    private CategoryRequestDTO category;
    private String createdByUserName;
}
