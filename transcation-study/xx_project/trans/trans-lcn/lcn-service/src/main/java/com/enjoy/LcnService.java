package com.enjoy;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDistributedTransaction
public class LcnService {

	public static void main(String[] args) {
		SpringApplication.run(LcnService.class, args);
	}

}
