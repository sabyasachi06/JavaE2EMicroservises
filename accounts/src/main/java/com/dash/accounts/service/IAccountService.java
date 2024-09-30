package com.dash.accounts.service;

import com.dash.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     *This method is used to create a customer account
     * It takes an input of type customerDto
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);
}
