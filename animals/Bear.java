package animals;

import java.awt.Color;
import java.awt.Graphics;
import mobility.Point;
import diet.Omnivore;

/**
 * This is a Bear class in which we build a Bear.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see AnimalThatRoars
 */
public class Bear extends AnimalThatRoars {
	
	/**
	 * This is a constructor for the class Bear.
	 * @param name the name of the bear.
	 * @param color the color of the bear.
	 */	
	public Bear(String Name,String color,  int size, int verSpeed, int horSpeed , Color c,String an) {
		super(Name,new Point(100, 5),size,verSpeed,horSpeed ,c,an);
		super.setWeight(size*1.5);
		super.setDiet(new Omnivore());
	}
	
	/**
	 * This function is for the sound that the bear makes.
	 */
	@Override
	public void roar() {
		System.out.println("Stands on its hind legs, roars and scratches its belly");		
	}

	/**
	 * This function draws the bear on the panel.
	 */
	@Override
	public void drawObject (Graphics g)
	{
	   g.setColor(col);
	   if(x_dir==1) // bear goes to the right side
		g.drawImage(img1, location.getX()-size/2, location.getY()-size/10, size/2, size, pan);
	   else // bear goes to the left side
		g.drawImage(img2, location.getX(), location.getY()-size/10, size/2, size, pan);
	}





	
	

}
