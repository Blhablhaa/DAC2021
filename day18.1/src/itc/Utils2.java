package itc;
public class Utils2 {
	private Emp e;
	private boolean dataReady;
	
	public synchronized void writeData(Emp ref) throws Exception {
		 System.out.println("w entered --- "+Thread.currentThread().getName());
		 while(dataReady)
			 wait();//Producer : Blocked on wait (outside the monitor)
		  
		//produce data
		e = ref;
		System.out.println("Write Data " +e);
		 System.out.println("w exited --- "+Thread.currentThread().getName());
		 dataReady=true;
		 notify();//trigger for un blocking consumer
	}

	public synchronized Emp readData() throws Exception {
		 System.out.println("r entered --- "+Thread.currentThread().getName());
		 while(!dataReady)
			 wait();
		//consume data
		System.out.println("Read  Data " + e);
		Thread.sleep(100);
		 System.out.println("r exited --- "+Thread.currentThread().getName());
		 dataReady=false;
		 notify();//trigger for un blocking producer
		return e;
	}

}
