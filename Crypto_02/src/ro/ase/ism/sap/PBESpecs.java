package ro.ase.ism.sap;

public class PBESpecs {
	byte[] salt;
	int noIterations;
	byte[] iv;
	byte[] params;
	
	public PBESpecs(byte[] salt, int noIterations, byte[] iv, byte[] params) {
		super();
		this.salt = salt.clone();
		this.noIterations = noIterations;
		this.iv = iv.clone();
		this.params = params.clone();
	}

	public byte[] getSalt() {
		return salt;
	}

	public int getNoIterations() {
		return noIterations;
	}
	
	public byte[] getIV() {
		return this.iv;
	}

	public byte[] getParams() {
		return params;
	}
}
