package org.learning.smsvc.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass //tell spring data jpa that this class is a super class for all entity classes.
public class BaseEntity {
    //all common columns for each entity.

    @Column(updatable = false) //don't let someone update this field in future. This is a source of truth.
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    private String updatedBy;
}
