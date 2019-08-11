package com.peter.activiti6.core;

import org.activiti.engine.cfg.AbstractProcessEngineConfigurator;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;

public class ShareniuProcessEngineConfigurator2 extends AbstractProcessEngineConfigurator {
	
	
	@Override
	public void beforeInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		System.out.println("ShareniuProcessEngineConfigurator2:::::::"+processEngineConfiguration);
	}
	
	@Override
	public void configure(ProcessEngineConfigurationImpl processEngineConfiguration) {
		System.out.println("ShareniuProcessEngineConfigurator2:::::::"+processEngineConfiguration);
	}
}
