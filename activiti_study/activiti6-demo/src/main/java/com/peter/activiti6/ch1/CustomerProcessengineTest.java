package com.peter.activiti6.ch1;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.junit.Test;

import java.io.InputStream;

public class CustomerProcessengineTest {
	
	
	
	
	@Test
	public void customerProcessengine() {
		ProcessEngineConfiguration processEngineConfigurationImpl=new StandaloneProcessEngineConfiguration();
		String jdbcDriver="com.mysql.jdbc.Driver";
		processEngineConfigurationImpl.setJdbcDriver(jdbcDriver);
		String jdbcUrl="jdbc:mysql://192.168.140.128:3306/activiti6ui?characterEncoding=UTF-8";
		processEngineConfigurationImpl.setJdbcUrl(jdbcUrl);
		
		String jdbcUsername="root";
		processEngineConfigurationImpl.setJdbcUsername(jdbcUsername);
		
		String jdbcPassword="123456";
		processEngineConfigurationImpl.setJdbcPassword(jdbcPassword);
		processEngineConfigurationImpl.setDatabaseSchemaUpdate("true");
		ProcessEngine buildProcessEngine = processEngineConfigurationImpl.buildProcessEngine();
		System.out.println(buildProcessEngine);
//		buildProcessEngine
//				.getRepositoryService()
//				.createDeployment()
//				.addClasspathResource("process/leave_apply.bpmn20.xml")
//				.deploy();
	}


	@Test
	public void customerProcessengine1() {
		InputStream inputStream=CustomerProcessengineTest.class
				.getClassLoader().getResourceAsStream("com/shareniu/activiti6/study_network1/ch1/activiti.cfg.xml");
		ProcessEngineConfiguration createProcessEngineConfigurationFromInputStream = ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(inputStream);
		ProcessEngine buildProcessEngine = createProcessEngineConfigurationFromInputStream.buildProcessEngine();
		System.out.println(buildProcessEngine);
	}
	/**
	 * 错误
	 */
	@Test
	public void customerProcessengine2() {
		InputStream inputStream=CustomerProcessengineTest.class
				.getClassLoader().getResourceAsStream("com/shareniu/activiti6/study_network1/ch1/activiti.cfg2.xml");
		ProcessEngineConfiguration createProcessEngineConfigurationFromInputStream = ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(inputStream);
		ProcessEngine buildProcessEngine = createProcessEngineConfigurationFromInputStream.buildProcessEngine();
		System.out.println(buildProcessEngine);
	}
	@Test
	public void customerProcessengine3() {
		InputStream inputStream=CustomerProcessengineTest.class
				.getClassLoader().getResourceAsStream("com/shareniu/activiti6/study_network1/ch1/activiti.cfg2.xml");
		ProcessEngineConfiguration createProcessEngineConfigurationFromInputStream = ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(inputStream,"processEngineConfiguration1");
		ProcessEngine buildProcessEngine = createProcessEngineConfigurationFromInputStream.buildProcessEngine();
		System.out.println(buildProcessEngine);
	}
	@Test
	public void customerProcessengine4() {
		String resource="com/shareniu/activiti6/study_network1/ch1/activiti.cfg.xml";
		ProcessEngineConfiguration createProcessEngineConfigurationFromInputStream = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource(resource);
		ProcessEngine buildProcessEngine = createProcessEngineConfigurationFromInputStream.buildProcessEngine();
		System.out.println(buildProcessEngine);
	}
	@Test
	public void customerProcessengine5() {
		String resource="com/shareniu/activiti6/study_network1/ch1/activiti.cfg2.xml";
		ProcessEngineConfiguration createProcessEngineConfigurationFromInputStream = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource(resource,"processEngineConfiguration1");
		ProcessEngine buildProcessEngine = createProcessEngineConfigurationFromInputStream.buildProcessEngine();
		System.out.println(buildProcessEngine);
	}
}
