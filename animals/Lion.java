package animals;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import mobility.Point;
import food.EFoodType;
import food.IEdible;
import diet.Carnivore;


/**
 * This is a lion class in which we build a lion.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see AnimalThatRoars
 */

public class Lion extends AnimalThatRoars {
	private int scarCount;
	private Random rand;


	/**
	 * This is a constructor for the class lion.
	 * @param name the name of the lion.
	 */
	
	public Lion(String Name,int size, int verSpeed, int horSpeed , Color c,String an) {
		super(Name,new Point(20, 0),size,verSpeed,horSpeed ,c,an);
		scarCount = 0;
		super.setWeight(size*0.8);
		super.setDiet(new Carnivore());
		this.rand = new Random(2364624);
	}
	
	/**
	 * This function is a setter for scarcount of the lion.
	 * @param scarcount is the amount of the lions's scars.
	 * @return a boolean if the setter worked.
	 */
	
	public boolean setScars(int num){
		this.scarCount += num;
		return true;
	}

	/**
	 * This function is a getter for Foodtype that the lion is.
	 * @return The Foodtype of that the lion is.
	 */
	
	@Override
	public EFoodType getFoodtype() {
		return EFoodType.NOTFOOD;
	}
	
	/**
	 * This function is for the sound that the lion makes.
	 */

	@Override
	public void roar() {
		System.out.println("Roars , then stretches and shakes its mane");
	}
	
	/**
	 * This function is the eating function of the bear.
	 * @param food is the food type that it supposed to eat.
	 * @return if the eating had succeeded or not.
	 */

	@Override
	public boolean eat(IEdible food) {
		boolean isSuccess = super.eat(food);
		if (isSuccess && rand.nextBoolean()) {
			this.setScars(this.scarCount + 1);
		}
		return isSuccess;

	}


	/**
	 * This function draws the lion on the panel.
	 */
	@Override
	public void drawObject(Graphics g) {
		g.setColor(col);
		   if(x_dir==1) // lion goes to the right side
			g.drawImage(img1, location.x-size/2, location.y-size/10, size/2, size, pan);
		   else // lion goes to the left side
			g.drawImage(img2, location.x, location.y-size/10, size/2, size, pan);	
	}

	



}
