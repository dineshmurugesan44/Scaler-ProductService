package com.scaler.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter

@Setter

//TO USE BASE MODEL BY CATEGORY AND PRODUCT USE THIS ANNOTATION.
@MappedSuperclass
public class BaseModel implements Serializable {
    //TO TELL IT IS A PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement part
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;

    @Column(nullable = false)
    private boolean isDeleted = false;

    private String createdByUserName;

    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();  // Set the createdAt when the entity is created
        this.updatedAt = new Date();  // Set the updatedAt when the entity is created
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();  // Set the updatedAt when the entity is updated
    }
}


