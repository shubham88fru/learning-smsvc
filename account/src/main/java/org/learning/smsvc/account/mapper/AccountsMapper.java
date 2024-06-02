package org.learning.smsvc.account.mapper;

import org.learning.smsvc.account.dto.AccountsDTO;
import org.learning.smsvc.account.entity.Accounts;

public class AccountsMapper {

    //prepare the data fetched from db to the fields required by client.
    public static AccountsDTO mapToAccountsDTO(Accounts accounts, AccountsDTO accountsDTO) {
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setAccountType(accounts.getAccountType());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        return accountsDTO;
    }

    //prepare the data received in client request to db entity object.
    public static Accounts mapToAccounts(AccountsDTO accountsDTO, Accounts accounts) {
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        return accounts;
    }
}
