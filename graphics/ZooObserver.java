package graphics;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import animals.Animal;

/**
 * This is the ZooObserver class in which we build a ZooObserver class which observes observable objects.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see Observer, Observable
 */
public class ZooObserver implements Observer{
	private ArrayList<Animal> ObservableAnimals;
	
	/**
	 * This is the constructor for the ZooObserver, which gets an array of animals to observe.
	 * @param obs
	 */
	public ZooObserver(ArrayList<Animal> obs) {
		ObservableAnimals = obs;
	}

	@Override
	/**
	 * This function is the update to the observer, each time an observable does something.
	 * @param obs this is an observable animal for the observer to observer.
	 * @param str this is the object which to show.
	 */
	public void update(Observable obs, Object str) {
		synchronized(this){
			if(obs instanceof Animal){
				if(((Animal) obs).getChanges())
			
			for(int j=0;j<ObservableAnimals.size();j++)
		    	 {			    		
		    		 Animal B=ObservableAnimals.get(j);
		    		 if(obs != null && B != null){
		    		 if(!(((Animal) obs).getAnimalName().equals(B.getAnimalName())))
		    		 {
		    			 if((((Animal) obs).calcDistance(B.getLocation()))<B.getSize())
		    			 {
		    				 if(((Animal) obs).getDiet().canEat(B.getFoodtype()))
		    				 {
		    					 if(((Animal) obs).getWeight()>=2*B.getWeight())
		    					 {
		    						 
		    						 ((Animal) obs).eatInc();
		    						 ZooPanel.getInstance().addEatCounter(1);
		    						 B.Interrupt();
		    						 ZooPanel.getInstance().addEatCounter(-B.getEatCount());
		    						 ObservableAnimals.remove(B);
		    						 ZooPanel.getInstance().LoopSize=ZooPanel.getInstance().Min(ZooPanel.getInstance().Animals.size(),5);
		    					 }
		    				 }
		    			 }
		    		 } 
		    		 }
		    	 }
				ZooPanel.getInstance().repaint();
			}
		}
	}
}
