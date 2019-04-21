package com.huo.txdemo.service;

import com.huo.txdemo.exception.RollbackException;

public interface FooService {
    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;
    void count();
}
