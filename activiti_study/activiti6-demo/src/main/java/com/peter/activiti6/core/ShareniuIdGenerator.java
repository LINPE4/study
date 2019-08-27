package com.peter.activiti6.core;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

public class ShareniuIdGenerator implements IdGenerator {

	public String getNextId() {
		return "shareniu"+UUID.randomUUID().toString();
	}

}
