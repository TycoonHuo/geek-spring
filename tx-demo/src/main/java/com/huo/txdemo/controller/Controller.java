package com.huo.txdemo.controller;

import com.huo.txdemo.exception.RollbackException;
import com.huo.txdemo.service.FooService;
import com.huo.txdemo.service.impl.FooServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private FooService fooServiceImpl;

    @RequestMapping("/tx")
    public String tx() throws RollbackException {
        fooServiceImpl.insertThenRollback();
        fooServiceImpl.count();
        return "txtxz";
    }
}
