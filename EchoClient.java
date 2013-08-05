import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient extends Thread
{
      Socket echoSocket = null;
      PrintWriter out = null;
      BufferedReader in = null;
		BufferedReader stdIn = null;
		Writable mWritable;
	public EchoClient(Writable pWritable)
	{
		mWritable = pWritable;
		try
      {
         echoSocket = new Socket("127.0.0.1", 4444);
         out = new PrintWriter(echoSocket.getOutputStream(), true);
         in = new BufferedReader(
            new InputStreamReader(echoSocket.getInputStream()));
      }
      catch (UnknownHostException e)
      {
         System.err.println("Don't know about host: taranis.");
         System.exit(1);
      }
      catch (IOException e)
      {
         System.err.println("Couldn't get I/O for "
                            + "the connection to: taranis.");
         System.exit(1);
      }
      stdIn =
         new BufferedReader(new InputStreamReader(System.in));
	}

   public void run()
   {
		try
		{
      	String userInput;
	
      	while (true)
      	{
      	   //out.println(userInput);
				mWritable.writeMessage(in.readLine());
      	   //System.out.println("echo: " + in.readLine());
      	}
		}
		catch(Exception e)
		{System.out.println(e);}
   }
	public void sendMsg(String s)
	{
		out.println(s);
	}
}

