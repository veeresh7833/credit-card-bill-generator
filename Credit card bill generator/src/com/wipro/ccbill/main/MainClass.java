package com.wipro.ccbill.main;
import com.wipro.ccbill.entity.CreditCardBill;
import com.wipro.ccbill.entity.Transaction;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;
import java.io.*;
public class MainClass {
  public static void main(String []args)
  {
  try {
	  Transaction monthsTransaction[] = new Transaction[5];
	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	  FileInputStream fis=new FileInputStream(new File("Test.txt"));       
   	  Scanner sc=new Scanner(fis); 
     for(int i=0;i<monthsTransaction.length;i++)
{ 
    	  
//while(sc.hasNextLine())  
//{  
String str=sc.nextLine();
String [] del=str.split(" ");  
double d=Double.parseDouble(del[3]);
 monthsTransaction[i]=new Transaction(del[0],formatter.parse(del[1]),del[2],d,del[4]); 
//}

}   
 CreditCardBill ccbill = new CreditCardBill("1111222233334444","ABC123",formatter.parse("02/03/2016"),monthsTransaction,"premium");
      System.out.println("Total to be paid : "+ccbill.calculateBillAmount());
 }
     catch(Exception e){
    	 
	 }
 }
  }