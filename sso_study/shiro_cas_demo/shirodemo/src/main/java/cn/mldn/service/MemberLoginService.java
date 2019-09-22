package cn.mldn.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import cn.mldn.vo.Member;

public class MemberLoginService {
	private Connection conn; // 数据库的连接类对象
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/shirodb";
	private static final String DBUSER = "root";
	private static final String PASSWORD = "mysqladmin";
	private PreparedStatement pstmt = null;

	public MemberLoginService() { // 在构造方法里面进行数据库连接对象的实例化
		this.connectionDataBase(); // 进行数据库的连接取得
	}

	public Member get(String mid) { // 实现用户登录处理
		Member vo = null;
		try {
			String sql = "SELECT mid,password FROM member WHERE mid=?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, mid);
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				vo = new Member();
				vo.setMid(rs.getString(1));
				vo.setPassword(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	/**
	 * 根据用户名称查询出用户对应的所有的角色数据
	 * @param mid
	 * @return
	 */
	public Set<String> listRolesByMember(String mid) {
		Set<String> allRoles = new HashSet<String>();
		String sql = "SELECT flag FROM role WHERE rid IN (" + "		SELECT rid FROM member_role WHERE mid=?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, mid);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				allRoles.add(rs.getString(1)) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allRoles ;
	}
	/**
	 * 根据用户的名称查询出该用户对应的所有权限数据
	 * @param mid
	 * @return
	 */
	public Set<String> listActionsByMember(String mid) {
		Set<String> allActions = new HashSet<String>();
		String sql = "SELECT flag FROM action WHERE actid IN ("
				+ "			SELECT actid FROM role_action WHERE rid IN("
				+ "				SELECT rid FROM member_role WHERE mid=?))";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, mid);
			ResultSet rs = this.pstmt.executeQuery();
			while (rs.next()) {
				allActions.add(rs.getString(1)) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allActions ;
	}
	
	public void close() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void connectionDataBase() { // 专门负责数据库连接的创建
		try {
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
