package com.ood.carrental;

import com.ood.carrental.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {

    private String accountNumber;
    private double balance;
    private String currency;
    private String accountType;
    private AccountStatus accountStatus;
    private String username;
    private String password;

}
