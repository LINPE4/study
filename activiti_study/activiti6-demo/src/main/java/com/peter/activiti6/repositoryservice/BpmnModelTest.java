package com.peter.activiti6.repositoryservice;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.converter.util.InputStreamProvider;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.util.io.InputStreamSource;
import org.activiti.engine.repository.DiagramElement;
import org.activiti.engine.repository.DiagramLayout;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BpmnModelTest {
	ProcessEngine defaultProcessEngine;
	RepositoryService repositoryService;

	@Before
	public void init() {
		defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
		repositoryService = defaultProcessEngine.getRepositoryService();
		System.out.println(repositoryService);
	}

	@Test
	public void testConverXMLToBpmnModel() throws UnsupportedEncodingException {
		InputStream inputStream = BpmnModelTest.class.getClassLoader()
				.getResourceAsStream("process/MyProcess.bpmn");

		BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();

		InputStreamProvider inputStreamProvider = new InputStreamSource(inputStream);
		BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(inputStreamProvider, true, false, "UTF-8");
		
		Process process = bpmnModel.getProcesses().get(0);
		System.out.println(bpmnModel);
		System.out.println(process);
		
		
		
		byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);
		String bpmn20Xml=new String(convertToXML,"UTF-8");
		System.out.println(bpmn20Xml);
		
	}
	
	@Test
	public void getBpmnModel() throws UnsupportedEncodingException {
		String processDefinitionId="myProcess:2:15004";
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		System.out.println(bpmnModel);
		
	}
	
	
	
	
	@Test
	public void getProcessDiagramLayout() throws UnsupportedEncodingException {
		String processDefinitionId="myProcess:2:15004";
		DiagramLayout processDiagramLayout = repositoryService.getProcessDiagramLayout(processDefinitionId);
		System.out.println("processDiagramLayout.getElements():");
		System.out.println(processDiagramLayout.getElements());
		Map<String, DiagramElement> elements = processDiagramLayout.getElements();

		System.out.println("processDiagramLayout.getElements():");
		Set<Entry<String,DiagramElement>> entrySet = elements.entrySet();
		for (Entry<String, DiagramElement> entry : entrySet) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
	@Test
	public void findFlowElementsOfType() throws UnsupportedEncodingException {
		String processDefinitionId="myProcess:2:15004";
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		Process process = bpmnModel.getProcesses().get(0);
		List<SequenceFlow> sequenceFlows = process.findFlowElementsOfType(SequenceFlow.class);
		for (SequenceFlow sequenceFlow : sequenceFlows) {
			System.out.println("::::::::::"+sequenceFlow.getId()+":::"+sequenceFlow.getSourceRef());
			System.out.println("::::::::::"+sequenceFlow.getTargetRef());
			
		}
		
		
		
		List<UserTask> userTasks = process.findFlowElementsOfType(UserTask.class);
		for (UserTask userTask : userTasks) {
			System.out.println(userTask.getId());
			System.out.println(userTask.getAssignee());
			List<SequenceFlow> outgoingFlows = userTask.getOutgoingFlows();
			System.out.println("任务节点的出线");
			System.out.println(outgoingFlows);
			for (SequenceFlow sequenceFlow : outgoingFlows) {
				FlowElement targetFlowElement = sequenceFlow.getTargetFlowElement();
				System.out.println("任务节点的下一个节点："+targetFlowElement.getId());
			}
			List<SequenceFlow> incomingFlows = userTask.getIncomingFlows();
			System.out.println("任务节点的入线");
			System.out.println(incomingFlows);
		}
	}
}
