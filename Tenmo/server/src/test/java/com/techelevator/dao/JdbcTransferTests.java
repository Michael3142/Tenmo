package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.security.Principal;

public class JdbcTransferTests extends BaseDaoTests{

    private static final Transfer TRANSFER1 = new Transfer(3001, 2001,2003,200.00,"Approved");
    private static final Transfer TRANSFER2 = new Transfer(3002, 2002,2002,150.00,"Approved");
    private static final Transfer TRANSFER3 = new Transfer(3003, 2003,2003,200.00,"Approved");


    private Transfer testTransfer;

    private JdbcTransferDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTransferDao(jdbcTemplate);

    }
// testing
//    @Test
//    public void getTransferByIdReturnsCorrectTransfer(){
//        Transfer transfer = sut.getTransferById(3001);
//        assertTransfersMatch(TRANSFER1, transfer);
//    }
//
//    @Test
//    public void transferCreatedisCreated(){
//        String username = "hoi";
//
//        Transfer createdTransfer = sut.createTransfer(testTransfer, username);
//
//        int newId = createdTransfer.getTransferId();
//        Assert.assertTrue(newId > 3000);
//
//        Transfer retrievedTransfer = sut.getTransferById(newId);
//        assertTransfersMatch(createdTransfer, retrievedTransfer);
//
//    }


    private void assertTransfersMatch(Transfer expected, Transfer actual){
        Assert.assertEquals(expected.getTransferId(), actual.getTransferId());
        Assert.assertEquals(expected.getFromAccount(), actual.getFromAccount());
        Assert.assertEquals(expected.getToAccount(), actual.getToAccount());
        Assert.assertEquals(expected.getAmountSending(), actual.getAmountSending());
        Assert.assertEquals(expected.getStatus(), actual.getStatus());
    }


}
