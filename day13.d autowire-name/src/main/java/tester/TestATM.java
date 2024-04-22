package tester;

import dependency.SoapTransport;
import dependent.ATMImpl;

public class TestATM {

	public static void main(String[] args) {
		ATMImpl atm=new ATMImpl();//dependent obj
		atm.setMyTransport(new SoapTransport());//creting the deoendency n writting(D.I.)
		atm.withdraw(1000);
	}

}
