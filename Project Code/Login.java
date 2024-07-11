j
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Login extends JFrame implements ActionListener
{
  JButton btnok,btnclose,about;
  TextField txtname,txtpass;
  JLabel lblnmae,lblpass;
String str,pass;
AboutFrame a;
JPanel panel;
  Login()
   {
     super("Steganography-Login");
     panel=new JPanel();
     Container c=getContentPane();
     c.add(panel);
     btnok=new JButton("OK");
     btnclose=new JButton("Cancel");
     about=new JButton("About Us");
     lblnmae=new JLabel("User Name");
     lblpass=new JLabel("Password ");
     txtname=new TextField(15);
     txtpass=new TextField(15);
     txtpass.setEchoChar('*');
     panel.setLayout(new FlowLayout());
     panel.add(lblnmae);
     panel.add(txtname);
     panel.add(lblpass);
     panel.add(txtpass);
     panel.add(btnok);
     panel.add(btnclose);
    // panel.add(about);
     panel.setBackground(Color.PINK);
     setSize(260,130);
     setVisible(true);
     setResizable(false);

	btnok.addActionListener(this);
	btnclose.addActionListener(this);
	about.addActionListener(this);
	setLocation(300,300);
   }

public void actionPerformed(ActionEvent ae)
{

if(ae.getSource()==btnok)
{
	str=txtname.getText();
	pass=txtpass.getText();
	if(str.equals("DYPCOEI")&& pass.equals("computer"))
	{	
	Steganography s=new Steganography();
	setVisible(false);
	}
	else
	{
		JOptionPane.showMessageDialog(this, "Incorrect Username or Password\n", "Invalid User", JOptionPane.WARNING_MESSAGE);
	}
}
	if(ae.getSource()== about)
		{
			try
			{
					a=new AboutFrame();
					a.setVisible(true);
			}
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(this, "Unable to open help file\n"+ ee, "Operation Failed", JOptionPane.ERROR_MESSAGE);
				System.out.println("Error: "+ ee);
			}
		}
	if(ae.getSource()==btnclose)
	{
		System.exit(0);
	}

}


public static void main(String s[])
 {
 Login l=new Login();
 }
}