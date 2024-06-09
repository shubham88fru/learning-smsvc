package org.learning.smsvc.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    //validation - this field is mandatory. Client can't send a null value.
    @NotEmpty(message = "Name cannot be a null or empty.")
    @Size(min=5, max=30, message = "The length of customer name should be between 5 and 30.")
    private String name;

    @NotEmpty(message = "Email cannot be a null or empty.")
    @Email(message= "Email address should be a valid value.")
    private String email;

    //numeric value, with exactly 10 digits
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
    private String mobileNumber;

    private AccountsDTO accountsDTO;
}
