package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

 


import mobility.Point;
import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;

/**
 * This is a Meat class in which we build a Meat.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see IEdible, IDrawable
 */
public class Meat implements IEdible, IDrawable {
	
	protected double height;
	protected Point location;
	protected ZooPanel pan;
	protected BufferedImage img;
	private static Meat instance=null;

	/**
	 * This is the consturctor for the meat.
	 */
	private Meat() {
		Random rand = new Random();
		this.location = new Point(380, 225);
		this.height = rand.nextInt(30);
		pan=ZooPanel.getInstance();
		loadImages(null);
	}

	/**
	 * This is the function in which we load the image of the meat from.
	 */
	@Override
	public void loadImages(String nm) {
		try { img = ImageIO.read(new File(PICTURE_PATH + "/meat.gif")); } 
		 catch (IOException e) { System.out.println("Cannot load image"); }				
	}

	/**
	 * This is the function we draw the meat on the panel.
	 */
	@Override
	public void drawObject(Graphics g) {
		g.drawImage(img, pan.getWidth()/2,  pan.getHeight()/2, 40 ,40, pan);
		
	}

	/**
	 * This function returns the type of meat.
	 */
	@Override
	public EFoodType getFoodtype() {
		return EFoodType.MEAT;
	}

	/**
	 * This function is not needed in meat.
	 */
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This is the function which we get the instance of the meat.
	 * @return the instance.
	 */
	public static Meat getInstance()
	{
	     if (instance == null)
	          synchronized(Meat.class){   
	              if (instance == null)
	                  instance = new Meat();
	          }
	       return instance;
	}  

}
