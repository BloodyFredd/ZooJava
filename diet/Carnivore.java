package diet;

import food.EFoodType;
import food.IEdible;

/**
 * This is a carnivore class in which we build a carnivore.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see IDiet
 */
public class Carnivore implements IDiet {

	/**
	 * This is a constructor for the carnivore.
	 */
	public Carnivore()
	{}
	/**
	 * Weight gain factor after eating meat
	 */
	public static final double WEIGHT_GAIN_FACTOR = 0.1;
	/**
	 * This is a function for whether the animal can or can't eat the food.
	 * @param food the food that the animal is supposed to eat.
	 * @return if the animal had succefully ate or not.
	 */

	public boolean canEat(EFoodType food) {
		if(food == EFoodType.MEAT){
			return true;
		}
		return false;
	}

	/**
	 * This is a function that describes the animal's eating procedure.
	 * @param animal the animal that is eating.
	 * @param food the animal that is supposed to be eaten.
	 * @return if the animal had succefully ate or not.
	 */
	@Override
	public double eat(double currentWeight, IEdible food) {
		if (canEat(food.getFoodtype())) {
			return currentWeight * WEIGHT_GAIN_FACTOR;
		}
		return 0;
	}
	
	/**
	 * This function creates a string from the class.
	 * It overrides toString in object.
	 */
	
	public String toString(){
		return "[" + this.getClass().getSimpleName() + "]";
	}
}
