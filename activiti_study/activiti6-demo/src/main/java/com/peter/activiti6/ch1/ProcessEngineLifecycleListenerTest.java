package com.peter.activiti6.ch1;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

public class ProcessEngineLifecycleListenerTest {
	public static void main(String[] args) {
		String processEngineName = "shareniu";
		ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
		
		System.out.println(":::"+processEngine);
		processEngine.close();
		
	}
}
