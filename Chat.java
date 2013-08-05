import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
 
public class Chat extends Frame implements ActionListener, Writable 
{
		//JButton sendButton;
		JTextArea textArea;
		JTextField sendField;
		JScrollPane scrollable;
		JPanel pane;
		JFrame frame;
		EchoClient ec;
	private final static String newline = "\n";
	 
	 	public Chat() 
		{
			ec = new EchoClient(this);
			pane = new JPanel();
			//Create and set up the window.
			frame = new JFrame("Chat");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setMinimumSize(new Dimension(800, 400));
 
			//Set up the content pane.
			pane.setLayout(new GridBagLayout());
 			GridBagConstraints c = new GridBagConstraints();
			
 			textArea = new JTextArea();
			scrollable = new JScrollPane(textArea);
			textArea.setEditable(false);
			c.ipady = 400;
			c.gridwidth = 3;
			c.gridheight = 3;
			c.weightx = 1;
			c.weighty = 1;
 			c.fill = GridBagConstraints.BOTH;
 				c.gridx = 0;
 			c.gridy = 0;
			pane.add(scrollable, c);
	
 			sendField = new JTextField();
			sendField.addActionListener(this);
			c.ipady = 0;
			c.ipadx = 150;
			c.gridwidth = 2;
			c.gridheight = 1;
  	  		c.gridx = 0;
  	  		c.gridy = 3;
  			pane.add(sendField, c);
 			
			frame.add(pane);
 
			//Display the window.
			System.out.println("made to pack");
			frame.pack();
			frame.setVisible(true);
	 	}
    	public void actionPerformed(ActionEvent evt) 
	 	{
        	String text = sendField.getText();
		  		ec.sendMsg(text);
				sendField.setText("");
        		sendField.selectAll();
        		//Make sure the new text is visible, even if there
        		//was a selection in the text area.
        		textArea.setCaretPosition(textArea.getDocument().getLength());
    	}
		public EchoClient getEC()
		{
			return ec;
		}
		public void writeMessage(String msg)
		{
			textArea.append(msg + newline);
		}
 
	 	public static void main(String[] args) 
		{
		  //Schedule a job for the event-dispatching thread:
		  //creating and showing this application's GUI.
		  	javax.swing.SwingUtilities.invokeLater(new Runnable() 
			{
					public void run() 
					{
						Chat gui = new Chat();
						gui.getEC().start();
					}
		  });
	 	}
}
