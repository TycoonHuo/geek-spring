package com.huo.txdemo;

import com.huo.txdemo.exception.RollbackException;
import com.huo.txdemo.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@Slf4j
public class TxDemoApplication implements CommandLineRunner {

    @Autowired
    private FooService fooServiceImpl;

    public static void main(String[] args) {
        SpringApplication.run(TxDemoApplication.class, args);
    }

    @Override
    public void run(String... args)  {
//        fooServiceImpl.insertRecord();
//        fooServiceImpl.count();
        log.info("=============================");
        try {
            fooServiceImpl.insertThenRollback();
        } catch (RollbackException e) {
            fooServiceImpl.count();
        }
        log.info("=============================");

        try {
            fooServiceImpl.invokeInsertThenRollback();
        } catch (RollbackException e) {
            fooServiceImpl.count();
        }
    }
}
