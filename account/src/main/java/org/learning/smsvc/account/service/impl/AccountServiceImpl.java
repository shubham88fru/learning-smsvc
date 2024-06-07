package org.learning.smsvc.account.service.impl;

import lombok.AllArgsConstructor;
import org.learning.smsvc.account.constants.AccountsConstants;
import org.learning.smsvc.account.dto.AccountsDTO;
import org.learning.smsvc.account.dto.CustomerDTO;
import org.learning.smsvc.account.entity.Accounts;
import org.learning.smsvc.account.entity.Customer;
import org.learning.smsvc.account.exception.CustomerAlreadyExistException;
import org.learning.smsvc.account.exception.ResourceNotFoundException;
import org.learning.smsvc.account.mapper.AccountsMapper;
import org.learning.smsvc.account.mapper.CustomerMapper;
import org.learning.smsvc.account.repository.IAccountsRepository;
import org.learning.smsvc.account.repository.ICustomerRepository;
import org.learning.smsvc.account.service.IAccountsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service @AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private IAccountsRepository accountsRepository;
    private ICustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer =
                customerRepository.findByMobileNumber(customerDTO.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException(
                    "Customer already registered with give mobile no."
            );
        }

        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts account = new Accounts();
        account.setCustomerId(customer.getCustomerId());
        long randAccNumber = 1000000000L + new Random().nextInt(900000000);
        account.setAccountNumber(randAccNumber);
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");
        return account;
    }


    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));

        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        if (accountsDTO != null) {
            Accounts accounts = accountsRepository
                .findById(accountsDTO.getAccountNumber())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Accounts", "AccountNumber", accountsDTO.getAccountNumber().toString())
                );

            AccountsMapper.mapToAccounts(accountsDTO, accounts);
            accounts = accountsRepository.save(accounts);

            long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", String.valueOf(customerId))
            );
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }
}
