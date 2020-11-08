package ro.ase.ism.sap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Enumeration;

public class KeyStoreManager {
	
	public static void list(String keyStoreFile, String keyStorePass) 
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		
		File ksFile = new File(keyStoreFile);
		if(!ksFile.exists()) {
			System.out.println("------------ File is missing --------------");
			throw new FileNotFoundException();
		}
		
		FileInputStream fis = new FileInputStream(ksFile);
		
		KeyStore ks = KeyStore.getInstance("jks");
		ks.load(fis, keyStorePass.toCharArray());
		
		System.out.println(keyStoreFile + " content: ");
		
		Enumeration<String> entries = ks.aliases();
		while(entries.hasMoreElements()) {
			String entry = entries.nextElement();
			System.out.println(entry);	
			
			if(ks.isKeyEntry(entry)) {
				System.out.println(" - PrivateKeyEntry");
			}
			if(ks.isCertificateEntry(entry)) {
				System.out.println(" - trustedCertEntry");
			}	
			
		}
		
		fis.close();
		
	}
	
}
