package ro.ase.ism.sap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class SymCipher {
	
	public static void encryptECB(
			String inputFileName,
			String password,
			String outputFileName,
			String encAlgorithm
			) throws IOException, 
					NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		
		//managing files
		
		File inputFile = new File(inputFileName);
		if(!inputFile.exists()) {
			System.out.println("---------- The file is not there -------");
			throw new FileNotFoundException();
		}
		FileInputStream fis = new FileInputStream(inputFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		
		File outputFile = new File(outputFileName);
		if(!outputFile.exists()) {
			outputFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);
		
		//create the Cipher
		Cipher cipher = 
				Cipher.getInstance(encAlgorithm+"/ECB/PKCS5Padding");
		//init the Cipher
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), 
				encAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		//read and encrypt
		byte[] inputBuffer = new byte[cipher.getBlockSize()];
		byte[] outputBuffer;
		int noBytes = 0;
		while((noBytes = fis.read(inputBuffer)) != -1) {
			outputBuffer = cipher.update(inputBuffer, 0, noBytes);
			fos.write(outputBuffer);
		}
		outputBuffer = cipher.doFinal();
		fos.write(outputBuffer);
		
		fis.close();
		fos.close();
	}
	
	public static void decryptECB(
			String inputFileName,
			String password,
			String outputFileName,
			String encAlgorithm
			) throws IOException, 
					NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		
		//managing files
		
		File inputFile = new File(inputFileName);
		if(!inputFile.exists()) {
			System.out.println("---------- The file is not there -------");
			throw new FileNotFoundException();
		}
		FileInputStream fis = new FileInputStream(inputFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		
		File outputFile = new File(outputFileName);
		if(!outputFile.exists()) {
			outputFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);
		
		//create the Cipher
		Cipher cipher = 
				Cipher.getInstance(encAlgorithm+"/ECB/PKCS5Padding");
		//init the Cipher
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), 
				encAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		//read and encrypt
		byte[] inputBuffer = new byte[cipher.getBlockSize()];
		byte[] outputBuffer;
		int noBytes = 0;
		while((noBytes = fis.read(inputBuffer)) != -1) {
			outputBuffer = cipher.update(inputBuffer, 0, noBytes);
			fos.write(outputBuffer);
		}
		outputBuffer = cipher.doFinal();
		fos.write(outputBuffer);
		
		fis.close();
		fos.close();
	}

}
