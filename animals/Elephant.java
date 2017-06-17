package animals;

import java.awt.Color;
import java.awt.Graphics;
import diet.Herbivore;
import mobility.Point;

/**
 * This is a elephant class in which we build a elephant.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see AnimalThatChews
 */

public class Elephant extends AnimalThatChews {

	/**
	 * This is a constructor for the class elephant.
	 * @param name the name of the elephant.
	 * @param trunkLength is the length of his trunk.
	 */
	public Elephant(String Name,double trunkLength,  int size, int verSpeed, int horSpeed , Color c,String an) {
		super(Name,new Point(50, 90),size,verSpeed,horSpeed ,c,an);
		super.setWeight(size*10);
		super.setDiet(new Herbivore());
	}
	/**
	 * This function is for the sound that the elephant makes.
	 */
	@Override
	public void chew() {
		System.out.println("Trumpets with joy while flapping its ears, then chews");
	}

	/**
	 * This function draws the elephant on the panel.
	 */
	@Override
	public void drawObject (Graphics g)
	{
	   g.setColor(col);
	   if(x_dir==1) // elephant goes to the right side
		g.drawImage(img1, location.getX()-size/2, location.getY()-size/10, size/2, size, pan);
	   else // elephant goes to the left side
		g.drawImage(img2, location.getX(), location.getY()-size/10, size/2, size, pan);
	}

}
