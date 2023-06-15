package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate template;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }
        @Override
    public Transfer getTransferById(int transferId){
        Transfer transfer = new Transfer();
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?;";
        SqlRowSet results = template.queryForRowSet(sql, transferId);
        if(results.next()){
            transfer.setTransferId(results.getInt("transfer_id"));
            transfer.setFromAccount(results.getInt("from_account"));
            transfer.setToAccount(results.getInt("to_account"));
            transfer.setAmountSending(results.getDouble("amount_sending"));
            transfer.setStatus(results.getString("status"));


        }
        return transfer;
    }

    @Override
    public void subtractAmountFromSender(Transfer transfer) {
        String sql = "UPDATE account SET balance = balance - ?  WHERE account_id = ?";
        template.update(sql, transfer.getAmountSending(), transfer.getFromAccount());
    }



    @Override
    public void addAmountToSender(Transfer transfer) {
        String sql = "UPDATE account SET balance = balance + ?  WHERE account_id = ?";
        template.update(sql,transfer.getAmountSending(), transfer.getToAccount());

    }


    @Override
    public int findIdByUsername(String username) {
        String sql = "SELECT user_id FROM tenmo_user WHERE username ILIKE ?;";
        Integer id = template.queryForObject(sql, Integer.class, username);
        if (id != null) {
            return id;
        } else {
            return -1;
        }
    }



    @Override
    public double getBalance(String username) {
        double results = 0.0;
        String sql = "SELECT balance from account WHERE user_id = ?";
        SqlRowSet queryResults = template.queryForRowSet(sql, findIdByUsername(username));
        if (queryResults.next()) {
            results = queryResults.getDouble("balance");
        }
        return results;
    }


    @Override
    public Transfer createTransfer(Transfer transfer, String username) {
        String sql = "INSERT INTO transfer (from_account, to_account, amount_sending, status)" +
                "VALUES(?,?,?,?) RETURNING transfer_id" ;

        if(transfer.getFromAccount() == transfer.getToAccount() || transfer.getAmountSending() <= 0 || getBalance(username) < transfer.getAmountSending()){
            System.out.println("Error with Transfer. Transfer not created.");
            return null;

        } else{
            int newTransferId = template.queryForObject(sql, int.class, transfer.getFromAccount(), transfer.getToAccount(), transfer.getAmountSending(), transfer.getStatus());
            Transfer newTransfer = getTransferById(newTransferId);
            newTransfer.setStatus("Approved");
            subtractAmountFromSender(newTransfer);
            addAmountToSender(newTransfer);
            System.out.println("Creating new transfer.");
            return newTransfer;
        }
    }
}
