package dependent;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dependency.Transport;
//bean id : my_atm , singleton n eager
//dependecy  : test layer
@Component("my_atm")
public class ATMImpl implements ATM {
	
	@Autowired//autowire=byType
	@Qualifier("testTransport")//byName
	private Transport myTransport;//fiels levvel I.D.
	
	public ATMImpl() {
		System.out.println("in cnstr of " +getClass().getName()+" "+myTransport);
	}

	@Override
	public void deposit(double amt) {
		System.out.println("depositing "+amt);
		byte[] data=("depositing "+amt).getBytes();
		myTransport.informBank(data);//dependent object is calling nethid of the dependency

	}

	@Override
	public void withdraw(double amt) {
		System.out.println("withdrawing "+amt);
		byte[] data=("withdrawing "+amt).getBytes();
		myTransport.informBank(data);//dependent object is calling nethid of the dependency
	}
 
	//init style method
	@PostConstruct
	public void myInit() {
		System.out.println("in init"+myTransport);
	}
	//destroy style method
	@PreDestroy
		public void myDestroy() {
			System.out.println("in destroy"+myTransport);
		}
}
