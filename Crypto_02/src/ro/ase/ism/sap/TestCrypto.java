package ro.ase.ism.sap;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class TestCrypto {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidAlgorithmParameterException, InvalidKeySpecException {
	
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
		SymCipher.decryptCBC("MessageCBC.enc", "password12345678", 
				"Message3.txt", "AES");
		
		//1 what do you do if the user password is not = with the block size
		//2 what do you do if the key is a string based on
		
		//solution to extend/reduce passwords to required size
		//solution to convert string based passwords to binary values
		//PBE - password based encryption
		
		PBESpecs pbeSpecs = SymCipher.encryptPBE("Message.txt", "a", 
				"MessagePBE.enc", "PBEWithHmacSHA256AndAES_128");
		SymCipher.decryptPBE("MessagePBE.enc", "a", 
				"Message4.txt", "PBEWithHmacSHA256AndAES_128", pbeSpecs);
		
		System.out.println("That's it");
		
	}

}
