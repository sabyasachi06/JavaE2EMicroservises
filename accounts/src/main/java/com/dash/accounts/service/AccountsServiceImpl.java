package com.dash.accounts.service;

import com.dash.accounts.Exception.AccountsAlreadyExistsException;
import com.dash.accounts.Exception.ResourceNotFoundException;
import com.dash.accounts.constants.AccountsConstants;
import com.dash.accounts.dto.AccountsDto;
import com.dash.accounts.dto.CustomerDto;
import com.dash.accounts.entity.Accounts;
import com.dash.accounts.entity.Customer;
import com.dash.accounts.mapper.AccountsMapper;
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

        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (customerOptional.isPresent()) {
            throw new AccountsAlreadyExistsException("Account already present with the mobile number : " + customerDto.getMobileNumber());
        } else {
            Customer savedCustomer = customerRepository.save(customer);
            Long savedCustomerID = savedCustomer.getCustomerId();
            Accounts newCustomerAccount = createCustomerAccountWithId(savedCustomerID);
            System.out.println("The new account number for the customer with the id : " + savedCustomerID + " is  : " + newCustomerAccount);
            accountsRepository.save(newCustomerAccount);
        }
    }

    private Accounts createCustomerAccountWithId(Long customerId) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customerId);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setAccountNumber(generateAccountNumber());
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        return newAccount;
    }

    private long generateAccountNumber() {
        Random random = new Random();
        return 1000000000 + random.nextLong(900000000);
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
