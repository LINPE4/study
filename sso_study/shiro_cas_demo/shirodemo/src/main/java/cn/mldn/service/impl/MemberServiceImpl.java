package cn.mldn.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dao.IMemberDAO;
import cn.mldn.service.IMemberService;
import cn.mldn.vo.Member;
@Service
public class MemberServiceImpl implements IMemberService {
	@Resource
	private IMemberDAO memberDAO ;
	@Override
	public Member get(String mid) throws Exception {
		return this.memberDAO.findById(mid); 
	}
	@Override
	public Map<String, Object> listAuthByMember(String mid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allRoles", this.memberDAO.findAllRoleByMember(mid)) ;
		map.put("allActions", this.memberDAO.findAllActionByMember(mid)) ;
		return map ;
	}

}
