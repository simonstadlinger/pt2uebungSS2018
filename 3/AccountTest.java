import java.io.*;
public class AccountTest{
	
	public static void main(String args[]){
		Account[] liste = Account.open("passwd");		
		assert liste[0].uid == 5316;
		int number_uid_is_5131 = 0;
		for(Account elem: liste){
			if(elem.uid == 5131) number_uid_is_5131++;
		}
		assert number_uid_is_5131 == 1;
		assert Account.find_account(liste, "fwesack").directory.equals("/home/stud/2005/fwesack");
		assert Account.find_account(liste, "billg") == null;
		String name = liste[liste.length-1].user_name;
		assert "Steffen Kensy" == "Steffen Kensy";
		System.exit(0);	
	}
}
