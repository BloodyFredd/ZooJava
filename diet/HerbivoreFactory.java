package diet;

import graphics.ZooPanel;

import java.awt.Color;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;

/**
 * This is herbivore factory class to build factory of herbivores.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see AbstractZooFactory
 */
public class HerbivoreFactory implements AbstractZooFactory {

	public ZooPanel panel=null;
	public int size=0;
	public int verSpeed=0;
	public int horSpeed=0;
	public Color c=null;
	
	/**
	 * This is the constructor for the herbivore factory.
	 */
	public HerbivoreFactory()
	{		
	}
	
	/**
	 * This is function to produce a new Herbivore.
	 * @param type which type of Herbivore to build.
	 * @return the new animal.
	 */
	@Override
	public Animal produceAnimal(String type) {
		if(type.equals("Elephant"))
			return new Elephant("Elephant",2,size,verSpeed,horSpeed,c,"elf");
		else if(type.equals("Turtle"))
			return new Turtle("Turtle",20,size,verSpeed,horSpeed,c,"trt");
		else if(type.equals("Giraffe"))
			return new Giraffe("Giraffe",1.5,size,verSpeed,horSpeed,c,"grf");
		return null;
	}

	/**
	 * this is the setter for the new animal.
	 * @param size the size of the Herbivore.
	 * @param verSpeed the vertical speed of the Herbivore.
	 * @param horSpeed the horizontal speed of the Herbivore.
	 * @param c the color of the Herbivore.
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
