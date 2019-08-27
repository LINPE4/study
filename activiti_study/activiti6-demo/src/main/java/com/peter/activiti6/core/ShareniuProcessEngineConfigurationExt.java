package com.peter.activiti6.core;

import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.validator.ValidatorSet;
import org.activiti.validation.validator.ValidatorSetNames;

import java.util.List;

public class ShareniuProcessEngineConfigurationExt extends org.activiti.spring.SpringProcessEngineConfiguration {
	@Override
	public void initProcessValidator() {
		this.processValidator = new ProcessValidatorFactory().createDefaultProcessValidator();
		
		List<ValidatorSet> validatorSets = processValidator.getValidatorSets();
		validatorSets.add(createValidatorSet());
	}
	private ValidatorSet createValidatorSet() {
		ValidatorSet validatorSet = new ValidatorSet(ValidatorSetNames.ACTIVITI_EXECUTABLE_PROCESS);

		validatorSet.addValidator(new ShareniuUserTaskValidator());
		return validatorSet;
	}
	
	
}
