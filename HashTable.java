

public class HashTable {

	Node[] hashTable = null;
	
	public HashTable()
	{
		hashTable = new Node[5];
	}
	
	public void add(String word)
	{
		Node<String> current = new Node<String>(word);
		hash(word,current);
	}
	
	public void hash(String key, Node<String> value)
	{
		int counter = 0;
		for(int i = 0; i < key.length(); i++)
		{
			
		}
	}
	
	private void seperateChaining(Node<String> value)
	{
		
	}
	
}
