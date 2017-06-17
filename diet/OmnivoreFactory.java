package diet;

import graphics.ZooPanel;
import java.awt.Color;
import animals.Animal;
import animals.Bear;

/**
 * This is omnivore factory class to build factory of omnivores.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see AbstractZooFactory
 */
public class OmnivoreFactory implements AbstractZooFactory{

	public ZooPanel panel=null;
	public int size=0;
	public int verSpeed=0;
	public int horSpeed=0;
	public Color c=null;
	
	/**
	 * This if the constructor for the omnivore.
	 */
	public OmnivoreFactory()
	{	
	}
	
	/**
	 * This is function to produce a new omnivore.
	 * @param type which type of omnivore to build.
	 * @return the new animal.
	 */
	@Override
	public Animal produceAnimal(String type) {
		if(type.equals("Bear"))
			return new Bear("Bear","Brown",size,verSpeed,horSpeed,c,"bea");
		return null;
	}
	
	/**
	 * this is the setter for the new animal.
	 * @param size the size of the omnivore.
	 * @param verSpeed the vertical speed of the omnivore.
	 * @param horSpeed the horizontal speed of the omnivore.
	 * @param c the color of the omnivore.
	 */
	@Override
	public void getValues(int size, int verSpeed, int horSpeed , Color c)
	{

		this.size=size;
		this.verSpeed=verSpeed;
		this.horSpeed=horSpeed;
		this.c=c;
	}
}
