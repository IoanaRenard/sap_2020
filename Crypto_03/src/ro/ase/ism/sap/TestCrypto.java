package ro.ase.ism.sap;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class TestCrypto {

	public static void main(String[] args) 
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		
		KeyStoreManager.list("ismkeystore.ks", "passks");
		
	}

}
