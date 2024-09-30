package com.dash.accounts.service;

import com.dash.accounts.dto.CustomerDto;
import com.dash.accounts.repository.IAccountsRepository;
import com.dash.accounts.repository.ICustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountService{
    private IAccountsRepository accountsRepository;
    private ICustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
