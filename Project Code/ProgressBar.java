import java.awt.*;
import java.awt.event.*;

public class ProgressBar extends Frame{
	Image image; 
	String outfile;	
	public static void main(String[] args) {
			//new ProgressBar();
	}
	public ProgressBar(String outfile){
		setTitle("STEGANOGRAPHY");
		setSize(350,150);
		//setDefaultLookAndFeelDecorated(true);
		setLocation(350,300);
	/*	addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});*/
		this.outfile=outfile;
		setVisible(true);
		setResizable(false);
	}
	public void paint(Graphics g){ 
		Toolkit tool = Toolkit.getDefaultToolkit();
		image = tool.getImage("wait.gif");
		g.drawString(outfile,20,40);
		g.drawImage(image,20,45,this);
;
}

}