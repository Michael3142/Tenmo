package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.security.Principal;

public interface AccountDao {

    double getBalance(Principal principal);



}
