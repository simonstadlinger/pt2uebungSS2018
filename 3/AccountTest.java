import java.io.*;
public class AccountTest{
	
	public static void main(String args[]){
		Account[] liste = Account.open("passwd.txt");		
		assert liste[0].uid == 5316;
		assert liste[liste.length].user_name == "Steffen Kensy";
		int number_uid_is_5131 = 0;
		for(Account elem: liste){
			if(elem.uid == 5131) number_uid_is_5131++;
		}
		assert number_uid_is_5131 == 1;
		assert Account.find_account(liste, "fwesack").directory == "/home/stud/2005/fwesack";
		assert Account.find_account(liste, "billg") == null;
		System.exit(42);	
	}
}
