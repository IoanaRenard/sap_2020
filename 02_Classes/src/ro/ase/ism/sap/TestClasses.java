package ro.ase.ism.sap;

public class TestClasses {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		String fileName1 = "mysecretmsg.enc";
		byte[] byteRepres = fileName1.getBytes();
		
		//String are immutable
		fileName1 = "newFile.txt";		//creating a new String object
		
		String filename2 = "newFile.txt"; //is not creating a new object  - remember JVM String constant pool
		String filename3 = filename2;
		
		//is always checking addresses
		if(fileName1 == filename2) {
			System.out.println("They are equal");
		}
		else
		{
			System.out.println("They are not equal");
		}
		
		String filename4 = new String("newFile.txt");
		
		if(fileName1 == filename4) {
			System.out.println("They are equal");
		}
		else
		{
			System.out.println("They are not equal");
		}
		
		//to check strings use equals
		if(fileName1.equals(filename4)) {
			System.out.println("They are equal");
		}
		else
		{
			System.out.println("They are not equal");
		}
		
		filename2 = "newFile2.txt";		//get a new object
		
		
		int value1 = 23;
		//auto-boxing
		Integer object1 = value1;
		//un-boxing
		int value2 = object1;
		
		
		Integer oVb1 = 23;
		Integer oVb2 = 23;
		
		if(oVb1 == oVb2) 
			System.out.println("They are equal");
		else
			System.out.println("They are not equal");
		
		Integer oVb3 = 230;
		Integer oVb4 = 230;
		
		if(oVb3 == oVb4) 
			System.out.println("They are equal");
		else
			System.out.println("They are not equal");
		
		if(oVb3.equals(oVb4)) 
			System.out.println("They are equal");
		else
			System.out.println("They are not equal");
		
		oVb3 = 300;			//Integer is immutable -> you get a new object
		
		
		//shallow copy
		UserAccount user1 = new UserAccount(1, "1234");
		System.out.println("User 1 " + user1.toString());
		
		UserAccount user2 = new UserAccount(2, "123456");
		System.out.println("User 2 " + user2.toString());
		
		//both will reference the same object
		user2 = user1;
		System.out.println("User 2 " + user2.toString());
		
		System.out.println(user1.getInfo());
		System.out.println(user2.getInfo());
		
		user1.pass = "password";
		
		System.out.println(user1.getInfo());
		System.out.println(user2.getInfo());
		
		int[] values1 = new int[] {1,2,3,4,5};
		System.out.println("Values 1 = ");
		for(int value: values1)
			System.out.print(" " + value);
		
		//shallow copy
		int[] values2 = values1;
		System.out.println("\nValues 2 = ");
		for(int value: values2)
			System.out.print(" " + value);
		
		values1[2] = 99;
		
		System.out.println("\nValues 2 = ");
		for(int value: values2)
			System.out.print(" " + value);
		
		//deep copy for UserAccount
		UserAccount user3 = (UserAccount) user2.clone();
		
		//deep-copy for arrays
		int[] values3 = values2.clone();
		
		values3[0] = 100;
		System.out.println("\nValues 2 = ");
		for(int value: values2)
			System.out.print(" " + value);
		
	}

}
