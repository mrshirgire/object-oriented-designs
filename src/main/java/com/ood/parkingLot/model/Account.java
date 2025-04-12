package com.ood.parkingLot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

//@Setter
@Getter
@Builder
public class Account {

    private UUID accountNumber;
    private String accountHolderName;
    private String password;
    private BigDecimal balance;
}
