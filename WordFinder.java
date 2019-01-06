import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


public class WordFinder {
	
	
	private static String[] list;
	private static Hashtable<String, Integer> table;
	private static int count = 0;
	
	public static void main(String args[]) 
	{		
		String input = "abcdefghij";
		init(input.length());

		findStrings(input,"","",input.length());
		inputWords("Dictionary.txt");
		
		
		
		findWords();
		
	}
	
	public static void init(int i)
	{
		count = 0;
		list = new String[findArraySize(i)];
		table = new Hashtable<String, Integer>();
	}
	
	public static void inputWords(String fileName) 
	{

		int index = 0;

		
		try
		{
			BufferedReader read = new BufferedReader(new FileReader(fileName));
			while(read.ready())
			{	
				String word = read.readLine();
				
				table.put(word, index);
				index++;
			}
		
		}
		catch(IOException e)
		{
			System.err.println("File Not Found");
		}
	}
	
	public static void findWords()
	{
		int i = 0;

		while(i < list.length && list[i] != null)
		{
			if(table.get(list[i].toUpperCase()) != null)
			{
				System.out.println(list[i]);
			}
			
			i++;
		}
	}
	
	private static int findArraySize(int i)
	{
		int total = i;
		int last = i;
	
		for(int a = i - 1; a > 0; a--)
		{
			total += (last * a);
			last = last * a;
		}
		
		return total;
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
					//listOfStrings.add(stringSoFar);
					
					list[count] = stringSoFar;
					count++;
					
					String passed = letters.substring(0,i) + letters.substring(i + 1,letters.length());
				
					findStrings(passed,stringSoFar,"",level-1);
					duplicates = duplicates + letters.charAt(i);
				}
				
				
				stringSoFar = tempStringSoFar;
				
			}
		}
	}
	

}