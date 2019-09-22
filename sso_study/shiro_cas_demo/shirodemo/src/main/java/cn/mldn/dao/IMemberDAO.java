package cn.mldn.dao;

import java.util.Set;

import cn.mldn.vo.Member;

public interface IMemberDAO {
	public Member findById(String mid) ;
	public Set<String> findAllRoleByMember(String mid) ;
	public Set<String> findAllActionByMember(String mid) ;
}
