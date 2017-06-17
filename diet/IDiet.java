package diet;
import food.EFoodType;
import food.IEdible;

/**
 * This is an IDiet class in which we build an IDiet.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see EFoodType
 */
public interface IDiet {
	/**
	 * This function is for whether the animal can eat the food or not,we will implement it in the inheritors classes.
	 * @param food the food that the animal is supposed to eat.
	 */
	public boolean canEat(EFoodType food);
	/**
	 * This is a function that describes the animal's eating procedure,we will implement it in the inheritors classes.
	 * @param animal the animal that is eating.
	 * @param food the plant that is supposed to be eaten.
	 */
	public double eat(double currentWeight, IEdible food);
}
