import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordFinder {
	
	
	private static ArrayList<String> listOfStrings = new ArrayList<String>();
	private static int counter = 0;
	
	public static void main(String args[]) 
	{		
		String input = "abb";

		findStrings(input,"","",input.length());
		
		inputWords("Dictionary.txt");
		
	}
	
	public static void inputWords(String fileName) 
	{
		String passed = "aa";
		int index = 0;
		
		try
		{
			BufferedReader read = new BufferedReader(new FileReader(fileName));
		
		while(read.ready())
		{	
			String word = read.readLine();
			counter++;
		
			
			
		}
		
		}
		catch(IOException e)
		{
			System.err.println("File Not Found");
		}
		
		
	}
	

	


	/**
	 * This method finds all the substrings given a string input
	 * @param letters The input string
	 * @param stringSoFar The subset of strings
	 * @param duplicates Keep tracks of equivilant strings 
	 * @param level The level of the recursive call. Should be proportional to the the length of letter string
	 */
	
	public static void findStrings(String letters, String stringSoFar,String duplicates, int level)
	{
		boolean flag = true;
		if(level == 0)
		{
			flag = false;
		}
		
		if(flag)
		{
			
			String tempStringSoFar = stringSoFar;
			for(int i = 0; i < level; i++)
			{
				stringSoFar = stringSoFar + letters.charAt(i) + "";
				
				
				if(!(duplicates.contains(letters.charAt(i) + "")))
				{
					listOfStrings.add(stringSoFar);
					
					String passed = letters.substring(0,i) + letters.substring(i + 1,letters.length());
				
					findStrings(passed,stringSoFar,"",level-1);
					duplicates = duplicates + letters.charAt(i);
				}
				
				
				stringSoFar = tempStringSoFar;
				
			}
		}
	}
	

}