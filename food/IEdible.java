package food;
/**
 * This is an interface that represents IEdible(represents whether an animal is edible or not).
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 */
public interface IEdible {
	
	EFoodType E=null;
	/**
	 * This function is abstract,it is used for getting the food type that the animal eats,and we will implement it in the inheritors classes.
	 */
	
	public abstract EFoodType getFoodtype();
	
}
