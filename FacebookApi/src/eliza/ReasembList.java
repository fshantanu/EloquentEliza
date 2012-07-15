package eliza;

import java.util.Vector;

/**
 *  Eliza reassembly list.
 */
public class ReasembList extends Vector<String> {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -6871704540410875284L;

	/**
	 *  Add an element to the reassembly list.
	 */
	public boolean add(String reasmb) {
		addElement(reasmb);
		return true;
	}

	/**
	 *  Print the reassembly list.
	 */
	public void print(int indent) {
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < indent; j++) 
				System.out.print(" ");
			String s = (String)elementAt(i);
			System.out.println("reasemb: " + s);
		}
	}
}
