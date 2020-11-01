package ro.ase.ism.sap;

/**
 * @author CatalinBoja
 *
 */
public class UserAccount {
	public int userId;
	public String pass;
	int failedLogins;
	
	public UserAccount(int userId, String pass) {
		this.userId = userId;
		this.pass = pass;
	}
	
	
	String getInfo() {
		return String.format("User id = %d, Pass = %s, Failed logins = %d",
				this.userId, this.pass, this.failedLogins);
	}


	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new UserAccount(this.userId, this.pass);
	}


	@Override
	public boolean equals(Object obj) {
		UserAccount received = (UserAccount) obj;
		return (this.userId == received.userId);
	}
	
	
	
	
}
