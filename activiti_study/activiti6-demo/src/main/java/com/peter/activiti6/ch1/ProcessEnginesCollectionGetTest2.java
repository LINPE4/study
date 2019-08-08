package com.peter.activiti6.ch1;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineInfo;
import org.activiti.engine.ProcessEngines;

import java.util.List;
import java.util.Map;

public class ProcessEnginesCollectionGetTest2 {
	public static void main(String[] args) {
		
		//默认引擎的构造在activiti.cfg.xml中配置 <property name="processEngineName" value="shareniu"></property>
		String processEngineName = "shareniu";
		ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);
		System.out.println("::::::::::流程引擎::::::" + processEngine);
		Map<String, ProcessEngine> processEngines = ProcessEngines.getProcessEngines();
		ProcessEngine processEngine2 = processEngines.get("default");
		System.out.println("::::::::::流程引擎::::::" + processEngine2);
		processEngine2 = processEngines.get("default1");
		System.out.println("::::::::::流程引擎::::::" + processEngine2);

		
		ProcessEngineInfo processEngineInfo = ProcessEngines.getProcessEngineInfo(processEngineName);
		System.out.println(":::::::::流程引擎信息类:::::::" + processEngineInfo.getException());
		System.out.println(":::::::::流程引擎信息类:::::::" + processEngineInfo.getName());
		System.out.println(":::::::::流程引擎信息类:::::::" + processEngineInfo.getResourceUrl());

		System.out.println("#########################");
		List<ProcessEngineInfo> processEngineInfos = ProcessEngines.getProcessEngineInfos();
		for (ProcessEngineInfo processEngineInfo2 : processEngineInfos) {
			System.out.println(":::::::::流程引擎信息类:::::::" + processEngineInfo2.getException());
			System.out.println(":::::::::流程引擎信息类:::::::" + processEngineInfo2.getName());
			System.out.println(":::::::::流程引擎信息类:::::::" + processEngineInfo2.getResourceUrl());
		}
		
		
		processEngine.close();
	}
}
