package ro.ase.ism.sap;

import java.nio.ByteBuffer;
import java.util.Base64;


//from Preda Mihai

public class DataTypes_Ref {
	
	public static String toHexString(int value) {
		String[] hexSymbols = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
		String hexString ="";
		//if byte is 23 -> "x17
		while(value != 0) {
			int hexIndex = value % 16;
			hexString = hexSymbols[hexIndex] + hexString;
			value = (byte) (value >> 4); //faster than division, because 16 = 2^4
		}
		//check if it is actual 0
		if(hexString.length() == 0) {
			hexString = "00";
		}

		if(hexString.length() == 1) {
			hexString = "0" + hexString;
		}
		return hexString;
		
	}
	public static int[] integerToByteArray(int intValue) {
		int[] byteRepresentation = new int[Integer.BYTES];
		for(int i=0; i< byteRepresentation.length; i++) {
			int bitMask =(int) 0xFF;
			byteRepresentation[byteRepresentation.length -1 -i] = 
					(int) ((intValue >>(i*8)) & bitMask );
		}
		return byteRepresentation;
	}
	
	public static String byteArrayToHexString(int[] byteRepresentation) {
		String hexValue = "";
		for(int value: byteRepresentation) {
			hexValue += (toHexString(value)+ " ");
		}
		return hexValue;
	}
	public static String stringToHexString(String stringValue) {
		String hexValue = "";
		byte[] bytesString = stringValue.getBytes();
		for(int value: bytesString) {
			hexValue += (toHexString(value)+ " ");
		}
		return hexValue;
	}
	
	public static byte[] intArray2ByteArray(int[] byteRepresentation) {
		byte[] byteValues = new byte[byteRepresentation.length];
		for(int i =0; i< byteRepresentation.length;i++) {
			byteValues[i] = (byte)byteRepresentation[i];
		}
		return byteValues;
	}
	public static void main(String[] args) {
		int intValue = 10;
		System.out.println("Value = "+intValue);
		
		intValue = 0x14; //it's 20 in decimal
		System.out.println("Value = "+intValue);
		
		intValue = 0b00001010; //its 10 in decimal
		
		System.out.println("Value = "+intValue);
		
//		define a byte with the 10101010 ..a pattern
		
		byte byteValue = (byte) 0b10101010;
		System.out.println("Byte Value = " + byteValue);
		
		byteValue = (byte) 0xAA;
		System.out.println("Byte Value = " + byteValue);
		byteValue = (byte) ( 1 << 7  | 1 << 5 | 1 << 3 | 1 << 1);
		System.out.println("Byte Value = " + byteValue);
		
		//check bit vales in a variable
		
		int justAValue = 23;
		
		//check the 5th bit (always from right to left) in int value 
		int mask = 0b0001_0000;
		
		//init a mask with the sign bit 1 for an int
		int mask2 = 0b1000_0000; //this is NOT 1000.....000 over 32 bits
		//this is  0000_0000_0000_0000_0000_0000_1000_0000
		
		//correct way
		
		mask2 = 1 <<31;
		
		//check the bit
		if((justAValue & mask) != 0) {
			System.out.println("The 5th bit is one");
		} else {
			System.out.println("The 5th bit is zero");
		}
		
		//printing values in Hexa strings
		// byte- is a signed byte => store values from 127 to -128
		byte[] values = new byte[] {0,10,45,23,6,9,100};
		String hexValue = "";
		for(byte value : values) {
			hexValue += (toHexString(value) + " ");
			
		}
		System.out.println("The hex represendation of values is " + hexValue);
		
		intValue = 34674;
		System.out.println("The hex representation is "+ Integer.toHexString(intValue));
		
		//get a byte array from integer
		int[] byteRepresentation = integerToByteArray(intValue);
		String result = byteArrayToHexString(byteRepresentation);
		
		System.out.println("Our  good represenzation is " + result+"\n");
		
		
		System.out.println("\n Alternative \n");
		byte[] bytes = ByteBuffer.allocate(4).putInt(34674).array();
		for (byte b : bytes) {
		   System.out.format("0x%x ", b);
		}
		
		String stringValue = "34674";
		String result2 = stringToHexString(stringValue);
		
		System.out.println("\nHex representation for the string is " + result2);
		
		
		System.out.println("\n---------------------------------\n");
		
		byte[] byteValues = intArray2ByteArray(byteRepresentation);
		String intBase64Rep = Base64.getEncoder().encodeToString(byteValues);
		
		System.out.println("34674 in base 64 is "+intBase64Rep+"\n");
		
		
		String stringBase64Rep = Base64.getEncoder().encodeToString(stringValue.getBytes());
		System.out.println("\nThe 34674 string in base 64 is " + stringBase64Rep+"\n");
		
		
		String base64Value =  Base64.getEncoder().encodeToString(stringValue.getBytes());

		byte[] byteArray = Base64.getDecoder().decode(base64Value);
		String decodedResult = new String(byteArray);
		System.out.println("The initial values is "+ decodedResult +"\n");

	}
}
