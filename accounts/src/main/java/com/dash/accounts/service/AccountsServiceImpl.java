package com.dash.accounts.service;

import com.dash.accounts.Exception.AccountsAlreadyExistsException;
import com.dash.accounts.constants.AccountsConstants;
import com.dash.accounts.dto.CustomerDto;
import com.dash.accounts.entity.Accounts;
import com.dash.accounts.entity.Customer;
import com.dash.accounts.mapper.CustomerMapper;
import com.dash.accounts.repository.IAccountsRepository;
import com.dash.accounts.repository.ICustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountService {
    private IAccountsRepository accountsRepository;
    private ICustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");

        Optional<Customer> customerOptional =customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (customerOptional.isPresent()) {
            throw new AccountsAlreadyExistsException("Account already present with the mobile number : "+customerDto.getMobileNumber());
        }else {
            Customer savedCustomer = customerRepository.save(customer);
            Long savedCustomerID = savedCustomer.getCustomerId();
            Accounts newCustomerAccount = createCustomerAccountWithId(savedCustomerID);
            System.out.println("The new account number for the customer with the id : " + savedCustomerID + " is  : " + newCustomerAccount);
            newCustomerAccount.setCreatedAt(LocalDateTime.now());
            newCustomerAccount.setCreatedBy("Anonymous");
            accountsRepository.save(newCustomerAccount);
        }
    }

    private Accounts createCustomerAccountWithId(Long customerId) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomer_id(customerId);
        newAccount.setAccount_type(AccountsConstants.SAVINGS);
        newAccount.setAccount_number(generateAccountNumber());
        newAccount.setBranch_address(AccountsConstants.ADDRESS);

        return newAccount;
    }

    private int generateAccountNumber() {
        Random random = new Random();
        int tenDigitAccountNumber = 1000000000 + random.nextInt(900000000);
        return tenDigitAccountNumber;
    }
}
