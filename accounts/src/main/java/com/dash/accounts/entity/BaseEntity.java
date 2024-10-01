package com.dash.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Column(updatable = false)
    LocalDateTime createdAt;
    @Column(updatable = false)
    String createdBy;
    @Column(insertable = false)
    LocalDateTime updatedAt;
    @Column(insertable = false)
    String updatedBy;

}
