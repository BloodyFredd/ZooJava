package diet;
 
import food.EFoodType;
import food.IEdible;

/**
 * This is a herbivore class in which we build a herbivore.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see IDiet
 */

public class Omnivore implements IDiet {

	private IDiet canrivore;
	private IDiet herbivore;
	/**
	 * This is a constructor for the omnivore.
	 */
	public Omnivore()
	{
		this.canrivore = new Carnivore();
		this.herbivore = new Herbivore();
	}
	
	/**
	 * This is a function for whether the animal can or can't eat the food.
	 * @param food the food that the animal is supposed to eat.
	 * @return if the animal had succefully ate or not.
	 */
	
	public boolean canEat(EFoodType food) {
		return this.canrivore.canEat(food) || this.herbivore.canEat(food);
	}

	/**
	 * This is a function that describes the animal's eating procedure.
	 * @param animal the animal that is eating.
	 * @param food the animal/plant that is supposed to be eaten.
	 * @return if the animal had succefully ate or not.
	 */
	
	@Override
	public double eat(double currentWeight, IEdible food) {
		if (canEat(food.getFoodtype())) {
			return this.canrivore.eat(currentWeight, food) + this.herbivore.eat(currentWeight, food);
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
