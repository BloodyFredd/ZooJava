package animals;

import java.awt.Color;
import mobility.Point;

/**
 * This is a animal that chews class in which we build an animal that chews.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see Point,Animal
 */

public abstract class AnimalThatChews extends Animal {
	
	/**
	 * This is a constructor for the class animal that chews.
	 * @param name the name of the animal.
	 * @param loacation the initial location of the animal.
	 */
	
	public AnimalThatChews(String Name,Point location, int size, int verSpeed, int horSpeed , Color c,String an){
		super(Name,location,size,verSpeed,horSpeed ,c,an);
	}
	
	/**
	 * This function is abstract,it is used for the sound that the animal makes when it chews, and we will implement it in the inheritors classes.
	 */
	public abstract void chew();
	
	/**
	* This function is used for the sound that the animal makes when it chews.
	*/
	@Override
	public void makeSound()
	{
		chew();
	}
	
}
