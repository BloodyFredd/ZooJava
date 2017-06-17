package graphics;

import java.util.ArrayList;
import java.util.Observable;

import plants.Meat;
import plants.Plant;
import animals.Animal;

/**
 * This is the ZooMemento class in which we build a ZooMemento to remember the states of the animals.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 */
public class ZooMemento {
		protected ArrayList<Animal> Animals;
		protected Plant P;
		protected Meat M;
		private boolean ThereIsFood;
		
		/**
		 * This is the constructor for the ZooMemento.
		 * @param QOfA the array of the animals in the queue.
		 * @param A the array of the animals.
		 * @param P a plant.
		 * @param M a meat.
		 * @param ThereIsFood boolean to check if there is food.
		 */
		public ZooMemento(ArrayList<Animal> A,Plant P,Meat M,boolean ThereIsFood)
		{	
			synchronized(this){
			Animals=new ArrayList<Animal>(10);
	   	 	for(int i=0;i<A.size();i++)
	   	 	{
	   	 		Animal a=(Animal) A.get(i).clone();
	   	 		Animals.add(a);
	   	 	}			
	   	 	if(ThereIsFood)
	   	 	{
	   	 		this.ThereIsFood=ThereIsFood;
	   	 		if(P!=null)
	   	 		{
	   	 			this.P=P;
	   	 		}
	   	 		else
	   	 		{
	   	 			this.M=M;
	   	 		}
	   	 	}	
			}
				
		}
		
		/**
		 * This is the setter to save the states of the animals.
		 * @param QOfA the array of the animals in the queue.
		 * @param A the array of the animals.
		 * @param P a plant.
		 * @param M a meat.
		 * @param ThereIsFood boolean to check if there is food.
		 * @return the object after the set.
		 */
		public synchronized ZooMemento getState(ArrayList<Animal> A)
		{   	 	
	   	 	for(int i=0;i<A.size();i++)
	   	 	{
	   			A.get(i).setSuspended();
	   			if(i<5)
	   				{	A.get(i).Interrupt();
	   					A.get(i).deleteObserver(ZooPanel.getInstance().obs);
	   				}
	   	 	}
			A.clear();
	   	 	for(int i=0;i<Animals.size();i++)
	   	 	{
	   	 		A.add(Animals.get(i));
	   	 		if(i<5)
	   	 		{
	   	 		((Observable) A.get(i)).addObserver(ZooPanel.getInstance().obs);
	   	 		}
	   	 	}
			
			return this;
		}
		
		/**
		 * This is the getter for the there is food boolean.
		 * @return boolean of there is food.
		 */
		public synchronized boolean getThereIsFood()
		{
			return ThereIsFood;
		}
		
		/**
		 * This is the getter for the plant.
		 * @return the plant.
		 */
		public synchronized Plant getP()
		{
			return P;
		}
		
		/**
		 * this is the getter for the meat.
		 * @return the meat.
		 */
		public synchronized Meat getM()
		{
			return M;
		}
}
