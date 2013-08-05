import java.io.IOException;
import java.util.ArrayList;
import java.net.ServerSocket;

public class MultiServer implements Writable
{
	ServerSocket serverSocket;
	boolean listening;
	static ArrayList<MultiServerThread> al;
	MultiServerThread mst;

	public MultiServer()
	{
		al = new ArrayList<MultiServerThread>();
		try
		{
			serverSocket = new ServerSocket(4444);
		}
		catch (IOException e)
		{
			System.err.println("Could not listen on port." + e);
			System.exit(-1);
		}
		listening = true;
	}

	public void run()
	{
		while (listening)
      {
			try
			{
				mst = new MultiServerThread(serverSocket.accept(), this);
			}
			catch(Exception e)
			{System.out.println(e);}

			mst.start();
			al.add(mst);
      }
	}
	public static void main(String[] args)
      throws IOException
	{
		new MultiServer().run();
	}

	public static boolean requestName(String msg)
	{
		String[] aStr = msg.split("\\s+");
		String n1 = aStr[0];
		String n2 = aStr[3];
		if(n2.equals("admin"))
		{
			/*	String pwd = aStr[4];
				System.out.println(ReadFile.read("pwdFile"));
				if(pwd.equals(ReadFile.read("pwdFile")))
					System.out.println("yessssss");				
			*/
		}
		else
		{
			for(int i=0; i<al.size(); i++)
			{
				if(al.get(i).getClientName().equals(n2))//add ignore case later
					return false;
			}
			for(int i=0; i<al.size(); i++)
			{
				if(al.get(i).getClientName().equals(n1))
				{
					al.get(i).changeName(n2);
					return true;
				}
			}
		}
		return false;//should never be reached
	}


	public void writeMessage(String msg)
	{
		System.out.println(msg);
		if(Command.isCMD(msg))
			System.out.println(Command.handle(msg));
		else 
			for(int i=0; i<al.size(); i++)
			{
				al.get(i).sendMsg(msg);
			}
	}
}
