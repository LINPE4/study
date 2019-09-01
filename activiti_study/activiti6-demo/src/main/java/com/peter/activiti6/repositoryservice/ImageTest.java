package com.peter.activiti6.repositoryservice;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageTest {
	ProcessEngine defaultProcessEngine;
	RepositoryService repositoryService;

	@Before
	public void init() {
		defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
		repositoryService = defaultProcessEngine.getRepositoryService();
		System.out.println(repositoryService);
	}

	@Test
	public void viewImage() throws IOException {
		String deploymentId = "12501";
		List<String> deploymentResourceNames = repositoryService.getDeploymentResourceNames(deploymentId);
		System.out.println(deploymentResourceNames);
		String imageName = null;
		for (String name : deploymentResourceNames) {
			if (name.indexOf(".png") >= 0) {
				imageName = name;
			}
		}
		System.out.println("imageName:" + imageName);
		if (imageName != null) {
			InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, imageName);
			FileUtils.copyInputStreamToFile(inputStream, new File("C://test//" + "1.png"));
		}
	}

	@Test
	public void generateDiagram() throws IOException {

		ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) defaultProcessEngine
				.getProcessEngineConfiguration();
		ProcessDiagramGenerator processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
		System.out.println("processDiagramGenerator::::" + processDiagramGenerator);

		String processDefinitionId = "myProcess:1:12504";
		List<String> highLightedActivities = new ArrayList<String>();
		highLightedActivities.add("usertask1");

		List<String> highLightedFlows = new ArrayList<String>();
		highLightedFlows.add("flow1");
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		InputStream inputStream = processDiagramGenerator.generateDiagram(bpmnModel, "PNG", highLightedActivities,
				highLightedFlows);
		FileUtils.copyToFile(inputStream, new File("C://test//" + "1.png"));

	}

}
