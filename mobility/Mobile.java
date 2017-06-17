package mobility;

/**
 * This is a mobile class in which we show the mobility of an animal.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see ILocatable
 */

public abstract class Mobile implements ILocatable {
	protected Point location;
	private double totalDistance;
	
	/**
	 * This is a constructor for the class Mobile.
	 * @param point the starting point of an animal.
	 */
	public Mobile(Point point){
		location=new Point(0,0);
		location.setX(point.getX());
		location.setY(point.getY());
	}
	
	/**
	 * This function adds the parameter that she gets to the total distance.
	 * @param dis the distance that needs to be added.
	 */
	public boolean addTotalDistance(double distance){
		return this.setTotalDistance(distance + this.totalDistance);
	}
	
	/**
	 * This function sets the total distance.
	 * @param totalDistance the total distance of an object.
	 * @return if the set worked.
	 */
	private boolean setTotalDistance(double totalDistance) {
		if (totalDistance > 0) {
			this.totalDistance = totalDistance;
			return true;
		}
		return false;
	}
	
	/**
	 * @return The total distance
	 */
	public double getTotalDistance() {
		return totalDistance;
	}
	
	/**
	 * This function is a getter for the location of the animal.
	 * @return The location of the animal.
	 */
	
	public Point getLocation(){
		return location;
	}
	
	/**
	 * This function is a getter for the total distance that the animal traveled.
	 * @return The total distance that the animal traveled.
	 */
	
	public double getDistance(){
		return totalDistance;
	}		
	
}
