package tester;

import java.net.MulticastSocket;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import config.AppConfig;
import dependent.ATMImpl;

public class TestSpring {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext (AppConfig.class)) {
			System.out.println("Sc booted....");
			// BeanFacory i/f method public<T> T getBean(String beanId,Class<T> class)....
			ATMImpl ref1 = ctx.getBean("my_atm", ATMImpl.class);// !st demand
			ref1.withdraw(1000);
			ATMImpl ref2 = ctx.getBean("my_atm", ATMImpl.class);
			System.out.println(ref1 == ref2);// true!
           //  ctx.close();--->directly call in singletone instance
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
