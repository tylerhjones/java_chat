import java.io.*;

public class ReadFile
{
	public static String read(String fileName)
	{
		String str = "fjiorewhguewvnioenci4t984u8t934jiorfht84923hgiovpoh4t8932foiphhiophqvf89hit234griotpu4";//junk overwritten. will never be guessed.
		try 
		{
    		BufferedReader in = new BufferedReader(new FileReader(fileName));
    		while ((str = in.readLine()) != null) 
			{
    		}
    		in.close();
		}
		catch (IOException e) 
		{
			System.out.println(e);
		}
		return str;
	}
}
