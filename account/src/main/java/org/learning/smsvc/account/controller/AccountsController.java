package org.learning.smsvc.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }
}
