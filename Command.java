
public class Command
{
	public static boolean isCMD(String str)
	{
		String[] al = str.split("\\s+");
		if(al.length > 2)
		{
			if(al[2].equals("/name"))
				return true;
			else return false;
		}
		return false;
	}
	public static String parseString(String str)
	{
		String[] al = str.split("\\s+");
		return al[4];
	}
	
	public static String handle(String input)
	{
		if(MultiServer.requestName(input))
			return "name change sucessfull";
		else
			return "failure";
	}

	public static String pm(String input)
	{
		//fill in later
		return " ";
	}

	public static String kick(String input)
	{
		//fill in later
		return " ";
	}

	public static String ban(String input)
	{
		//fill in later
		return " ";
	}

	public static String help(String input)
	{
		//fill in later
		return " ";
	}
}
