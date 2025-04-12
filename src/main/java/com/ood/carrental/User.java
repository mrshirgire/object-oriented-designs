package com.ood.carrental;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class User {

    private String  username;
    Location location;
    private String firstName;
    private String lastName;
    private String accountId;

}
