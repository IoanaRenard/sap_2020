package ro.ase.ism.sap;

import java.util.Base64;

public class DataTypes {
	
	public static String toHexString(int value) {
		
		String hexString = "";
		String[] hexSymbols = 
				new String[] {"0","1","2","3","4","5","6","7","8","9","A","B",
						"C","D","E","F"};
			
		//if byte is 23 -> "17"
		//if byte is 27 -> "1B"
		
		while(value != 0) {
			int hexIndex = value % 16;
			hexString = hexSymbols[hexIndex] + hexString;
			//value /= 16;
			value = (byte) (value >> 4);	//faster than the division, because 16 = 2^4
		}
		
		if(hexString.length() == 0) {
			hexString = "00";
		}
		
		if(hexString.length() == 1) {
			hexString = "0" + hexString;
		}
		
		return hexString;
	}

	public static void main(String[] args) {
		
		int intValue = 10;
		
		System.out.println("Value = " + intValue);
		
		intValue = 0x14;		//it's 20 in decimal
		
		System.out.println("Value = " + intValue);
		
		intValue = 0b00001010;	//it's 10 in decimal
		
		System.out.println("Value = " + intValue);
		
		//define a byte with the 10101010.... pattern
		byte byteValue = (byte) 0b10101010;
		System.out.println("Byte Value = " + byteValue);
		byteValue = (byte) 0xAA;
		System.out.println("Byte Value = " + byteValue);
		byteValue = (byte) (1 << 7 | 1 << 5 | 1 << 3 | 1 << 1);
		System.out.println("Byte Value = " + byteValue);
		
		//check bit values in a variable
		int justAValue = 23;
		//check the 5th bit (always from right to left) in the int value
		int mask = 0b0001_0000;
		mask = 0b00010000;
		
		//init a mask with the sign bit 1 for an int
		int mask2 = 0b1000_0000;		//this is NOT 10000.....000 over 32 bits
										//this is 0000_0000_0000_0000_0000_0000_1000_0000
		//this is it
		mask2 = 1 << 31;
		
		//check the bit
		if((justAValue & mask) != 0) {
			System.out.println("The 5th bit is one");
		}
		else {
			System.out.println("The 5th bit is zero");
		}
		
		//printing value in Hexa strings
		byte[] values = new byte[] {10,45,23,6,9,100};
		
		String hexValue = "";
		for(byte value : values) {
			hexValue += (toHexString(value) + " ");
		}
		
		System.out.println("The hex representation of values is " + hexValue);
		
		intValue = 34674;
		System.out.println(
				"The hex representation is " + Integer.toHexString(intValue));
		//get a byte array from a integer
		
		int[] byteRepresentation = new int[Integer.BYTES];
		for(int i = 0; i< byteRepresentation.length; i++) {
			
			int bitMask = (int) 0xFF;
			byteRepresentation[byteRepresentation.length - 1 - i] = 
					(int) ((intValue >> (i*8)) & bitMask);
		}
		
		
		hexValue = "";
		for(int value : byteRepresentation) {
			hexValue += (toHexString(value) + " ");
		}
		
		System.out.println("Our hex representation is " + hexValue);
		
		String stringValue = "34674";			//this is a 10 byte array - char  = 2 bytes
		byte[] bytes = stringValue.getBytes();	//this is a 5 byte array
		
		hexValue = "";
		for(int value : bytes) {
			hexValue += (toHexString(value) + " ");
		}
		
		System.out.println("Hex representation for the string is " + hexValue);
		
		
		//alternative to our toHexString method
		System.out.println("Values in hex strings");
		for (byte b : values) {
			   System.out.format("0x%02x ", b);
			}
		System.out.println("");

		//base 64 encoding
		byte[] byteValues = new byte[byteRepresentation.length];
		for(int i = 0; i < byteRepresentation.length; i++) {
			byteValues[i] = (byte) byteRepresentation[i];
		}
		String intBase64Rep = 
				Base64.getEncoder().encodeToString(byteValues);
		
		System.out.println("34674 in base64 is " + intBase64Rep);
		
		String base64Value = Base64.getEncoder().encodeToString("34674".getBytes());
		
		System.out.println("the 34674 string in base64 is " + 
				Base64.getEncoder().encodeToString("34674".getBytes()));
		
		
		byte[] byteArray = Base64.getDecoder().decode(base64Value);
		System.out.println("The initial value is " + new String(byteArray));
		
		
	}

}
