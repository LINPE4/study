package com.enjoy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "事务示例", description = "事务示例")
@RestController
public class TransController {
    @Resource
    private JdbcTemplate jdbcTemplate;// ---- 会不会有变化

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    private String sql_james = "INSERT INTO bank_a(money,user_name)VALUES (?,?)";
    private String sql_peter = "INSERT INTO bank_b(money,user_name)VALUES (?,?)";

    @ApiOperation(value="1.spring声明式事务")
    @RequestMapping(value = "/spring/transfer/declare", method = RequestMethod.GET)
    @Transactional
    public String declareTransfer(int money) {
        int resultJames = jdbcTemplate.update(sql_james,-money,"james");
        int resultPeter = jdbcTemplate.update(sql_peter,money,"peter");
        if (money > 20){
            throw new RuntimeException("money too large");
        }
        return resultJames+"-"+resultPeter;
    }

	@ApiOperation(value="2.spring编程式事务")
    @RequestMapping(value = "/spring/transfer/code", method = RequestMethod.GET)
    public String codeTransfer(final int money) {
        // 通过transactionTemplate进行事务的管理
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                int resultJames = jdbcTemplate.update(sql_james,-money,"james");
                int resultPeter = jdbcTemplate.update(sql_peter,money,"peter");
                if (money > 20){
                    throw new RuntimeException("money too large");
                }
            }
        });
        return "success";
    }

    @ApiOperation(value="3.java编程式事务")
    @RequestMapping(value = "/java/transfer/code", method = RequestMethod.GET)
    public String trans(final int money) throws InterruptedException {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {//建副本
            int resultJames = jdbcTemplate.update(sql_james,-money,"james");
            int resultPeter = jdbcTemplate.update(sql_peter,money,"peter");
            if (money > 20){
                throw new RuntimeException("money too large");
            }
        } catch (DataAccessException ex) {
            transactionManager.rollback(status); // 也可以執行status.setRollbackOnly();
            throw ex;
        }

        transactionManager.commit(status);
        return "success";
    }

}