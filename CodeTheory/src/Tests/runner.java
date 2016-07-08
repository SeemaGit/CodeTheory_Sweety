package Tests;

import java.util.regex.Pattern;

import org.testng.Assert;

public class runner {
public static String d ="20/5/2016";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pattern p = Pattern.compile("[^0-9]");
		String s = ".12l3hj54j";
		 String a[]=p.split(s,2);
		a[0].replaceAll(" ", "0");
	for(int i=0;i<a.length;i++)
System.out.println(a[i]);
  

	if(a[0]==null)
	{
		a[0]="0";
	}
	System.out.println(a[0].get);
	System.out.println("dhdh");
}
}
