package graphics;

/**
 * This is the IAnimalBehavior interface.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 */
public interface IAnimalBehavior {
    public String getAnimalName();
	 public int getSize();
	 public void eatInc();
	 public int getEatCount();
	 public boolean getChanges ();
	 public void setSuspended();
	 public void setResumed();
	 public void setChanges (boolean state);

}
