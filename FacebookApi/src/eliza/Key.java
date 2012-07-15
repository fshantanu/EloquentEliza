package eliza;

/**
 *  Representation of the  Eliza key.
 *  A key has the key itself, a rank, 
 *  and a list of decomposition rules.
 *  
 *  @author shantanu
 */

public class Key {
	
	/** 
	 * The key itself 
	 */
	private String key;

	/**
	 * The numerical rank 
	 */
	private int rank;
	
	/** 
	 * The list of decompositions 
	 */
	private DecompList decomp;

	/**
	 *  Initialize the key.
	 */
	public Key(String key, int rank, DecompList decomp) {
		this.key = key;
		this.rank = rank;
		this.decomp = decomp;
	}

	/**
	 *  Another initialization for gotoKey.
	 */
	Key() {
		key = null;
		rank = 0;
		decomp = null;
	}

	public void copy(Key k) {
		key = k.getKey();
		rank = k.rank();
		decomp = k.decomp();
	}

	/**
	 *  Print the key and all under it.
	 */
	public void print(int indent) {
		for (int i = 0; i < indent; i++) System.out.print(" ");
		System.out.println("key: " + key + " " + rank);
		decomp.print(indent+2);
	}

	/**
	 *  Print the key and rank only, not the rest.
	 */
	public void printKey(int indent) {
		for (int i = 0; i < indent; i++) System.out.print(" ");
		System.out.println("key: " + key + " " + rank);
	}

	/**
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @return Decomposition
	 */
	public DecompList getDecomp() {
		return decomp;
	}

	/**
	 *  Get the rank.
	 */
	public int rank() {
		return rank;
	}

	/**
	 *  Get the decomposition list.
	 */
	public DecompList decomp() {
		return decomp;
	}
}
