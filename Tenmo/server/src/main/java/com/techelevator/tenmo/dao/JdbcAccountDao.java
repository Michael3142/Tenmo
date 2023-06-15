package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;

@Component
    public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate template;

    public JdbcAccountDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public double getBalance(Principal principal) {
        double results = 0;
        String sql = "SELECT balance from account";
        SqlRowSet queryResults = template.queryForRowSet(sql);
        if (queryResults.next()) {
            results = queryResults.getDouble("balance");
        }
        return results;
    }
}
