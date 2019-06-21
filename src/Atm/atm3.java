package Atm;
import java.sql.*;
import java.util.*;
import java.util.Date;
public class atm3 {
	atm2  ra2=new atm2();
	String driverClass="com.mysql.cj.jdbc.Driver";
	String databaseUrl="jdbc:mysql://localhost:3306/atm";
	String username="root";
	String password="";
	int number,deposite,total,withDraw;
	String name;
	int pin;
	Scanner sc=new Scanner(System.in);
	public void balanceInquir() throws Exception {
		Class.forName(driverClass);
		Connection cn =DriverManager.getConnection(databaseUrl,username,password);
		Statement stat = cn.createStatement();
		System.out.println("Please Input Account Number of User:");
		number=sc.nextInt();
		String query="Select * from accountinfo";
		ResultSet RS = stat.executeQuery(query);
		while(RS.next()) {
			int anumber=RS.getInt("account_no");
			if(anumber==number) {
		 query="select * from accountinfo where account_no="+number+"";
		ResultSet rs=stat.executeQuery(query);
		while(rs.next()) {
			name=rs.getString("username");
			deposite=rs.getInt("deposite");
			total=rs.getInt("total");
			withDraw=rs.getInt("withdraw");
		}
		
		System.out.println("Account Number:"+number+"\nName :"+name+"\nLast deposite:"+deposite+"\nLast Withdraw :"+withDraw+"\nTotal Amount:"+total+"");

		
		
		ra2.main(null);
			}
		
			
		}
		System.out.println("No user found with this Account Number.");
		
	}
	public void withDraw() throws Exception {
		int apin=0;
		Class.forName(driverClass);
		Connection cn =DriverManager.getConnection(databaseUrl,username,password);
		Statement stat = cn.createStatement();
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
		   System.exit(1);
		}
		else
		{
			System.out.println("How much money you want to withdraw?");
			withDraw=sc.nextInt();
			if(total<deposite)
			{
				System.out.println("Not sufficient Balance.");
				System.exit(1);
			}
			else 
			{
				total = total-withDraw;
				String sql="update accountinfo set total="+total+",withdraw="+withDraw+" where account_no ="+number+"";
				stat.executeUpdate(sql);
				
			}
			System.out.println("Thank u,WithDraw completed.");
			ra2.main(null);
			
			
		}
		}
		
		}
	System.out.println("Account doesn't exist.");	
	}
	
	public void changePin() throws Exception{
		Class.forName(driverClass);
		Connection cn=DriverManager.getConnection(databaseUrl,username,password);
		Statement stat=cn.createStatement();
		System.out.println("Enter your Account number:");
		number=sc.nextInt();
		String query="Select * from accountinfo";
		ResultSet RS=stat.executeQuery(query);
		while(RS.next()) {
			int anumber=RS.getInt("account_no");
			if(anumber==number)
			{
				System.out.println("Enter your PIN:");
				pin=sc.nextInt();
				System.out.println("Enter New PIN:");
				int newPin=sc.nextInt();
				System.out.println("REEnter New PIN:");
				int new2Pin=sc.nextInt();
				query="select pin from accountinfo where account_no="+number+"";
				ResultSet rs=stat.executeQuery(query);
				while(rs.next()) {
					int apin=rs.getInt("pin");
					if(pin==apin&&newPin==new2Pin) {
						String sql="update accountinfo set pin="+newPin+" where account_no="+number+"";
						stat.executeUpdate(sql);
						System.out.println("PIN CHANGED.");
						
					}
					else if(new2Pin!=newPin)
					{
						System.out.println("Two new password doesn;t match.");
					System.out.println("PIN chnage process unsuccessful.");
					}
						else 
						{
							System.out.println("Entered Pin is incorrect.");
					System.out.println("PIN chnage process unsuccessful.");
						System.exit(1);
					

				}
			}
			
			
			
		}
	System.out.println("Account doesn't exist.");
	}

}
public void statement() throws Exception{
	Date date =  new Date();
	
	Class.forName(driverClass);
	Connection cn =DriverManager.getConnection(databaseUrl,username,password);
	Statement stat = cn.createStatement();
	System.out.println("Please Input Account Number of User:");
	number=sc.nextInt();
	String query="Select * from accountinfo";
	ResultSet RS = stat.executeQuery(query);
	while(RS.next()) {
		int anumber=RS.getInt("account_no");
		if(anumber==number) {
	 query="select * from accountinfo where account_no="+number+"";
	ResultSet rs=stat.executeQuery(query);
	while(rs.next()) {
		System.out.println("*********************************************\n");
		System.out.println("TIME::");
		System.out.println(date);
		name=rs.getString("username");
		deposite=rs.getInt("deposite");
		total=rs.getInt("total");
		withDraw=rs.getInt("withdraw");
	}
	
	System.out.println("Account Number:"+number+"\nName :"+name+"\nLast deposite:"+deposite+"\nLast Withdraw :"+withDraw+"\nTotal Amount:"+total+"");
	ra2.main(null);
		}
	
		
	}
	System.out.println("No user found with this Account Number.");
	
}
	
}