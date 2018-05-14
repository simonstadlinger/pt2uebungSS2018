import java.io.*;
import java.util.Vector;
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

	public static Account[] open(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));	
			Vector<Account> container = new Vector<Account>();	
			String accInfo;
			while((accInfo = reader.readLine()) != null) {
				String[] dataset = accInfo.split(":");
				container.add(new Account(dataset[0],dataset[1], Integer.parseInt(dataset[2]),Integer.parseInt(dataset[3]),dataset[4],dataset[5],dataset[6]));
			}
			int size = container.size();
			Account[] accountset = new Account[size];
			for(int i = 0; i<size;i++)
			{
				accountset[i]= container.elementAt(i);
			}			

			return accountset;
		}
		catch(IOException e) {
  			System.out.println("ERROR: File wasn't found!\n");
  			return null;
		}
		
	}

	public static Account find_account(Account[] liste, String login_name) {
		
		for (Account acc: liste) {
			if (acc.login_name == login_name) {
				return acc;
			}
		}
		return null;
	}
}
