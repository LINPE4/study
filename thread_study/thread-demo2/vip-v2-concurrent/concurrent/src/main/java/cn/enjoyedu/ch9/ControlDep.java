package cn.enjoyedu.ch9;

/**
 * 控制依赖
 */
public class ControlDep {
	int a = 0;
	volatile boolean flag = false;

	public void init() {
		a = 1; // 1
		flag = true; // 2
		//.......
	}

	public synchronized void use() {
		if (flag) { // 3
			int i = a * a; // 4
		}
		//.......
	}
}
