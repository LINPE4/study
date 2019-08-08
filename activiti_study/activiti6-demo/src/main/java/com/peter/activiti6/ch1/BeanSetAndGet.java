package com.peter.activiti6.ch1;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;

import java.util.Map;

public class BeanSetAndGet {
	public static void main(String[] args) {
		String processEngineName = "shareniu";
		ProcessEngine processEngine = ProcessEngines.getProcessEngine(processEngineName);

		ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) processEngine
				.getProcessEngineConfiguration();
		Map<Object, Object> beans = processEngineConfiguration.getBeans();
		//SpringBeanFactoryProxyMap
		System.out.println(beans);
		Object object = beans.get("student");
		Object teacher = beans.get("teacher");
		System.out.println(teacher);
		System.out.println(object);
		if (object instanceof Student) {
			Student student=(Student) object;
			System.out.println(student.getAge());
			System.out.println(student.getName());
		}
	}
}
