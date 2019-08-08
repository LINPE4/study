package com.peter.activiti6.ch1;

import org.activiti.engine.*;
import org.activiti.form.api.FormRepositoryService;
import org.activiti.form.api.FormService;

public class ProcessenginesTest {
	public static void main(String[] args) {
		/**
		 * :formEngineFormService:null :::::::formEngineRepositoryService:null
		 * FormService formEngineFormService = processEngine.getFormEngineFormService();
		 * FormRepositoryService formEngineRepositoryService =
		 * processEngine.getFormEngineRepositoryService();
		 * 
		 *  
		 *  目前这两个获取不到。
		 */
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		System.out.println("::::::::流程引擎类：" + processEngine);
		Class<? extends ProcessEngine> class1 = processEngine.getClass();
		System.out.println(":::::::::class:" + class1);
		DynamicBpmnService dynamicBpmnService = processEngine.getDynamicBpmnService();
		FormService formEngineFormService = processEngine.getFormEngineFormService();
		FormRepositoryService formEngineRepositoryService = processEngine.getFormEngineRepositoryService();
		org.activiti.engine.FormService formService = processEngine.getFormService();
		HistoryService historyService = processEngine.getHistoryService();
		IdentityService identityService = processEngine.getIdentityService();
		ManagementService managementService = processEngine.getManagementService();
		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		TaskService taskService = processEngine.getTaskService();
		System.out.println(":::::::dynamicBpmnService:" + dynamicBpmnService);
		System.out.println(":::::::formEngineFormService:" + formEngineFormService);
		System.out.println(":::::::formEngineRepositoryService:" + formEngineRepositoryService);
		System.out.println(":::::::formService:" + formService);
		System.out.println(":::::::historyService:" + historyService);
		System.out.println(":::::::identityService:" + identityService);
		System.out.println(":::::::managementService:" + managementService);
		System.out.println(":::::::processEngineConfiguration:" + processEngineConfiguration);
		System.out.println(":::::::repositoryService:" + repositoryService);
		System.out.println(":::::::runtimeService:" + runtimeService);
		System.out.println(":::::::taskService:" + taskService);

	}
}
