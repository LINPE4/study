package com.enjoy;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagerServer
public class TmManager {

    public static void main(String[] args) {
        SpringApplication.run(TmManager.class, args);
    }

}
