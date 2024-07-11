/**
 *  File Name  = EncodeDecode.java
 *  Class Name = EncodeDecode.class
 *	
 *	
 *  @ Dr D Y Patil College of Engineering and Innovation ,Talegon
 *  @date 
 */


import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EncodeDecode
{
	public static final int OFFSET= 10240;
	private String message;
	private String outputFile, dataFile, inputFile;
	public long progress;
	private DataInputStream in, data;
	private DataOutputStream out;
	private Boolean check=false;
	private int i, j;
	private short messageSize, temp;
	private int dataFileSize, tempInt;
	byte by,byt, byb,ch;
Crypt c;
	public EncodeDecode()
	{
		i=0;
		j=0;
		 c=new Crypt();
	}

	public String getMessage()
	{
		return message;
	}

	public boolean encodeMessage(String msg, String inFile, String outFile)
	{
		//message= msg;
		inputFile= inFile;
		outputFile= outFile;
ProgressBar pp=new ProgressBar("Writing At:"+outFile);
message=c.encrypt(msg);
	try
		{
			// Open the data file
			in= new DataInputStream(new FileInputStream(inputFile));
			out= new DataOutputStream(new FileOutputStream(outputFile));

			// Skip past OFFSET bytes
			for(i=0; i<= OFFSET; i++)
				out.writeByte(in.readByte());

			messageSize= (short) message.length();
		
			for(i=14; i>=0; i-=2)
			{
				temp= messageSize;
				temp>>=i;		// Shift the bits in pair of 2 to least significant position
				by= (byte) temp;	// and store them in the Vector
				by&= 0x03;
				// messageSize being 16 bit short type, will be stored in 8 pairs of 2 bits
				// Write these bytes to the output file
				byt= in.readByte();
				byt&= 0xFC;
				byt|= by;
				out.writeByte(byt);				
			}

			for(i=0; i<messageSize; i++)	// Embed the message
			{
				byt= (byte) message.charAt(i);
				byt&= 0x7F;

				for(j=6; j>=0; j-=2)
				{
					by= byt;
					by>>= j;
					by&= 0x03;
					
					// Write these bytes to the output file
					byb= in.readByte();
					byb&= 0xFC;
					byb|= by;
					out.writeByte(byb);
				}
			}

			// Write the remaining bytes
			while(true)
			{
				by= in.readByte();
				out.writeByte(by);
			}
		}
		catch(EOFException e)
		{
		}
		catch(Exception e)
		{
			message= "Oops!! an Error occured: "+ e.toString();
			return false;
		}
		finally
		{
			try
			{
				in.close();
				out.close();
			}
			catch(Exception ex) 
			{
				message= "Oops!!an Error occured: "+ ex.toString();
				return false;
			}
		}

		message= "Encoding successful...";
		pp.setVisible(false);
		return true;
	}

	public String decodeMessage(String inFile)
	{
		inputFile= inFile;
		char mesg[]= null;
ProgressBar p=new ProgressBar("Reading From:"+inputFile); 
		try
		{
			in= new DataInputStream(new FileInputStream(inputFile));

			// Get the size of the message
			messageSize= 0;

			for(i=0; i<=OFFSET; i++)	// Skip OFFSET bytes
				in.readByte();

			for(i=14; i>=0; i-=2)
			{
				by= in.readByte();	// Read a byte from input file
				temp= (short) by;
				temp&= 0x0003;
				temp<<= i;			// Shift the bits in pair of 2 to get them to the right position
				messageSize|= temp;
			}
				

			if(messageSize<=0)
			{
				message= "This file does not contain a message.";
				return("#FAILED#");
				
			}

			mesg= new char[messageSize]; // Create a character array of size messageSize
			for(i=0; i<messageSize; i++)
			{
				by= 0;
				for(j=6; j>=0; j-=2)
				{					
					byt= in.readByte();	// Read a byte from input file
					byt&= 0x03;
					byt<<= j;
					by|= byt;
				}
				mesg[i]= (char) (((char) by)& 0x007F);
			}			
		}
		catch(Exception e)
		{
			message= "Oops!!\n Error: "+ e;
			return("#FAILED#");
		}
		finally
		{
			try
			{
				in.close();
			}
			catch(Exception ex)
			{
				message= "Oops!!\nError: "+ ex;
				return("#FAILED#");
			}

			message= "Message size: "+ messageSize+ " B";
			String messg= new String(mesg);
			messg=c.decrypt(messg);
			p.setVisible(false);
			return messg;
	
		}
	}

}

class AboutFrame extends JFrame implements ActionListener
{	
	private JLabel  lblImage, lblName1, lblPhone1;
        	private JLabel lblName2, lblPhone2;
        	private JLabel lblName3, lblPhone3;
	private JLabel lblName4,lblPhone4;
        	private JLabel lblLabel4, lblName5;
	private JButton btnClose;
	private JLabel uvbtn, pritbtn, saurbtn,chetbtn;
	Frame f;
	JPanel p;
	public AboutFrame()
	{
		super("STEGANOGRAPHY IMPLEMENTATION BY:");
		Container c=getContentPane();
		p=new JPanel();
		p.setLayout(null);
	
		lblImage= new JLabel(new ImageIcon("mail.gif"));    

		lblName1= new JLabel("Vaishnavi Salunke");
		uvbtn= new JLabel("vaishnavi2002@mail.com");
		lblPhone1= new JLabel("+91-9307734113");
		
		lblName2= new JLabel("Shraddha");
		pritbtn= new JLabel("shaddha@gmail.com");
		lblPhone2= new JLabel("+91-8446698371");
                
        	lblName3= new JLabel("Rupali");
		saurbtn= new JLabel("rupali@gmail.com");
		lblPhone3= new JLabel("+91-9579137388");

		lblName4= new JLabel("Mugdha");
		chetbtn= new JLabel("mugdha@gmail.com");
		lblPhone4= new JLabel("+919422373289");
              
        		lblLabel4= new JLabel("Guided By:");
        		lblName5= new JLabel("DYPCOEI computer dept, Sagar Sir");

		btnClose= new JButton("Back");

		btnClose.addActionListener(this);

		p.add(lblImage);

		p.add(lblName1);
		p.add(uvbtn);
		p.add(lblPhone1);

		p.add(lblName2);
		p.add(pritbtn);
		p.add(lblPhone2);

		/*p.add(lblName3);
		p.add(saurbtn);
		p.add(lblPhone3);

		p.add(lblName4);
		p.add(chetbtn);
		p.add(lblPhone4);

		p.add(lblLabel4);*/
		p.add(lblName5);

		p.add(btnClose);

		lblImage.setBounds(20,20,60,60);

		lblName1.setBounds(150,20,100,30);
		uvbtn.setBounds(130,40,200,30);
		lblPhone1.setBounds(150,60,100,30);

		lblName2.setBounds(350,20,100,30);
		pritbtn.setBounds(330,40,200,30);
		lblPhone2.setBounds(350,60,100,30);
/*
		lblName3.setBounds(550,20,100,30);
		saurbtn.setBounds(530,40,200,30);
		lblPhone3.setBounds(550,60,100,30);

		lblName4.setBounds(770,20,100,30);
		chetbtn.setBounds(750,40,200,30);
		lblPhone4.setBounds(770,60,100,30);

		lblLabel4.setBounds(450,100,100,30); */
		lblName5.setBounds(430,120,150,30);
		p.setBackground(Color.cyan);
		setSize(950,200);
		setResizable(false);
		c.add(p);
	setLocation(80, 250);
		p.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
	
				if(e.getSource()==btnClose)
				setVisible(false);
	}
}