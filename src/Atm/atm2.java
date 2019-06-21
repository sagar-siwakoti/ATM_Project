package Atm;

import java.util.*;
import java.lang.*;
public class atm2 {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		AtmCreation sag = new AtmCreation();
		atm3  ra=new atm3();
		int choice,achoice = 0;//label:
		do{
			System.out.println("\n******************************************************************");
			System.out.println("\n********************\tEVIL BANK OF NEPAL\t******************\n");
			System.out.println("******************************************************************\n");

		System.out.println("Please Enter your choice: \n1.Go to Bank\n2.Go to ATM\n3.Exit");
		choice =sc.nextInt();
		switch(choice) {
		case 1:
			do{
			System.out.println("Enter \n1.Create Account\n2.DepositeMoney\n3.TransferMoney\n4.exit");
		achoice = sc.nextInt();
		switch(achoice) {
		case 1: 
			sag.createAccount();
			break;
		case 2:
			sag.deposite();
			break;
		case 3:
			sag.transferMoney();
			break;
		case 4:
			System.exit(1);
			break;
		default:
			System.out.println("Wrong input .Please select the right option.");
			
		}
		//continue label;
	}while(achoice!=4);
		break;
		case 2:
			do {
			System.out.println("Enteryour choice\n 1.Balance inquiry\n2.Change Pin\n3.statement\n4.Withdrawal\n5.Exit");
			achoice = sc.nextInt();
			switch(achoice) {
			case 1:
				ra.balanceInquir();
				break;
			case 2:
				ra.changePin();
				
				break;
			case 3:
				ra.statement();
				break;
			case 4:
				ra.withDraw();
				break;
			case 5:
				break;
			}
			}while(achoice!=5);
			break;
		case 3:
		System.exit(0);
		
		}
	}while(choice!=3);
	}
}