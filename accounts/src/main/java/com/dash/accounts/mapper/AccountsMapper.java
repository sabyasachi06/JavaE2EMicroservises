package com.dash.accounts.mapper;

import com.dash.accounts.dto.AccountsDto;
import com.dash.accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto){
        accountsDto.setAccount_number(accounts.getAccountNumber());
        accountsDto.setAccount_type(accounts.getAccountType());
        accountsDto.setBranch_address(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts){
        accounts.setAccountNumber(accountsDto.getAccount_number());
        accounts.setAccountType(accountsDto.getAccount_type());
        accounts.setBranchAddress(accountsDto.getBranch_address());
        return accounts;
    }
}
