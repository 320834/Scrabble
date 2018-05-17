
public class Node<E extends Comparable<E>> implements Comparable<Node<E>> {

	protected Node<E> left; // reference to the left subtree
	protected Node<E> right; // reference to the right subtree
	protected E data; // data item stored in the node

	protected int height;
	protected int desc; // num of descendants
	public int balanceFactor = 0;

	/**
	 * Constructs a BSTNode initializing the data part according to the parameter
	 * and setting both references to subtrees to null.
	 * 
	 * @param data
	 *            data to be stored in the node
	 */
	protected Node(E data) {
		this.data = data;
		left = null;
		right = null;
		height = 0;
		desc = 0;
		balanceFactor = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Node<E> other) {
		return this.data.compareTo(other.data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return data.toString();
	}

}