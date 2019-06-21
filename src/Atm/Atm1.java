package Atm;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Atm1 {
	String driverClass="com.mysql.cj.jdbc.Driver";
	String databaseUrl="jdbc:mysql://localhost:3306/atm";
	String username="root";
	String password="";

	int number,deposite,total,withDraw;
	String name;
	int pin;
	Scanner sc=new Scanner(System.in);
	
	public void statement() throws Exception{
		Date date =  new Date();
		
		Class.forName(driverClass);
		Connection cn =DriverManager.getConnection(databaseUrl,username,password);
		Statement stat = cn.createStatement();
		System.out.println("Please Input Account Number of User:");
		int number = sc.nextInt();
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
		//ra2.main(null);
			}
		
			
		}
		System.out.println("No user found with this Account Number.");
		
	
		
			//pdf
			
	String file_name="E:\\\\Java\\prog\\pdg\\statement.pdf";
	Document document=new Document();
	
	PdfWriter.getInstance(document , new FileOutputStream(file_name));
	document.open() ;
			
			
		//	document.add(Image.getInstance(\.png"));
			document.add(new Paragraph("                                                                                                                 Date: "+RS.getString("time")));
			Paragraph para=new Paragraph("Account Name  :  "+RS.getString("first_name")+"   "+RS.getString("last_name"));
	    	document.add(para);
	    	 para=new Paragraph("Account Number  :  "+RS.getString("account_no"));

	    	 
	    	 document.add(para);
	    	 
			
	    	 Class.forName(driverClass);
	 		Connection conn1 =DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement ps1=null;
			
			ps1=conn1.prepareStatement(query);
			RS=ps1.executeQuery();
			document.add(new Paragraph(" "));
	   	 document .add(new Paragraph("Currency   :   NPR"));
	   	 document.add(new Paragraph("Accrued Interest : 0.45 "));
			document.add(new Paragraph("  "));	//for space 
			document.add(new Paragraph("                Deposited Balance  : "));
			document.add(new Paragraph(" "));
			PdfPTable table1=new PdfPTable(3);
			PdfPCell c11 =new PdfPCell(new Phrase("S.N."));
			table1.addCell(c11);
			
			c11=new PdfPCell(new Phrase("Debit Balance  "));
			table1.addCell(c11);
			
			c11=new PdfPCell(new Phrase("Date and Time of Transaction"));
			table1.addCell(c11);
			Statement stat1 = conn1.createStatement();

			String query1="Select * from accountinfo";
			query="select * from accountinfo where account_no="+number+"";
			ResultSet rs1=stat1.executeQuery(query);
			table1.setHeaderRows(1);
			while(rs1.next())
			{
				table1.addCell(rs1.getString("sno"));
				table1.addCell(rs1.getString("debit"));
				table1.addCell(rs1.getString("time"));
				
			}
			document.add(table1);
			
			
			document.add(new Paragraph(" "));

	    	 Class.forName(driverClass);
	 		Connection conn2 =DriverManager.getConnection(databaseUrl,username,password);
			PreparedStatement ps2=null;
			
			ps2=conn2.prepareStatement(query1);
			rs1=ps2.executeQuery();
			document.add(new Paragraph("               Withdrawn Balance :"));
			document.add(new Paragraph(" "));
			PdfPTable table2=new PdfPTable(3);
			PdfPCell  c2=new PdfPCell(new Phrase("S.N."));
			table2.addCell(c2);
			
			c2=new PdfPCell(new Phrase(" Credit Balance "));
			table2.addCell(c2);
			
			c2=new PdfPCell(new Phrase("Date and Time of Transaction"));
			table2.addCell(c2);
			
			table2.setHeaderRows(1);
		/*	while(rs11.next())
			{
				table2.addCell(rs11.getString("sno"));
				table2.addCell(rs11.getString("credit"));
				table2.addCell(rs11.getString("time"));
				
			}
			document.add(table2);
			
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Total Deposited balance : "+total));
			document.add(new Paragraph("\tTotal Withdrawn Balance : "+total_credit));
			document.add(new Paragraph("                                                                                         Available Bank Balance : "+available_balance));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("                                           !!    Thanks for using our banking Services      !!"));
			*/
			document.close();
			}


}
