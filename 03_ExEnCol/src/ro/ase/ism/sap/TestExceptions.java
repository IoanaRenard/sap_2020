package ro.ase.ism.sap;

import java.io.FileNotFoundException;

public class TestExceptions {
	
	public static void generateException(int number) 
			throws SmallIntException, FileNotFoundException {
		if(number < Byte.MAX_VALUE)
			throw new SmallIntException();
		
		if(number == 200)
			throw new FileNotFoundException();
	}

	public static void main(String[] args) {
		
		int value = 10;
		
		System.out.println("Start...");
		
		try {
			System.out.println("Starting try");
			value += 2;
			generateException(300);		
			value +=2;
			System.out.println("Ending try");
		} 
		catch (SmallIntException e) {
			System.out.println("The number was small");
		}
		catch (FileNotFoundException ex) {
			System.out.println("The number was 200");
			value = 200;
		}
		catch (Exception ex) {
			System.out.println("Another error " + ex.getMessage());
		}
		finally {
			System.out.println("Always executed");
		}

		
		System.out.println("The value is " + value);	
		System.out.println("The end");
	}

}


class SmallIntException extends Exception{
	
	public SmallIntException(String msg) {
		super(msg);
	}
	
	public SmallIntException() {
	}
}
