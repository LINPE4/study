package com.peter.activiti6.repositoryservice;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

public class RepositoryServiceTest {

	ProcessEngine defaultProcessEngine;
	RepositoryService repositoryService;

	@Before
	public void init() {
		defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
		repositoryService = defaultProcessEngine.getRepositoryService();
		System.out.println(repositoryService);
	}

	@Test
	public void testRepositoryServiceImpl() {
		// MybatisDeploymentDataManager
		// ResourceEntityManagerImpl
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		System.out.println(deploymentBuilder);
	}

	/**
	 *   部署会插入
	 * 	`ACT_RE_DEPLOYMENT`，`ACT_RE_PROCDEF`，`ACT_GE_BYTEARRAY`
	 */
	@Test
	public void testAddClasspathResource() {

		String resource = "process/MyProcess.bpmn";
		Deployment deployment = repositoryService.createDeployment()
				.name("分享牛测试002")
				.key("测试002——key")
				.category("测试002-分类")
				.addClasspathResource(resource)// 添加资源
				.deploy();// 完成发布
		System.out.println(deployment.getId() + "::::::" + deployment.getKey() + ":::::" + deployment.getName());

	}
	/**
	 * 资源的名称  必须是new String[] { "bpmn20.xml", "bpmn" };  结尾，否则可能导致定义表没有数据，定义表没有数据就意味着没有办法启动
	 * 新的流程实例。
	 */
	@Test
	public void testAddString() {
		String namePre="分享牛测试004";
		String filePath = "process/MyProcess.bpmn";
		String resourceName="MyProcess.bpmn";
		String text=IoUtil.readFileAsString(filePath);
		Deployment deployment = repositoryService.createDeployment()
				.name(namePre)
				.key(namePre+"——key")
				.category(namePre+"-分类")
				.addString(resourceName, text)
				.deploy();// 完成发布
		System.out.println(deployment.getId() + "::::::" + deployment.getKey() + ":::::" + deployment.getName());
		
	}
	
	
	@Test
	public void addInputStream() {
		String namePre="分享牛测试006";
		String filePath = "process/MyProcess.bpmn";
		InputStream inputStream = RepositoryServiceTest.class.getClassLoader()
		.getResourceAsStream(filePath);
		String resourceName="MyProcess.bpmn";
		String text=IoUtil.readFileAsString(filePath);
		Deployment deployment = repositoryService.createDeployment()
				.name(namePre)
				.key(namePre+"——key")
				.category(namePre+"-分类")
				.addInputStream(resourceName, inputStream)
				.deploy();// 完成发布
		System.out.println(deployment.getId() + "::::::" + deployment.getKey() + ":::::" + deployment.getName());
		
	}
	@Test
	public void addBytes() {
		String namePre="分享牛测试007";
		String filePath = "process/MyProcess.bpmn";
		InputStream inputStream = RepositoryServiceTest.class.getClassLoader()
		.getResourceAsStream(filePath);
		String resourceName="MyProcess.bpmn";
		String text=IoUtil.readFileAsString(filePath);
		byte[] bytes=IoUtil.readInputStream(inputStream, "MyProcess.bpmn");
		Deployment deployment = repositoryService.createDeployment()
				.name(namePre)
				.key(namePre+"——key")
				.category(namePre+"-分类")
				.addBytes(resourceName, bytes)
				.deploy();// 完成发布
		System.out.println(deployment.getId() + "::::::" + deployment.getKey() + ":::::" + deployment.getName());
		
	}
	@Test
	public void addZipInputStream() {
		String namePre="分享牛测试100";
		String filePath = "process/zip.zip";
		InputStream inputStream = RepositoryServiceTest.class.getClassLoader()
		.getResourceAsStream(filePath);
		ZipInputStream	zipInputStream=new ZipInputStream(inputStream);
		Deployment deployment = repositoryService.createDeployment()
				.name(namePre)
				.key(namePre+"——key")
				.category(namePre+"-分类")
				.addZipInputStream(zipInputStream)
				.deploy();// 完成发布
		System.out.println(deployment.getId() + "::::::" + deployment.getKey() + ":::::" + deployment.getName());
		
	}
	
}
