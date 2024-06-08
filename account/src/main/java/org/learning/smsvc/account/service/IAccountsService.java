package org.learning.smsvc.account.service;

import org.learning.smsvc.account.dto.CustomerDTO;

public interface IAccountsService {

    /**
     *
     * @param customerDTO
     */
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDTO customerDTO);

    boolean deleteAccount(String mobileNumber);
}
