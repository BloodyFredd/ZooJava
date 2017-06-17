package plants;

import java.awt.image.BufferedImage;
import java.util.Random;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;
 

/**
 * This is a plant class in which we build a plant.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see IEdible, ILocatable , IDrawable
 */
public abstract class Plant implements IEdible, ILocatable , IDrawable {

	protected double height;
	protected Point location;
	private double weight;
	protected ZooPanel pan;
	protected BufferedImage img;
	
	/**
	 * This is the constructor of the plant.
	 */
	public Plant() {
		Random rand = new Random();
		this.location = new Point(380, 225);
		this.height = rand.nextInt(30);
		this.weight = rand.nextInt(12);
		pan=ZooPanel.getInstance();
	}

	/**
	 * This function returns the food type.
	 */
	@Override
	public EFoodType getFoodtype() {
		return EFoodType.VEGETABLE;
	}

	/**
	 * This function is a getter for the height of the plant.
	 * @return the height of the plant.
	 */
	public double getHeight() {
		return this.height;
	}

	/**
	 * This function returns the location of the plant.
	 */
	@Override
	public Point getLocation() {
		return this.location;
	}

	/**
	 * This function is a getter for the weight of the plant.
	 * @return the weight of the plant.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * This function is a setter for the height of the plant.
	 * @param height is the height of the plant.
	 * @return a boolean if the setter worked.
	 */
	public boolean setHeight(double height) {

		boolean isSuccess = (height >= 0);
		if (isSuccess) {
			this.height = height;
		} else {
			this.height = 0;
		}
		return isSuccess;
	}

	/**
	 * This function is a setter for the weight of the plant.
	 * @param weight is the weight of the plant.
	 * @return a boolean if the setter worked.
	 */
	public boolean setWeight(double weight) {
		boolean isSuccess = (weight >= 0);
		if (isSuccess) {
			this.weight = weight;
		} else {
			this.weight = 0;
		}

		return isSuccess;
	}

	/**
	 * This function returns the string.
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}
	
	/**
	 * This function is not needed in plant.
	 */
	@Override
	public String getColor() {
		return null;
	}

}
