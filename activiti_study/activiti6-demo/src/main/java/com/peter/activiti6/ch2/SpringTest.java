package com.peter.activiti6.ch2;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

public class SpringTest {
	public static void main(String[] args) {
		ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
		System.out.println(defaultProcessEngine);
		RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
		System.out.println(repositoryService);
		HistoryService historyService = defaultProcessEngine.getHistoryService();
		System.out.println(historyService);
	}
}
