package com.wipro.ccbill.entity;
import java.util.Date;
import com.wipro.ccbill.exception.InputValidationException;
public class CreditCardBill {
	private String creditCardNo;
	private String customerId;
	private Date billDate;
	private Transaction monthTransactions[];
	private String custtype;
	public CreditCardBill ()
	{
	//super();	
	}
	public CreditCardBill (String creditCardNo, String customerId, Date billDate, Transaction monthTransactions[],String custtype)
	{
		this.creditCardNo=creditCardNo;
		this.customerId=customerId;
		this.billDate=billDate;
		this.monthTransactions=monthTransactions;
		this.custtype=custtype;
	}
	public int getCustomertype()
	{
		if(custtype.contentEquals("premium"))
		{
			return 20;
		}
		else if(custtype.contentEquals("regular"))
		{
			return 10;
		}
		else
		{
			return 0;
		}
	}
	public double getTotalAmount(String transactionType)
	{
	double amount =0;
	if(!((transactionType.contentEquals("DB"))||(transactionType.contentEquals("CR")||(transactionType.contentEquals("EMI")))))
	{
		return 0.0;
	}
	else
	{
		for(int i=0;i<monthTransactions.length;i++)
		{
			if(monthTransactions[i].getTransactionType().equals(transactionType))
				   amount+=monthTransactions[i].getTransactionAmount();
		}
		return amount;
	}
	}
	public double calculateBillAmount() {
		try
		{
			if(validateData().contentEquals("valid"))//condition 1
			{
				if(monthTransactions!=null&&monthTransactions.length>0)//condition 2
				{
					double amountSpend=getTotalAmount("DB");
					double amountPaid=getTotalAmount("CR");
					double emi=getTotalAmount("EMI");
					System.out.println(amountSpend+" "+amountPaid+" "+emi);
					double outstanding=amountPaid-(amountSpend+emi);
					double interest=(outstanding*(19.9/100))/12;
					int dis=getCustomertype();
					System.out.println(outstanding+" "+interest+" "+dis);
					return outstanding+(interest/dis);
				}
				else
				{
					return 0.0;//condition 2 fails
				}
			}
			else
			{
				return 0.0;
			}
		}
			catch(InputValidationException e)
		{
			e.toString();
		}
		return 0.0;
	}
	public String validateData() throws InputValidationException
	{
		if(creditCardNo==null||creditCardNo.length()!=16||customerId==null||customerId.length()<6) throw new InputValidationException();
		return "valid";
	}
}
