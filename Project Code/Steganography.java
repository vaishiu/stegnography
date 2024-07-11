import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class Steganography extends JFrame implements ActionListener, KeyListener
{
	JLabel in,out,msg,lblSizeIn;
	JButton encode,decode,br_in,br_out,help;
	JTextField txtInFile,txtOutFile;
	JTextArea txtMessage;
	FileDialog fd;	
	String inFile,outFile,message;
	JDialog d;	
	JPanel p;
	
	private final int OFFSET=EncodeDecode.OFFSET;
	public boolean action;
	private boolean isEmbeddable;
	public  long inputFileSize;
	private JScrollPane scrollPane;
	private JFileChooser fileChooser;
	private BackGround backend;
	Steganography()
	{
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});		
Container c=getContentPane();
		
			fileChooser= new JFileChooser("./");
		inputFileSize= -1;
		isEmbeddable= false;
		setSize(680,500);
		p=new JPanel();
		p.setLayout(null);
		c.add(p);
		setVisible(true);
		setTitle("Steganography By Dr D Y Patil College of Engineering and Innovation, Talegon");	
		p.setBackground(Color.cyan);
		setLocation(150,150);
		setResizable(false);

         in=new JLabel("Input image");
		p.add(in);
		
		out=new JLabel("Save Image At");
		p.add(out);
		
		msg=new JLabel("Message");
		p.add(msg);	
		
		lblSizeIn=new JLabel();
		p.add(lblSizeIn);
		
		help=new JButton("Help");
		p.add(help);
		
		encode=new JButton("Encode");
		p.add(encode);
		
		decode=new JButton("Decode");
		p.add(decode);	
	
		br_in=new JButton("Browse");
		p.add(br_in);	

		br_out=new JButton("Browse");
		p.add(br_out);

		txtInFile = new JTextField();
		txtOutFile = new JTextField();
		
		
		
		txtMessage = new JTextArea();
		txtMessage.addKeyListener(this);
		p.add(txtInFile);
		p.add(txtOutFile);	
		
		int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		scrollPane=new JScrollPane(txtMessage,v,h);
		p.add(scrollPane);
		
		in.setBounds(20,80,120,25);
		txtInFile.setBounds(140,80,350,25);
		br_in.setBounds(510,80,90,30);
		lblSizeIn.setBounds(600,80,120,25);
		
		out.setBounds(20,130,120,25);
		txtOutFile.setBounds(140,130,350,25);
		br_out.setBounds(510,130,90,30);
		
		msg.setBounds(20,180,60,30);
		scrollPane.setBounds(140,180,400,200);

		encode.setBounds(140,400,80,30);
		decode.setBounds(330,400,80,30);
		help.setBounds(520,400,80,30);
		
		br_in.addActionListener(this);
		br_out.addActionListener(this);
		encode.addActionListener(this);
		decode.addActionListener(this);
		help.addActionListener(this);
		
		
		}

		public void keyPressed(KeyEvent k) {}
	public void keyReleased(KeyEvent k) {}
	public void keyTyped(KeyEvent k)
	{
		message= txtMessage.getText();
		int size= message.length();

		if(size>=32766)
		{
			JOptionPane.showMessageDialog(this, "This is the Maximum possible length of the message!", "Message limit", JOptionPane.INFORMATION_MESSAGE);
			txtMessage.setText(message.substring(0,32765));
		}

		updateEmbedability();
		return;
	}

	private void updateEmbedability()
	{
 		message= txtMessage.getText();
		int messageSize= message.length();
		
		if(txtInFile.getText().length()>0 && messageSize>0)
		{
			if(inputFileSize- OFFSET>messageSize*4)
				isEmbeddable= true;
			else
				isEmbeddable= false;

		}
	}

	public void actionPerformed(ActionEvent e)
	{
		Object obj= e.getSource();
		// Input Browse button was pressed
		if(obj== br_in)
		{			
			if(fileChooser.showOpenDialog(this)== fileChooser.APPROVE_OPTION)
			{
				String path= fileChooser.getSelectedFile().getPath();
				txtInFile.setText(path);
				inputFileSize= new File(path).length();
				lblSizeIn.setText(""+ inputFileSize+ " B");
				updateEmbedability();
				
			}
		}

		// Output Browse button was pressed
		if(obj== br_out)
		{
			if(fileChooser.showSaveDialog(this)== fileChooser.APPROVE_OPTION)
				txtOutFile.setText(fileChooser.getSelectedFile().getPath());
					
		}

		// Encode button was pressed
		if(obj== encode)
		{
			action= true;
			if(validateInput())
			{
				backend= new BackGround(txtMessage.getText(), txtInFile.getText(), null, txtOutFile.getText(), this);
				backend.start();
				txtMessage.setText("");
			// 	ProgressBar pp=new ProgressBar();
				
			}
			else return;
		}

		// Decode button was pressed
		if(obj== decode)
		{			
			action= false;
			if(validateInput())
			{
				backend= new BackGround(txtMessage.getText(), txtInFile.getText(), null, txtOutFile.getText(), this);
				backend.start();
			}
			else return;
		}

		// Help button was pressed
		if(obj== help)
		{
			try
			{
				Runtime run= Runtime.getRuntime();
				run.exec("notepad help.txt");
			}
			
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(this, "Unable to open help file\n"+ ee, "Operation Failed", JOptionPane.ERROR_MESSAGE);
				System.out.println("Error: "+ ee);
			}
		}

	}

        //checks the validaty of all input fields and also validate message box
	private boolean validateInput()
	{
                //input field is empty
		if(txtInFile.getText().length()<=0)
		{
			JOptionPane.showMessageDialog(this, "Please choose the input file!", "Input file required.", JOptionPane.WARNING_MESSAGE);
			return false;
		}
               
		if(action)			// true indicates Encode operation
		{
                        //message size is too large
			if(!isEmbeddable)
			{
				JOptionPane.showMessageDialog(this, "Message is too large to be embedded in input file\nPlease choose a larger input file.", "Message Unembeddable!", JOptionPane.WARNING_MESSAGE);
				return false;
			}

                        //ouput file field empty
			if(txtOutFile.getText().length()<=0)
			{
				JOptionPane.showMessageDialog(this, "Please choose the output file!", "Output file required.", JOptionPane.WARNING_MESSAGE);
				return false;
			}
                        
                        //text file field empty
			if(txtMessage.getText().length()<=0)
			{
				JOptionPane.showMessageDialog(this, "Please key in the message!", "Message is empty.", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}

		return true;
	}

}
		
			 
