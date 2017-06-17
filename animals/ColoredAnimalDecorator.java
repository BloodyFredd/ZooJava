package animals;

/**
 * This is an colored animal decorator class.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see ColoredAnimal
 */
public class ColoredAnimalDecorator implements ColoredAnimal{

	protected ColoredAnimal C;
	
	/**
	 * This is the constructor for the colored animal decorator.
	 * @param Col
	 */
	public ColoredAnimalDecorator(ColoredAnimal Col)
	{
		this.C=Col;
	}
	
	/**
	 * This is an override for the paint animal, not in use.
	 */
	@Override
	public void PaintAnimal() {
	
	}


}
