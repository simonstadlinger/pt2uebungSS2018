import java.io.*;

public class Account {

	public String login_name;
	public String password;
	public int uid;
	public int gid;
	public String user_name;
	public String directory;
	public String shell;

	public Account(String login_name, String password, int uid, int gid, String user_name, String directory, String shell) {
		this.login_name = login_name;
		this.password   = password;
		this.uid        = uid;
		this.gid        = gid;
		this.user_name  = user_name;
		this.directory  = directory;
		this.shell      = shell;
	}

	public static void main(String args[]){
		open("passwd.txt");
	}

	public static Account[] open(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));	

			String accInfo;
			while((accInfo = reader.readLine()) != null) {
				System.out.println(accInfo);
			}

			

			return null;
		}
		catch(IOException e) {
  			System.out.println("ERROR: File wasn't found!\n");
  			return null;
		}
		
	}

	/*public static Account find_account(Account[] liste, String login_name) {
		
		return result;
	}*/
}