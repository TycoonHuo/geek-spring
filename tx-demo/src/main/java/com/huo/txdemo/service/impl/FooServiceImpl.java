package com.huo.txdemo.service.impl;

import com.huo.txdemo.exception.RollbackException;
import com.huo.txdemo.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("insert into foo values ('张三')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("insert into foo values ('张三1')");
        count();
        throw new RollbackException("该回滚了");
    }

    @Override
//    @Transactional(rollbackFor = RollbackException.class)
    public void invokeInsertThenRollback() throws RollbackException {
                insertThenRollback();
    }

    @Override
    public void count() {
        log.info("Count:{}", jdbcTemplate.queryForObject("select count(1)from foo", Long.class));
    }
}
