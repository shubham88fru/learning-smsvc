package org.learning.smsvc.account.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data //getters, setters, constructor etc in a single annotation.
public class AccountsDTO {
    @NotEmpty(message = "Account number can't ne a null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be a 10 digit number")
    private Long accountNumber;

    @NotEmpty(message = "Account type can't be a null or empty.")
    private String accountType;

    @NotEmpty(message = "Branch address can't be a null or empty.")
    private String branchAddress;
}
