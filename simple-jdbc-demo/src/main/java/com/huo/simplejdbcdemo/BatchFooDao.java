package com.huo.simplejdbcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 批处理操作 jdbc
 */
@Repository
public class BatchFooDao implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        batchUpdate();
    }

    private void batchUpdate() {
        jdbcTemplate.batchUpdate("insert into foo values(?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(2,"b-"+i);
            }

            @Override
            public int getBatchSize() {
                return 2;
            }
        });

    }

}
