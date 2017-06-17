package mobility;

/**
 * This is an interface that represents ILocatable(represents the location of the animal).
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 */

public interface ILocatable {
	
	/**
	 * This is an abstract getter function for the animal's location,and we will implement it in the inheritors classes.
	 */
	public abstract Point getLocation();
	/**
	 * This is an abstract setter function for the animal's location,and we will implement it in the inheritors classes.
	 */
	//public abstract boolean setLocation(Point p);
}
