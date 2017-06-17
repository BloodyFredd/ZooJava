package graphics;

import java.awt.Graphics;

/**
 * This is the IDrawable interface.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 */
public interface IDrawable {
	 public final static String PICTURE_PATH = "C:/Users/Freddy/Desktop/Eclipse/Workspace/Zoo2/photos";
	 public void loadImages(String nm);
	 public void drawObject (Graphics g);
	 public String getColor();	 

}
