public class HashTable{

	//Table full of nodes
	Node<String>[] hashTable = null;
	private int mod;
	
	public HashTable()
	{
		hashTable = new Node[10];
		mod = 10;
	}
	
	public HashTable(int x)
	{
		hashTable = new Node[x];
		mod = x;
	}
	
	public void add(String word)
	{
		Node<String> current = new Node<String>(word);
		
		hash(word,current);
	}
	
	public int hash(String key, Node<String> value)
	{
		int preIndex = key.hashCode();
		
		int index = preIndex % mod;
		
		if(index < 0)
		{
			index = index * -1;
		}
		
		if(hashTable[index] == null)
		{
			hashTable[index] = value;
		}
		else
		{
			//If there is a collision at this index
			seperateChaining(index, value);
		}
		
		return index;
	}
	
	private void seperateChaining(int index, Node<String> value)
	{
		//Simply adds to the tree
		addTree(hashTable[index],value.data);
	}
	
	private Node<String> addTree(Node<String> root, String value)
	{
		if(root == null)
		{
			return new Node<String>(value);
		}
		else if(root.data.compareToIgnoreCase(value) > 0)
		{
			root.left = addTree(root.left,value);
		}
		else if(root.data.compareToIgnoreCase(value) < 0)
		{
			root.right = addTree(root.right, value);
		}
		else if(root.data.compareToIgnoreCase(value) == 0)
		{
			return root;
		}
		
		return root;
	}
	
	//This method finds the word of the string 
	public boolean find(String word)
	{
		int preIndex = word.hashCode();
		
		int index = preIndex % mod;
		
		if(hashTable[index] == null)
		{
			return false;
		}
		
		if(hashTable[index].data.compareToIgnoreCase(word) == 0)
		{
			return true;
		}
		else if(hashTable[index].right != null || hashTable[index].left != null)
		{
			return findTree(hashTable[index],word);
		}
		
		return false;
		
	}
	
	private boolean findTree(Node<String> root, String word)
	{
		if(root.data.compareToIgnoreCase(word) == 0)
		{
			return true;
		}
		else if(root.data.compareToIgnoreCase(word) > 0)
		{
			return findTree(root.left,word);
		}
		else if(root.data.compareToIgnoreCase(word) < 0 )
		{
			return findTree(root.right,word);
		}
		
		return false;
	}
	
	public static void main(String args[])
	{
		HashTable a = new HashTable(300000);
		
		a.add("ab");
		a.add("ba");
		
		System.out.println(a.find("aefe"));
	}
	
}
