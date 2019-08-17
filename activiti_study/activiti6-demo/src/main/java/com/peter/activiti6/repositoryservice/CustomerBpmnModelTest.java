package com.peter.activiti6.repositoryservice;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 原型可以对照customerBpmnModel.bpmn20.xml
 */
public class CustomerBpmnModelTest {
	ProcessEngine defaultProcessEngine;
	RepositoryService repositoryService;

	@Before
	public void init() {
		defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
		repositoryService = defaultProcessEngine.getRepositoryService();
		System.out.println(repositoryService);
	}

	@Test
	public void genarateBpmnModel() throws UnsupportedEncodingException {
		BpmnModel bpmnModel = new BpmnModel();
		// <process id="myProcess" name="My process" isExecutable="true">
		Process process = new Process();
		process.setId("myProcess");
		process.setName("My process");
		process.setExecutable(true);
		// <startEvent id="startevent1" name="Start"></startEvent>
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startevent1");
		startEvent.setName("Start");
		process.addFlowElement(startEvent);

		/**
		 * <userTask id="usertask1" name="提交申请"></userTask>
		 * <userTask id="usertask2" name="【部门经理审批】"></userTask>
		 * <userTask id="usertask3" name="【总经理审批】"></userTask>
		 * 
		 */
		UserTask usertask1 = generateUserTask("usertask1", "提交申请");
		UserTask usertask2 = generateUserTask("usertask2", "部门经理审批】");
		UserTask usertask3 = generateUserTask("usertask3", "【总经理审批】");
		process.addFlowElement(usertask1);
		process.addFlowElement(usertask2);
		process.addFlowElement(usertask3);

		/**
		 * <exclusiveGateway id="exclusivegateway1" name="Exclusive
		 * Gateway"></exclusiveGateway>
		 */

		ExclusiveGateway generateExclusiveGateway = generateExclusiveGateway("exclusivegateway1", "排他网管");
		process.addFlowElement(generateExclusiveGateway);

		/**
		 * <endEvent id="endevent1" name="End"></endEvent>
		 * <endEvent id="endevent2" name="End"></endEvent>
		 */
		EndEvent endevent1 = generateEndEvent("endevent1", "End");
		EndEvent endevent2 = generateEndEvent("endevent2", "End");
		process.addFlowElement(endevent1);
		process.addFlowElement(endevent2);
		/**
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		/**
		 * <sequenceFlow id="flow1" sourceRef="startevent1" targetRef=
		 * "usertask1"></sequenceFlow>
		 */
		SequenceFlow flow1 = generateSequence("flow1", null, "startevent1", "usertask1", null, process);
		process.addFlowElement(flow1);
		/**
		 * <sequenceFlow id="flow2" sourceRef="usertask1" targetRef=
		 * "exclusivegateway1"></sequenceFlow>
		 */
		SequenceFlow flow2 = generateSequence("flow2", null, "usertask1", "exclusivegateway1", null, process);
		process.addFlowElement(flow2);
		/**
		 * <sequenceFlow id="flow3" name="小于三天" sourceRef="exclusivegateway1" targetRef=
		 * "usertask2"> <conditionExpression xsi:type=
		 * "tFormalExpression"><![CDATA[${day<3}]]></conditionExpression>
		 * </sequenceFlow>
		 */
		SequenceFlow flow3 = generateSequence("flow3", "小于三天", "exclusivegateway1", "usertask2", "${day<3}", process);
		process.addFlowElement(flow3);
		/**
		 * <sequenceFlow id="flow4" name="大于三天" sourceRef="exclusivegateway1" targetRef=
		 * "usertask3"> <conditionExpression xsi:type=
		 * "tFormalExpression"><![CDATA[${day>3}]]></conditionExpression>
		 * </sequenceFlow>
		 */
		SequenceFlow flow4 = generateSequence("flow4", "大于三天", "exclusivegateway1", "usertask3", "${day>3}", process);
		process.addFlowElement(flow4);
		/**
		 * <sequenceFlow id="flow6" sourceRef="usertask2" targetRef=
		 * "endevent2"></sequenceFlow>
		 */
		SequenceFlow flow5 = generateSequence("flow5", null, "usertask2", "endevent2", null, process);
		process.addFlowElement(flow5);
		
		List<SequenceFlow> outgoingFlows = new ArrayList<SequenceFlow>();
		outgoingFlows.add(flow3);
		outgoingFlows.add(flow4);
		generateExclusiveGateway.setOutgoingFlows(outgoingFlows);
		bpmnModel.addProcess(process);
		BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();

		byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);
		String string = new String(convertToXML, "UTF-8");
		System.out.println(string);
		
		String resourceName = "customer.bpmn";
		Deployment deploy = repositoryService.createDeployment().addBpmnModel(resourceName, bpmnModel).deploy();
		System.out.println(deploy);
	}

	public SequenceFlow generateSequence(String id, String name, String sourceRef, String targetRef,
			String conditionExpression, Process process) {
		SequenceFlow sequenceFlow = new SequenceFlow();
		sequenceFlow.setId(id);
		sequenceFlow.setName(name == null ? "" : name);
		sequenceFlow.setSourceFlowElement(process.getFlowElement(sourceRef));
		sequenceFlow.setTargetFlowElement(process.getFlowElement(targetRef));
		sequenceFlow.setSourceRef(sourceRef);
		sequenceFlow.setTargetRef(targetRef);

		sequenceFlow.setConditionExpression(conditionExpression == null ? "" : conditionExpression);
		return sequenceFlow;
	}

	public EndEvent generateEndEvent(String id, String name) {
		EndEvent endEvent = new EndEvent();
		endEvent.setId(id);
		endEvent.setName(name);
		return endEvent;

	}

	public ExclusiveGateway generateExclusiveGateway(String id, String name) {
		ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
		exclusiveGateway.setId(id);
		exclusiveGateway.setName(name);
		return exclusiveGateway;
	}

	public UserTask generateUserTask(String id, String name) {
		UserTask userTask = new UserTask();
		userTask.setId(id);
		userTask.setName(name);
		return userTask;
	}
}
