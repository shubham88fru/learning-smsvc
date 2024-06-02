package org.learning.smsvc.account.dto;

import lombok.Data;

@Data //getters, setters, constructor etc in a single annotation.
public class AccountsDTO {
    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
