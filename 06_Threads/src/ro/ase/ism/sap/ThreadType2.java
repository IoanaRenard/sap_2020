package ro.ase.ism.sap;

public class ThreadType2 implements Runnable{
	
	int id;
	String message;
	int interations;

	public ThreadType2(int id, String message, int interations) {
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
