package mobility;

import graphics.ZooPanel;

/**
 * This is a point class.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 */
public class Point {
	public int x;
	public int y;
	
	/**
	 * This is the constructor for the point.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 */
	public Point(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public Point(Point newLocation) {
		this(newLocation.x, newLocation.y);
	}

	/**
	 * This is the getter for the x.
	 * @return the x coordinate of the point.
	 */
	public int getX() {
		return x;
	}

	/**
	 * This is the setter for the x.
	 * @param x the x coordinate of the point.
	 * @return a boolean if the set worked.
	 */
	public boolean setX(int x) {
			this.x = x;
		return true;
	}

	/**
	 * This is the getter for the y.
	 * @return the y coordinate of the point.
	 */
	public int getY() {
		return y;
	}

	/**
	 * This is the setter for the y.
	 * @param y the y coordinate of the point.
	 * @return a boolean if the set worked.
	 */
	public boolean setY(int y) {
			this.y=y;
		return true;
	}

	/**
	 * This function checks if the point she gets is within the boundaries that had been set.
	 * @param point is the point we want to check.
	 * @return if the point is in the boundaries or not.
	 */
	public static boolean cheackBounderies(Point newLocation,ZooPanel p) {
		if(newLocation.y>=0 && newLocation.y<=p.getHeight() && (newLocation.x>=0 && newLocation.x<=p.getWidth()))
			return true;
		return false;
	}
	
	/**
	 * This function creates a string from the class.
	 * It overrides toString in object.
	 */
	
	public String toString(){
		return "(" + getX() + ", " + getY() + ")";
	}

}
