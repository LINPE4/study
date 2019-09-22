package cn.mldn.service;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements IEmpService {
	private Logger log = Logger.getLogger(IEmpService.class) ;
	@RequiresAuthentication
	@Override
	public void add() {
		log.info("************ 【IEmpService.add()】 ************");
	}
	@RequiresGuest
	@Override
	public void edit() {
		log.info("************ 【IEmpService.edit()】 ************");
	}
	@RequiresRoles(value={"member","dept"})
	@RequiresPermissions(value={"emp:list","member:list"})
	@Override
	public void remove() {
		log.info("************ 【IEmpService.remove()】 ************");
	}
	@RequiresPermissions(value={"emp:list","member:list"})
	@Override
	public void list() {
		log.info("************ 【IEmpService.list()】 ************"); 
	}

}
