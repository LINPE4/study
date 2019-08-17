package com.peter.activiti6.core;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.UserTask;
import org.activiti.validation.ValidationError;
import org.activiti.validation.validator.ProcessLevelValidator;

import java.util.List;

public class ShareniuUserTaskValidator extends ProcessLevelValidator {

	@Override
	protected void executeValidation(BpmnModel bpmnModel, Process process, List<ValidationError> errors) {
		List<UserTask> findFlowElementsOfType = process.findFlowElementsOfType(UserTask.class);
		for (UserTask userTask : findFlowElementsOfType) {
			if (userTask.getAssignee()==null || userTask.getAssignee().equals("")) {
				 addError(errors,userTask.getId()+ "没有任务处理人", process, userTask, "任务节点没有处理人");
			}
		}
	}

}
