package animals;

import java.awt.Color;
import java.awt.Graphics;
import diet.Herbivore;
import mobility.Point;

/**
 * This is a giraffe class in which we build a giraffe.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see AnimalThatChews
 */

public class Giraffe extends AnimalThatChews {

	/**
	 * This is a constructor for the class giraffe.
	 * @param name the name of the giraffe.
	 * @param neckLength is the length of his neck.
	 */
	
	public Giraffe(String Name,double neckLength,  int size, int verSpeed, int horSpeed , Color c,String an) {
		super(Name,new Point(50, 0),size,verSpeed,horSpeed ,c,an);
		super.setWeight(size*2.2);
		super.setDiet(new Herbivore());
	}
	
	/**
	 * This function is a getter for Foodtype that the giraffe is.
	 * @return The Foodtype of that the giraffe is.
	 */
	

	
	/**
	 * This function is for the sound that the giraffe makes.
	 */
	
	@Override
	public void chew() {
		System.out.println("Bleats and Stomps its legs, then chews");
	}
	
/**
 * This function draws the giraffe on the panel.
 */
	public void drawObject (Graphics g)
	{
	   g.setColor(col);
	   if(x_dir==1) // giraffe goes to the right side
		g.drawImage(img1, location.getX()-size/2, location.getY()-size/10, size/2, size, pan);
	   else // giraffe goes to the left side
		g.drawImage(img2, location.getX(), location.getY()-size/10, size/2, size, pan);
	}









}
