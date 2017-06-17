package diet;

import java.awt.Color;
import animals.Animal;

/**
 * This is an AbstractZooFactory class.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 */
public interface AbstractZooFactory 
{	
	public Animal produceAnimal(String foodtype);
	public void getValues(int size, int verSpeed, int horSpeed , Color c);
}
