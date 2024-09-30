package com.dash.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Accounts extends BaseEntity {

    private Long customer_id;
    @Id
    private int account_number;
    private String account_type;
    private String branch_address;
}
