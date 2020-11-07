package ro.ase.ism.sap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.RecursiveAction;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SymCipher {

	public static void encryptECB(String inputFileName, String password, String outputFileName, String encAlgorithm)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		// managing files

		File inputFile = new File(inputFileName);
		if (!inputFile.exists()) {
			System.out.println("---------- The file is not there -------");
			throw new FileNotFoundException();
		}
		FileInputStream fis = new FileInputStream(inputFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		File outputFile = new File(outputFileName);
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);

		// create the Cipher
		Cipher cipher = Cipher.getInstance(encAlgorithm + "/ECB/PKCS5Padding");
		// init the Cipher
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), encAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key);

		// read and encrypt
		byte[] inputBuffer = new byte[cipher.getBlockSize()];
		byte[] outputBuffer;
		int noBytes = 0;
		while ((noBytes = fis.read(inputBuffer)) != -1) {
			outputBuffer = cipher.update(inputBuffer, 0, noBytes);
			fos.write(outputBuffer);
		}
		outputBuffer = cipher.doFinal();
		fos.write(outputBuffer);

		fis.close();
		fos.close();
	}

	public static void decryptECB(String inputFileName, String password, String outputFileName, String encAlgorithm)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		// managing files

		File inputFile = new File(inputFileName);
		if (!inputFile.exists()) {
			System.out.println("---------- The file is not there -------");
			throw new FileNotFoundException();
		}
		FileInputStream fis = new FileInputStream(inputFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		File outputFile = new File(outputFileName);
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);

		// create the Cipher
		Cipher cipher = Cipher.getInstance(encAlgorithm + "/ECB/PKCS5Padding");
		// init the Cipher
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), encAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, key);

		// read and encrypt
		byte[] inputBuffer = new byte[cipher.getBlockSize()];
		byte[] outputBuffer;
		int noBytes = 0;
		while ((noBytes = fis.read(inputBuffer)) != -1) {
			outputBuffer = cipher.update(inputBuffer, 0, noBytes);
			fos.write(outputBuffer);
		}
		outputBuffer = cipher.doFinal();
		fos.write(outputBuffer);

		fis.close();
		fos.close();
	}

	public static void encryptCBC(String inputFileName, String password, String outputFileName, String encAlgorithm)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

		// managing files
		File inputFile = new File(inputFileName);
		if (!inputFile.exists()) {
			System.out.println("---------- The file is not there -------");
			throw new FileNotFoundException();
		}
		FileInputStream fis = new FileInputStream(inputFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		File outputFile = new File(outputFileName);
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);

		// CBC requires an IV value - it's not a secret
		// option 1 - use predefine value
		// option 2 - generate a random one

		// decide how to handle it
		// option 1 - hardcode the value
		// option 2 - write it in the cipher text file - at the beginning

		// we go for a random one that we will write at the beginning of the file

		// create the Cipher
		Cipher cipher = Cipher.getInstance(encAlgorithm + "/CBC/PKCS5Padding");
		int blockSize = cipher.getBlockSize();

		// generate a random IV
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		byte[] iv = new byte[blockSize];
		secureRandom.nextBytes(iv);

		// init the Cipher
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), encAlgorithm);
		IvParameterSpec ivParam = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParam);

		// write it in the output file
		fos.write(iv);

		byte[] inputBuffer = new byte[blockSize];
		byte[] outputBuffer;
		int noBytes = 0;

		while ((noBytes = fis.read(inputBuffer)) != -1) {
			outputBuffer = cipher.update(inputBuffer, 0, noBytes);
			fos.write(outputBuffer);
		}
		outputBuffer = cipher.doFinal();
		fos.write(outputBuffer);

		fis.close();
		fos.close();
	}

	public static void decryptCBC(String inputFileName, String password, String outputFileName, String encAlgorithm)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

		// managing files
		File inputFile = new File(inputFileName);
		if (!inputFile.exists()) {
			System.out.println("---------- The file is not there -------");
			throw new FileNotFoundException();
		}
		FileInputStream fis = new FileInputStream(inputFile);

		File outputFile = new File(outputFileName);
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);

		// cipher
		Cipher cipher = Cipher.getInstance(encAlgorithm + "/CBC/PKCS5Padding");
		int blockSize = cipher.getBlockSize();
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), encAlgorithm);

		// we know the IV is the first block in the received file
		byte[] iv = new byte[blockSize];
		fis.read(iv);

		IvParameterSpec ivParam = new IvParameterSpec(iv);

		cipher.init(Cipher.DECRYPT_MODE, key, ivParam);

		byte[] input = new byte[blockSize];
		byte[] output;
		int noBytes = 0;

		while ((noBytes = fis.read(input)) != -1) {
			output = cipher.update(input, 0, noBytes);
			fos.write(output);
		}

		output = cipher.doFinal();
		fos.write(output);

		fis.close();
		fos.close();
	}

	public static PBESpecs encryptPBE(String inputFileName, String password, String outputFileName, String encAlgorithm)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
			InvalidKeySpecException {

		// managing files
		File inputFile = new File(inputFileName);
		if (!inputFile.exists()) {
			System.out.println("---------- The file is not there -------");
			throw new FileNotFoundException();
		}
		FileInputStream fis = new FileInputStream(inputFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		File outputFile = new File(outputFileName);
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);

		// init PBE Cipher
		Cipher cipher = Cipher.getInstance(encAlgorithm);
		int blockSize = cipher.getBlockSize();

		// create the PBE key based on user string password
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[cipher.getBlockSize()];
		secureRandom.nextBytes(salt);
		int noIterations = 1000;
		PBEKeySpec pbeKey = new PBEKeySpec(password.toCharArray(), salt, noIterations, blockSize);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(encAlgorithm);
		Key key = keyFactory.generateSecret(pbeKey);

		cipher.init(Cipher.ENCRYPT_MODE, key);

		// print the PBE details
		System.out.println("Using PBE with the user password " + password);
		Util.printHex(cipher.getIV(), "PBE generated IV ");
		Util.printHex(salt, "Random salt ");
		System.out.println("\nNo iterations " + noIterations);
		
	
		PBESpecs pbeSpecs = 
				new PBESpecs(salt, noIterations, cipher.getIV(),
						cipher.getParameters().getEncoded());

		// encryption

		byte[] input = new byte[blockSize];
		byte[] output;
		int noBytes = 0;

		while ((noBytes = fis.read(input)) != -1) {
			output = cipher.update(input, 0, noBytes);
			fos.write(output);
		}

		output = cipher.doFinal();
		fos.write(output);

		fis.close();
		fos.close();

		return pbeSpecs;
	}

	public static void decryptPBE(String inputFileName, String password, String outputFileName, String encAlgorithm,
			PBESpecs pbeSpecs) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
			InvalidKeySpecException {

		// managing files
		File inputFile = new File(inputFileName);
		if (!inputFile.exists()) {
			System.out.println("---------- The file is not there -------");
			throw new FileNotFoundException();
		}
		FileInputStream fis = new FileInputStream(inputFile);

		File outputFile = new File(outputFileName);
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);

		Cipher cipher = Cipher.getInstance(encAlgorithm);
		int blockSize = cipher.getBlockSize();
		
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), 
				pbeSpecs.getSalt(), pbeSpecs.getNoIterations(),
				blockSize);
		SecretKeyFactory keyFactory = 
				SecretKeyFactory.getInstance(encAlgorithm);
		Key key = keyFactory.generateSecret(keySpec);
		
		AlgorithmParameters algParams;
		algParams = AlgorithmParameters.getInstance(encAlgorithm);
		algParams.init(pbeSpecs.getParams());
		
		cipher.init(
				Cipher.DECRYPT_MODE, key, algParams);


		// decryption
		byte[] input = new byte[blockSize];
		byte[] output;
		int noBytes = 0;

		while ((noBytes = fis.read(input)) != -1) {
			output = cipher.update(input, 0, noBytes);
			fos.write(output);
		}

		output = cipher.doFinal();
		fos.write(output);

		fis.close();
		fos.close();
	}
}
