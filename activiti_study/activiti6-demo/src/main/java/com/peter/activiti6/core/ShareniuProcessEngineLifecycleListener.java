package com.peter.activiti6.core;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineLifecycleListener;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;

public class ShareniuProcessEngineLifecycleListener implements ProcessEngineLifecycleListener {

	public void onProcessEngineBuilt(ProcessEngine processEngine) {
		System.out.println("流程引擎构造OK");

		ProcessEngineConfigurationImpl configuration = (ProcessEngineConfigurationImpl) processEngine
				.getProcessEngineConfiguration();
		// protected String activityFontName = "Arial";
		//  protected String labelFontName = "Arial";
		//  protected String annotationFontName = "Arial";
		String activityFontName = configuration.getActivityFontName();
		if (activityFontName.equals("Arial")) {
			//throw new Error("字体不能是Arial");
			
			//System.exit(-1);
		}
	}

	public void onProcessEngineClosed(ProcessEngine processEngine) {
		System.out.println("流程引擎关闭");
	}

}
