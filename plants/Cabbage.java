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
public class Cabbage extends Plant {
	private static Cabbage instance=null;
	
	/**
	 * This is the constructor for the cabbage.
	 */
	private Cabbage() 
	{
		loadImages(null);
	}

	/**
	 * This is the function to load the cabbage image.
	 */
	@Override
	public void loadImages(String nm) {
			try { img = ImageIO.read(new File(PICTURE_PATH + "/cabbage.png")); } 
				catch (IOException e) { System.out.println("Cannot load image"); }
	}

	/**
	 * This is the function to draw the cabbage on the panel.
	 */
	@Override
	public void drawObject(Graphics g) 
	{
		g.drawImage(img,  pan.getWidth()/2,  pan.getHeight()/2, 40, 40, pan);		
	}
	
	/**
	 * This function returns the instance of the lettuce.
	 * @return the instance.
	 */
	public static Cabbage getInstance()
	{
	     if (instance == null)
	          synchronized(Cabbage.class){   
	              if (instance == null)
	                  instance = new Cabbage();
	          }
	       return instance;
	} 


}
