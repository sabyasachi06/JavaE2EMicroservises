package com.dash.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Column(updatable = false)
    Date createdAt;
    @Column(updatable = false)
    String createdBy;
    @Column(insertable = false)
    Date updatedAt;
    @Column(insertable = false)
    String updatedBy;
}
