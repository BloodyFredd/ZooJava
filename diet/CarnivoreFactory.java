package diet;

import java.awt.Color;
import graphics.ZooPanel;
import animals.Animal;
import animals.Lion;

/**
 * This is carnivore factory class to build factory of carnivores.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see AbstractZooFactory
 */
public class CarnivoreFactory implements AbstractZooFactory {
	public ZooPanel panel=null;
	public int size=0;
	public int verSpeed=0;
	public int horSpeed=0;
	public Color c=null;

	/**
	 * This is the constructor for the carnivore factory.
	 */
	public CarnivoreFactory()
	{
	}
	
	/**
	 * This is function to produce a new carnivore.
	 * @param type which type of carnivore to build.
	 * @return the new animal.
	 */
	@Override
	public Animal produceAnimal(String type) {
		if(type.equals("Lion")){
			return new Lion("Lion",size,verSpeed,horSpeed,c,"lio");			
		}
		return null;		
	}
	
	/**
	 * this is the setter for the new animal.
	 * @param size the size of the carnivore.
	 * @param verSpeed the vertical speed of the carnivore.
	 * @param horSpeed the horizontal speed of the carnivore.
	 * @param c the color of the carnivore
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
