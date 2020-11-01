package ro.ase.ism.sap;

import java.awt.image.BandCombineOp;

public class TestThreads {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Starting test...");
		
		ThreadType1 t1 = new ThreadType1(1, "ISM - master program", 10);
		Thread t2 = new Thread(new ThreadType2(2, "Hello world", 10));
		
		//never start threads with run - it's not a thread
		//t1.run();
		//t2.run();
		
		t1.start();
		t2.start();
		
		//don't forget to wait on your threads
		try {
			t1.join();
			t2.join();
			
		} catch (InterruptedException e) {
			System.out.println("We have a problem with our threads");
		}
		
		//test concurent access
		BankAccount account = new BankAccount(1000);
		Thread wife = new Thread(new ClientThread("Wife", account));
		Thread husband = new Thread(new ClientThread("Husband", account));
		
		wife.start();
		husband.start();
		
		wife.join();
		husband.join();
			
		System.out.println("The end.");
		
	}

}
