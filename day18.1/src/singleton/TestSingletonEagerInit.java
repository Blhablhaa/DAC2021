package singleton;

import static singleton.SingletonEagerInit.getSingletonInstance;

public class TestSingletonEagerInit {

	public static void main(String[] args) throws Exception {
		//load the class
		Class<?> loadedClass = Class.forName("singleton.SingletonEagerInit");
	
		System.out.println("cls loaded....");
		SingletonEagerInit ref1 = getSingletonInstance();
		SingletonEagerInit ref2 = getSingletonInstance();
		SingletonEagerInit ref3 = getSingletonInstance();
		System.out.println(ref1==ref2);
		System.out.println(ref1==ref3);

	}

}
