package eliza;

import java.lang.Math;
import java.util.Random;

/**
 * Represents a Decomposition rule in Eliza
 * @author shantanu
 *
 */
public class Decomp {
	
	/** 
	 * The decomp pattern
	 */
	private String pattern;
	
	/** 
	 * The memory flag. If the flag is set, then the
	 * reassembly logic is saved to memory and used 
	 * if another key is not found. 
	 */
	private boolean mem;
	
	/** 
	 * The reassembly list for the given
	 * decomposition pattern 
	 * 
	 */
	private ReasembList reasemb;
	
	/** 
	 * The current reassembly point
	 */
	int currReasmb;

	/**
	 *  Initialize the decomp rule
	 */
	public Decomp(String pattern, boolean mem, ReasembList reasemb) {
		this.pattern = pattern;
		this.mem = mem;
		this.reasemb = reasemb;
		this.currReasmb = 100;
	}

	/**
	 *  Print out the decomp rule.
	 */
	public void print(int indent) {
		for (int i = 0; i < indent; i++) 
			System.out.print(" ");
		System.out.println("decomp: " + pattern + " " + mem);
		reasemb.print(indent + 2);
	}

	/**
	 *  Get the pattern.
	 */
	public String pattern() {
		return pattern;
	}

	/**
	 *  Get the mem flag.
	 */
	public boolean mem() {
		return mem;
	}

	/**
	 *  Get the next reassembly rule.
	 */
	public String nextRule() {
		if (reasemb.size() == 0) {
			System.out.println("No reassembly rule.");
			return null;
		}
		return (String)reasemb.elementAt(currReasmb);
	}
	
	public String randomRule(){
		if (reasemb.size() == 0) {
			System.out.println("No reassembly rule.");
			return null;
		}
		Random random = new Random();
		return reasemb.get(random.nextInt(reasemb.size()));
	}

	/**
	 *  Step to the next reassembly rule.
	 *  If mem is true, pick a random rule.
	 */
	public void stepRule() {
		int size = reasemb.size();
		if (mem) {
			currReasmb = (int)(Math.random() * size);
		}
		//  Increment and make sure it is within range.
		currReasmb++;
		if (currReasmb >= size) currReasmb = 0;
	}
}
