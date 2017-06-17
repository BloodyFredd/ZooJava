package animals;

import java.awt.Color;
import java.awt.Graphics;
import diet.Herbivore;
import mobility.Point;

/**
 * This is a turtle class in which we build a turtle.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see AnimalThatChews
 */

public class Turtle extends AnimalThatChews {

	/**
	 * This is a constructor for the class turtle.
	 * @param name the name of the turtle.
	 * @param age is the age of the turtle.
	 */
	
	public Turtle(String Name,int age,  int size, int verSpeed, int horSpeed , Color c,String an) {
		super(Name,new Point(80, 0),size,verSpeed,horSpeed ,c,an);
		super.setWeight(size*0.5);
		super.setDiet(new Herbivore());
	}

	/**
	 * This function is for the sound that the turtle makes.
	 */
	
	@Override
	public void chew() {
		System.out.println("Retracts its head in then eats quietly");
	}

	/**
	 * This function draws the object on the panel.
	 */
	@Override
	public void drawObject (Graphics g)
	{
	   g.setColor(col);
	   if(x_dir==1) // turtle goes to the right side
		g.drawImage(img1, location.getX()-size/2, location.getY()-size/10, size/2, size, pan);
	   else // turtle goes to the left side
		g.drawImage(img2, location.getX(), location.getY()-size/10, size/2, size, pan);
	}





}
