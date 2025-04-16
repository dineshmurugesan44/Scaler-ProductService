package com.scaler.productservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter

@Setter

//TO USE BASE MODEL BY CATEGORY AND PRODUCT USE THIS ANNOTATION.
@MappedSuperclass
public class BaseModel {
    //TO TELL IT IS A PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //AutoIncrement part
    private Integer id;

    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;
    private String createdByUserName;


}
