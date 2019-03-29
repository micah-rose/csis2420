package heapSort;

import java.util.Random;

public class Mail implements Comparable <Mail> {

	Random rand = new Random();
	private String mailCode;
	DeliveryType dType;
	//char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	public Mail(DeliveryType dType, String mailCode) {
		this.dType = dType;
		this.mailCode = mailCode;
	}
	
	public Mail() { //FIX ME 
	    int type = new Random().nextInt(DeliveryType.values().length);
	    int mailCodeSize = 5;
	    char c = 0;

	    for(int i = 0; i <= mailCodeSize; i++) {
	    	c = (char) (rand.nextInt(26) + 'a');
	    }
	    
	    System.out.println(type + " " + c);
		
	}
	
	@Override
	public String toString()
	{
		return mailCode + "(" + dType + ")";
	}
	
	public int compareTo(Mail arg0) 
	{
		int compare = dType.compareTo(arg0.dType);
		if(compare == 0) {
			return mailCode.compareTo(arg0.mailCode);
		}
		return compare;
	}	
	
	
}
