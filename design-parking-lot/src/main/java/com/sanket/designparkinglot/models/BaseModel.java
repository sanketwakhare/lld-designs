package com.sanket.designparkinglot.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreatedDate
    Date createdAt;

    @CreatedBy
    Date createdBy;

    @LastModifiedDate
    Date lastModifiedAt;

    @LastModifiedBy
    String lastModifiedBy;
}
