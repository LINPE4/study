package cn.mldn.test;

import cn.mldn.util.MD5Code;

public class TestMD5 {
	public static void main(String[] args) {
		String password = "java" ;
		String salt = "d3d3Lm1sZG4uY24=" ;
		System.out.println(new MD5Code().getMD5ofStr(password + "{{" + salt + "}}"));
	}
}
