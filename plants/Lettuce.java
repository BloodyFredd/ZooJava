package plants;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This is a Cabbage class in which we build a Cabbage.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see Plant
 */
public class Lettuce extends Plant {
	private static Lettuce instance=null;
		
	/**
	 * This is the constructor for the Lettuce.
	 */
	private Lettuce()
	{	
		loadImages(null);
	}

	/**
	 * This is the function to load the image of the lettuce.
	 */
	@Override
	public void loadImages(String nm) {
			try { img = ImageIO.read(new File(PICTURE_PATH + "/lettuce.png")); } 
			 catch (IOException e) { System.out.println("Cannot load image"); }		
	}

	/**
	 * This is the function to draw the lettuce on the panel.
	 */
	@Override
	public void drawObject (Graphics g)
	{
		g.drawImage(img,  pan.getWidth()/2,  pan.getHeight()/2, 40, 40, pan);
	}
	
	/**
	 * This function returns the instance of the lettuce.
	 * @return the instance.
	 */
	public static Lettuce getInstance()
	{
	     if (instance == null)
	          synchronized(Lettuce.class){   
	              if (instance == null)
	                  instance = new Lettuce();
	          }
	       return instance;
	}  

}
