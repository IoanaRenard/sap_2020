package ro.ase.ism.sap;

public class ThreadType1 extends Thread{

	int id;
	String message;
	int interations;
	

	public ThreadType1(int id, String message, int interations) {
		this.id = id;
		this.message = message;
		this.interations = interations;
	}

	@Override
	public void run() {
		for(int i = 0; i < this.interations; i++) {
			System.out.println(this.id + " - " + this.message);
		}
	}
	
	
	
}
