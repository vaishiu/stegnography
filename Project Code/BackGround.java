import javax.swing.JOptionPane;


// Class for creation of threads for performing backend operations
public class BackGround extends Thread
{
	private EncodeDecode steg;
	private Steganography main;
//	private EmbedFile embedClient;;
	private String message;
	private String outFile;
	private String inFile;
	private String dataFile;

	public BackGround(String message, String inFile, String dataFile, String outFile, Object client)
	{
		steg= new EncodeDecode();
		this.message= message;
		this.inFile= inFile;
		this.dataFile= dataFile;
		this.outFile= outFile;

				this.main= (Steganography) client;
	}

	public void run()
	{
	
		{
			if(main.action)		// Encode
			{
				boolean result= steg.encodeMessage(message, inFile, outFile);
				if(result)
				{	//pp.setVisible(false);
		
					JOptionPane.showMessageDialog(main, "Message encoded successfully at =>\n "+ outFile, "Operation Successful", JOptionPane.INFORMATION_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(main, steg.getMessage(), "Operation Failed", JOptionPane.WARNING_MESSAGE);
			}				// Decode
			else
			{
				String result= steg.decodeMessage(inFile);
				if(result.equals("#FAILED#"))
					JOptionPane.showMessageDialog(main, steg.getMessage(), "Operation Failed", JOptionPane.WARNING_MESSAGE);
				else
					main.txtMessage.setText(result);
			}
		}
	}
}

