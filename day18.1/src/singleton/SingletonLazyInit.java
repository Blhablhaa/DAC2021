package singleton;

public class SingletonLazyInit {
	private static SingletonLazyInit ref;

	private SingletonLazyInit() {
		System.out.println("in ctor");
	}

	public static SingletonLazyInit getSingletonInstance() {
		if (ref == null)
			ref = new SingletonLazyInit();
		return ref;
	}

}
