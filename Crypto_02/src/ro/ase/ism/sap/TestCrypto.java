package ro.ase.ism.sap;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class TestCrypto {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidAlgorithmParameterException {
	
		System.out.println("Starging ...");
		
		//DES = 64 bit; -> 64 bit block size
		//AES = 128, 192, 256, 512 -> 128 bit block size
		//3DES = 3 x 64 bit keys = 192 bits -> 64 bit block size
		
		//SymCipher.encryptECB(
				//"Message.txt", "password12345678password", "Message.enc", "DESede");
		//SymCipher.decryptECB(
				//"Message.enc", "password12345678password", "Message2.txt", "DESede");
		
		//ECB
		SymCipher.encryptECB(
				"Message.txt", "password", "Message.enc", "DES");
		SymCipher.decryptECB(
				"Message.enc", "password", "Message2.txt", "DES");
		//CBC
		SymCipher.encryptCBC(
				"Message.txt", "password12345678", "MessageCBC.enc", "AES");
		
		System.out.println("That's it");
		
	}

}
