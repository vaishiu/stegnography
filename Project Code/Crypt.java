import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Crypt extends JFrame
{
	int r=0;
	String encrypt(String message)
		{
		String s = message;
		int len = s.length();
		String s1 = "" ;
		try{
		for(int i=0;i<=len;i++)
		{
			 r=0;
			char c = s.charAt(i);
		if(c=='x' || c=='y'||c=='z')
		{
		
			if(c=='x')
			{
				r = 94;		
			}
			else if(c=='y')
			{
				r = 95;
			}
			else if(c=='z')
			{
				r = 96;
			}
			else
			{
				r = (int)c;
			}
			
			r = r+3;
		}
		else
		{
			r = (int)c;
			r = r+3;
		}
			c = (char)r;
			
			s1 = s1 + c;
		}
		}catch(Exception x){}
		return s1;
		}

		String decrypt(String message)
		{		
		String s2 = message;
		int len2 = s2.length();
		String s3 = "" ;
		try{
		for(int j =0;j<=len2;j++)
		{
			char c2 = s2.charAt(j);
		if(c2=='a'||c2=='b'||c2=='c')
		{
			if(c2=='a')
			{
				r = 123;		
			}
			else if(c2=='b')
			{
				r = 124;
			}
			else if(c2=='c')
			{
				r = 125;
			}
				
		}
		else
		{
		 r = (int)c2;
		}
			r = r-3;
			c2 = (char)r;
			s3 = s3 + c2;
		
		}
		}catch(Exception e2){}
		return s3;
		}
			
		
	

		
	public static void main(String ar[])
	{
		Crypt c = new Crypt();
	}
}