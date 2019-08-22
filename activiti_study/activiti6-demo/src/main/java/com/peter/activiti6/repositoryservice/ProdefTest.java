package com.peter.activiti6.repositoryservice;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProdefTest {
	ProcessEngine defaultProcessEngine;
	RepositoryService repositoryService;

	@Before
	public void init() {
		defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
		repositoryService = defaultProcessEngine.getRepositoryService();
		System.out.println(repositoryService);
	}

	@Test
	public void queryProcessDefinition() {
		List<ProcessDefinition> list = repositoryService
		.createProcessDefinitionQuery()
		.orderByProcessDefinitionVersion()
		.desc()
		.list();
		for (ProcessDefinition processDefinition : list) {
			System.out.println(processDefinition.getId());
			System.out.println(processDefinition.getName());
			System.out.println(processDefinition.getKey());
			System.out.println(processDefinition.getVersion());
		}
	}
	@Test
	public void deleteDeployment() {
	String deploymentId="47501";
	repositoryService.deleteDeployment(deploymentId);
	}
	@Test
	public void createNativeDeploymentQuery() {
		List<Deployment> list = repositoryService.createNativeDeploymentQuery()
		.sql("select * from act_re_deployment ")
		.list();
		for (Deployment deployment : list) {
			System.out.println(deployment.getId());
		}
	}

}
