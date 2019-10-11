package com.atguigu.cache;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {
	@Autowired
	EmployeeMapper employeeMapper;



	@Test
	public void contextLoads() {

		Employee empById = employeeMapper.getEmpById(1);
		System.out.println(empById);

	}

}
