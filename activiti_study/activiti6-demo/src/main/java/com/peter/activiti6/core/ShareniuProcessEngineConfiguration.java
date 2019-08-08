package com.peter.activiti6.core;

import org.activiti.engine.impl.cfg.SpringBeanFactoryProxyMap;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ShareniuProcessEngineConfiguration extends StandaloneProcessEngineConfiguration {
	@Override
	public void initBeans() {
	Map<Object, Object> beanTemps=	new HashMap<Object, Object>();
		if (beans!=null) {
			SpringBeanFactoryProxyMap  map=(SpringBeanFactoryProxyMap) beans;
			
			Object object = map.get("student");
			beanTemps.put("student", object);
		}
		beans =beanTemps;
	}
	
}
