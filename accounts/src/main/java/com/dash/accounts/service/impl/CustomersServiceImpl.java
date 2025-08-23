package com.dash.accounts.service.impl;


import com.dash.accounts.Exception.ResourceNotFoundException;
import com.dash.accounts.dto.AccountsDto;
import com.dash.accounts.dto.CardsDto;
import com.dash.accounts.dto.CustomerDetailsDto;
import com.dash.accounts.dto.LoansDto;
import com.dash.accounts.entity.Accounts;
import com.dash.accounts.entity.Customer;
import com.dash.accounts.mapper.AccountsMapper;
import com.dash.accounts.mapper.CustomerMapper;
import com.dash.accounts.repository.IAccountsRepository;
import com.dash.accounts.repository.ICustomerRepository;
import com.dash.accounts.service.ICustomersService;
import com.dash.accounts.service.client.CardsFeignClient;
import com.dash.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {
    private IAccountsRepository accountsRepository;
    private ICustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        if (null!= loansDtoResponseEntity){
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        if (null!= cardsDtoResponseEntity){
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;

    }
}
