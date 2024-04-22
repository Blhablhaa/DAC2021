package singleton;

public class SingletonEagerInit {
	private static final SingletonEagerInit ref;
	static {
		System.out.println("in static init block...");
		ref = new SingletonEagerInit();
	}

	private SingletonEagerInit() {
		System.out.println("in ctor");
	}

	public static SingletonEagerInit getSingletonInstance() {

		return ref;
	}

}
