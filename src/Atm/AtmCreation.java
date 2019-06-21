package Atm;
import java.sql.*;
import java.util.*;
public class AtmCreation {
	atm2  ra1=new atm2();
	String driverClass="com.mysql.cj.jdbc.Driver";
	String databaseUrl="jdbc:mysql://localhost:3306/atm";
	String username="root";
	String password="";
	Scanner sc=new Scanner(System.in);
	String name,add;
	int number,pin,anumber;
	int deposite, total=0;
	public void createAccount() throws Exception {
		Class.forName(driverClass);
		Random rd=new Random();
		Connection cn =DriverManager.getConnection(databaseUrl,username,password);
		Statement stat = cn.createStatement();
		String query="select account_no from accountinfo where pin="+pin+"";
		ResultSet rs=stat.executeQuery(query);
		label:
		do {
		number=rd.nextInt(10000000)+1000000;
		while(rs.next()) {
		anumber=rs.getInt("account_no");
		if(anumber==number)
			continue label;
		}
		}while(anumber==number);
		System.out.println("Enter your name");
		name=sc.nextLine();
		
		System.out.println("Enter your Address:");
		add=sc.nextLine();

		System.out.println("Enter the marital status:");
		String mari=sc.next();
		
		System.out.println("Enter the profession:");
		String prof=sc.next();
		
		System.out.println("Your Account Number is :"+number);
		
		System.out.println("Enter your pin:");
		pin =sc.nextInt();
		String sql="insert into accountinfo(username,address,Marital_Status,profession,account_no,pin) values  ('"+name+"','"+add+"','"+mari+"','"+prof+"',"+number+","+pin+")";
		stat.executeUpdate(sql);
		System.out.println("Account creation Success.");
		System.out.println("*********************************************\n");
	}
	public void deposite() throws Exception {
		Class.forName(driverClass);
		Connection cn =DriverManager.getConnection(databaseUrl,username,password);
		Statement stat = cn.createStatement();
		String query="select * from accountinfo";
		ResultSet rs=stat.executeQuery(query);
		System.out.println("Do you already have account?Y/N");
		char check = sc.next().charAt(0);
		if(check=='N'||check=='n')
		{
			createAccount();
		}
		System.out.println("\t\tDeposite ");
		System.out.println("Enter your account number:");
		number=sc.nextInt();
		while(rs.next()) {
			int anumber=rs.getInt("account_no");
			if(anumber==number)
			{
				 System.out.println("Enter the amount to be deposited:");
				    deposite=sc.nextInt();
				    query ="select total from accountinfo where account_no="+number+"";
				    ResultSet RS=stat.executeQuery(query);
				    while(RS.next()) {
				    	total=RS.getInt("total");
				    }
				    total=total+deposite;
					String sql="update accountinfo set deposite="+deposite+",total="+total+" where account_no ="+number+"";
					
					if(stat.executeUpdate(sql) >0){
						System.out.println("Deposite Success.");
					}else{
						System.out.println("Deposite Unsuccess.");
					}
					System.out.println("*********************************************\n");
					
					ra1.main(null);
			}
		}
			
		System.out.println("Sorry Account Doesn't exit");
			
		
	}
	public void transferMoney() throws Exception {
		int apin=0,transfer;
		Class.forName(driverClass);
		Connection cn=DriverManager.getConnection(databaseUrl,username,password);
		Statement stat=cn.createStatement();
		System.out.println("Please Enter your account Number:");
		number=sc.nextInt();
		String query="Select * from accountinfo";
		ResultSet RS=stat.executeQuery(query);
		while(RS.next()) 
		{
		int anumber=RS.getInt("account_no");
		if(anumber==number)
		{
		System.out.println("Enter your pin:");
		pin=sc.nextInt();
		query="select * from accountinfo where account_no="+number+"";
		ResultSet rs=stat.executeQuery(query);
		while(rs.next()) {
		apin=rs.getInt("pin");
			total=rs.getInt("total");
		}
		if(pin!=apin)
		{
			System.out.println("Pin is incorrect.\nAccess Denied.");
			ra1.main(null);
		}
		else
		{
			System.out.println("How much money you want to transfer?");
			transfer=sc.nextInt();
			if(total<transfer)
			{
				System.out.println("Not sufficient Balance.");
			}
			else 
			{
				System.out.println("Please Enter receiver Account Number:");
				int transferAccount=sc.nextInt();
				query="select * from accountinfo";
				ResultSet rS=stat.executeQuery(query);
				while(rS.next()) {
					anumber=rS.getInt("account_no");
					if(transferAccount==anumber) {
						total=total-transfer;
						query="select * from accountinfo where account_no="+transferAccount+"";
						ResultSet Rs=stat.executeQuery(query);
						while(Rs.next()) {
							int receiverTotal=Rs.getInt("total");
							receiverTotal=transfer+receiverTotal;
							String sql="Update accountinfo set total="+receiverTotal+" where account_no="+transferAccount+"";
							stat.executeUpdate(sql);
							sql="Update accountinfo set total="+total+" where account_no="+number+"";
							stat.executeUpdate(sql);
							System.out.println("Transfer Success.");
					

							if(stat.executeUpdate(sql) >0){
								System.out.println("Deposite Success.");
							}else{
								System.out.println("Deposite Unsuccess.");
							}
							
							ra1.main(null);
						}
					}
				
				}
					System.out.println("Receiver Account number is incorrect.");
					System.exit(1);	
		}
		}
	
		}
		}
			
		System.out.println("Your Account Number is Incorrect");
		
	
	}
}
