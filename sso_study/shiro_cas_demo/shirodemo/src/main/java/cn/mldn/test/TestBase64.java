package cn.mldn.test;

import java.util.Base64;

public class TestBase64 {
	public static void main(String[] args) {
		String val = "www.mldn.cn";
		System.out.println(new String(Base64.getEncoder().encode(val.getBytes())));
	}
}
