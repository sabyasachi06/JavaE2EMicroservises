package com.dash.accounts.repository;

import com.dash.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountsRepository extends JpaRepository<Customer,Long> {
}
