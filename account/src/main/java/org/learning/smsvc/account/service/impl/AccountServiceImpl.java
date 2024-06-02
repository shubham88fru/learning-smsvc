package org.learning.smsvc.account.service.impl;

import lombok.AllArgsConstructor;
import org.learning.smsvc.account.constants.AccountsConstants;
import org.learning.smsvc.account.dto.CustomerDTO;
import org.learning.smsvc.account.entity.Accounts;
import org.learning.smsvc.account.entity.Customer;
import org.learning.smsvc.account.exception.CustomerAlreadyExistException;
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
}
