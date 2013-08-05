import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiServerThread
   extends Thread
{
	private Socket mSocket;
	Writable mWritable;
	PrintWriter out;
	BufferedReader in;
	String name;
	/**
	*Constructor
	* @param Socket connection socket.
	* @param Writable takes a writeable from the MultiServer 
	*/
	public MultiServerThread(Socket pSocket, Writable pWritable)
	{
		super("MultiServerThread");
		mWritable = pWritable;
		mSocket = pSocket;
		//check guest numbers here to make name different each time
		String tmpNumber = Long.toString(System.currentTimeMillis()/1000);
		tmpNumber = tmpNumber.substring(tmpNumber.length()-6, tmpNumber.length()-1);
		name = "guest" + tmpNumber;
	}

	public void run()
	{
		try
		{
			out = new PrintWriter(mSocket.getOutputStream(), true);
			in = new BufferedReader(
											new InputStreamReader(
													mSocket.getInputStream()));
			String inputLine;
         String outputLine;
			
			//	Actual read in point. recieves from client.		
			while ((inputLine = in.readLine()) != null)
			{
				if(inputLine != null)
				{
					if(mWritable != null)
						mWritable.writeMessage(name + " : " + inputLine);//Sends msg to MultiServer which then sends to all the clients connected to it.
				}
			}
	
			out.close();
			in.close();
			mSocket.close();
		}
		catch (IOException e)
		{ e.printStackTrace(); }
	}
	public void sendMsg(String str)
	{ out.println(str); }
	
	public String getClientName()
	{ return name; }

	public void changeName(String n)
	{ name = n; }
}
