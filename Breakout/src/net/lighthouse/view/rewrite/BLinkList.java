package net.lighthouse.view.rewrite;
//JAVA
import java.util.ArrayList;
//ACM
import acm.graphics.GObject;
//LIGHTHOUSE
import net.lighthouse.model.BObject;

/**
 * An ArrayList for BLinks with additional BLink specific methods. Despite the
 * confusing name this class extends ArrayList and not LinkedList.
 * 
 * @author finite
 *
 */
class BLinkList extends ArrayList<BLink> {

	/**
	 * 
	 * @param o
	 *            the BObject to look for.
	 * @return {@code true} if the List contains a link to the BObject.
	 */
	boolean containsBObject(BObject o) {
		for (BLink b : this) {
			// this is actually supposed to be == and not equals.
			if (b.getB() == o) {
				return true;
			}
		}
		return false;
	}

	/**
	 * returns the GObject linked to a BObject.
	 * 
	 * @param o
	 *            the BObject linked to the GObject that is returned
	 * @return the GObject linked to the given BObject.
	 */
	GObject getGObject(BObject o) {
		for (BLink b : this) {
			// this is actually supposed to be == and not equals.
			if (b.getB() == o) {
				return b.getG();
			}
		}
		return null;
	}
}
