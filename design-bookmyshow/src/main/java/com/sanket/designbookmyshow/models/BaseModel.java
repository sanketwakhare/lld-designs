package com.sanket.designbookmyshow.models;

import org.hibernate.annotations.GeneratorType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EnableJpaAuditing
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private Date createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private Date lastModifiedAt;
    @LastModifiedBy
    private String lastModifiedBy;
}
