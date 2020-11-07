package ro.ase.ism.sap;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class TestCrypto {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
	
		System.out.println("Starging ...");
		
		SymCipher.encryptECB(
				"Message.txt", "passwordpassword", "Message.enc", "AES");
		SymCipher.decryptECB(
				"Message.enc", "passwordpassword", "Message2.txt", "AES");
		
		System.out.println("That's it");
		
	}

}
