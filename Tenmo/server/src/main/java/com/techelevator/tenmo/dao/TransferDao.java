package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.security.Principal;
import java.util.List;

public interface TransferDao {

    Transfer createTransfer(Transfer transfer, String username);

    Transfer getTransferById(int transferId);

    void subtractAmountFromSender(Transfer transfer);

    void addAmountToSender(Transfer transfer);


    double getBalance(String username);

    int findIdByUsername(String username);
}
