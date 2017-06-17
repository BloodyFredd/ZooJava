package animals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.Point;

/**
 * This is a animal class in which we build an animal.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see IEdible, IDrawable, IAnimalBehavior, Runnable, ColoredAnimal,Cloneable, Observable
 */
public abstract class Animal extends Observable implements IEdible, IDrawable, IAnimalBehavior, Runnable, ColoredAnimal,Cloneable {
	protected String name;
	private double weight;
	private IDiet diet;		
	protected final int EAT_DISTANCE = 5;
	protected int size;
	protected Color col;
	protected int horSpeed;
	protected int verSpeed;
	protected boolean coordChanged;
	protected int x_dir;
	protected int y_dir;
	protected int eatCount;
	protected ZooPanel pan;
	protected boolean threadSuspended;	 
	protected BufferedImage img1, img2;
	private boolean state;
	private double v_old, v_hor_new, v_ver_new, k;
	protected Point location;
	private Future<?> task;
	
	/**
	 * This is a constructor for the class animal.
	 * @param name the name of the animal.
	 * @param loacation the initial location of the animal.
	 */	
	public Animal(String Name,Point location, int size, int verSpeed, int horSpeed , Color c,String an){
		setLocation(location);
		setSize(size);
		setVerSpeed(verSpeed);
		setHorSpeed(horSpeed);
		setCol(c);
		setName(Name);
		pan=ZooPanel.getInstance();
		threadSuspended = false;
		x_dir=1;
		y_dir=1;
		this.setEatCount(0);
		loadImages(an);
		setChanges(true);
	}
	
/**
 * This is the getter for the size.
 * @return the size of the animal.
 */
	public int getSize() {
		return size;
	}

/**
 * This is the setter for the size.
 * @param size sets the size of the animal.
 * @return boolean if setter worked.
 */
	public boolean setSize(int size) {
		if(size>=50 && size<=300)
		{
			this.size = size;
			return true;
		}
		size=50;
		return false;
	}

	/**
	 * This is the getter for the color of the animal.
	 * @return the color of the animal.
	 */
	public Color getCol() {
		return col;
	}

	/**
	 * This is the setter for the animal.
	 * @param col sets this color for the animal.
	 * @return boolean if setter worked.
	 */
	public boolean setCol(Color col) {
			this.col = col;
			return true;
	}

	/**
	 * This is the getter for the horizontal speed of the animal.
	 * @return the horizontal speed.
	 */
	public int getHorSpeed() {
		return horSpeed;
	}

	/**
	 * This is the setter for the horizontal speed.
	 * @param horSpeed sets the horizontal speed.
	 * @return boolean if the setter worked.
	 */
	public boolean setHorSpeed(int horSpeed) {
		if(horSpeed>=1 && horSpeed<=10)
		{
			this.horSpeed = horSpeed;
			return true;
		}
		this.horSpeed=1;
		return false;
	}

	/**
	 * This is the getter for the vertical speed of the animal.
	 * @return the vertical speed.
	 */
	public int getVerSpeed() {
		return verSpeed;
	}

	/**
	 * This is the setter for the vertical speed.
	 * @param horSpeed sets the vertical speed.
	 * @return boolean if the setter worked.
	 */
	public boolean setVerSpeed(int verSpeed) {
		if(verSpeed>=1 && verSpeed<=10)
		{
			this.verSpeed = verSpeed;
			return true;
		}
		this.verSpeed=1;
		return false;
	}

	/**
	 * getter for the direction of the x.
	 * @return the direction.
	 */
	public int getX_dir() {
		return x_dir;
	}

	/**
	 * The setter for the direction of x.
	 * @param x_dir sets the new direction.
	 */
	public void setX_dir(int x_dir) {
		this.x_dir = x_dir;
	}

	/**
	 * getter for the direction of the y.
	 * @return the direction.
	 */
	public int getY_dir() {
		return y_dir;
	}

	/**
	 * The setter for the direction of y.
	 * @param y_dir sets the new direction.
	 */
	public void setY_dir(int y_dir) {
		this.y_dir = y_dir;
	}

	/**
	 * getter for the eat count of the animal.
	 * @return the eat count of the animal.
	 */
	public int getEatCount() {
		return eatCount;
	}

	/**
	 * This is the setter for the eat counter.
	 * @param eatCount which eat counter to set.
	 */
	public void setEatCount(int eatCount) {
		this.eatCount = eatCount;
	}
	
	/**
	 * This function is a setter for the diet of the animal.
	 * @param die is the diet of the animal.
	 * @return a boolean if the setter worked.
	 */	
	public boolean setDiet(IDiet die){
		this.diet = die;
		return true;
	}
	
	/**
	 * This function is a getter for the diet of the animal.
	 * @return the diet of the animal.
	 */
	public IDiet getDiet(){
		return diet;
	}
	
	@Override
	/**
	 * This function is a getter for the name of the animal.
	 * @return the name of the animal.
	 */
	public String getAnimalName(){
		return name;
	}
	
	/**
	 * This function is a setter for the name of the animal.
	 * @param name is the name of the animal.
	 * @return a boolean if the setter worked.
	 */	
	public boolean setName(String name){
		this.name = name;
		return true;
	}
	
	/**
	 * This function is a getter for the weight of the animal.
	 * @return the weight of the animal.
	 */	
	public double getWeight(){
		return weight;
	}
	
	/**
	 * This function is a setter for the weight of the animal.
	 * @param weight is the weight of the animal.
	 * @return a boolean if the setter worked.
	 */	
	public boolean setWeight(double weight){
		if(weight > 0){
			this.weight = weight;
			return true;
		}
		this.weight = 1;
		return false;
	}
	
	/**
	 * This function is abstract,it is used for the sound that the animal makes, and we will implement it in the inheritors classes.
	 */
	public abstract void makeSound();
	
	/**
	 * This function is used for the eating of the animal.
	 */	
	public boolean eat(IEdible food){
		double gainedWeight = this.diet.eat(this.weight, food);
		if (gainedWeight > 0) {
			this.weight += gainedWeight;
			this.makeSound();
			return true;
		}
		return false;
	}
	
	/**
	 * This function is for calculating the distance that the animal had traveled.
	 * @param point is the point from which we start calculating.
	 * @return the distance that the animal had traveled.
	 */	
	public double calcDistance(Point point){
		return Math.sqrt(Math.pow(point.getX() - this.getLocation().getX(), 2) + Math.pow(point.getY() - this.getLocation().getY(), 2));
	}	
	
	/**
	 * This function creates a string from the class.
	 * It overrides toString in object.
	 */	
	public String toString(){
		return "[" + this.getClass().getSimpleName() + "] " + this.name;
	}

	/**
	 * This function is a getter for Foodtype that the turtle is.
	 * @return The Foodtype of that the turtle is.
	 */
	@Override
	public EFoodType getFoodtype() {
		return EFoodType.MEAT;
	}
	
	/**
	 * This function is for incrementing the eat count of the animal.
	 */
	@Override
	public void eatInc() {
		eatCount++;		
	}
	
	/**
	 * This function is for getting the current state of the animal(if it has changes or not).
	 */
	@Override
	public boolean getChanges() {
		return state;
	}
	
	/**
	 * This function is for setting the current state of the animal to false - means stopping the animal.
	 */
	@Override
	public void setSuspended() {
		threadSuspended = true;
		setChanges(false);
		
	}
	
	/**
	 * This function is for getting the current state of the animal(if it is suspended or not).
	 */
	public boolean getSuspended(){
		return threadSuspended;
		
	}
	
	/**
	 * This function is for setting the current state of the animal to true - means resuming the animal.
	 */	
	@Override
	public synchronized void setResumed() {
		threadSuspended = false;
		notify();		
	}

	/**
	 * This function is for setting the current state of the animal to true of false - true-if the animal had changed , false- if the animal hasn't changed.
	 * @param p-the state we want to change to
	 */
	@Override
	public void setChanges(boolean p) {
		state=p;
		
	}
	/**
	 * This function for the running of the animal, this is what the thread do,
	 *  in this function we check if there is food for the animal,
	 *   if there is food the animal moves towards it, else it just keeps moving normally.
	 */
	public void run(){
		while(Thread.currentThread().isAlive()){// run while the thread is alive.
			try { //while the thread runs we try to make the thread sleep.
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				return;
			}
			synchronized(this){
			if(threadSuspended){ // if the thread is suspended, try to wait.
				try {
					wait();
				}
				catch (InterruptedException e) {
					return;		
				}			
			}
						
			// if the panel have food that the specific animal can eat, run towards it.
			if(pan.getThereIsFood()==true && this.getDiet().canEat((pan.getFood().getFoodtype())))
			{
			v_old = Math.sqrt(Math.pow(this.getHorSpeed(),2) + Math.pow(this.getVerSpeed(),2));
			if(location.x == pan.getWidth()/2)
			{
				k = Math.abs(location.y - pan.getHeight()/2);
			}
			else
				k = Math.abs((location.y - pan.getHeight()/2) / (location.x - pan.getWidth()/2));
			v_hor_new = v_old / Math.sqrt(k*k+1);
			v_ver_new = v_hor_new * k;
			if(location.x>pan.getWidth()/2)
				   x_dir = -1;
			else
				   x_dir=1;
			if(location.y>pan.getHeight()/2)
				   y_dir=-1;
			else
				   y_dir=1;
							
			if(v_ver_new > 10)
			{
				v_ver_new = 10;
			}
			else if(v_ver_new < 1)
			{
			   if(location.y != pan.getHeight()/2)
				   v_ver_new = 1;   
			   else
				   v_ver_new = 0;  
			}
			if(v_hor_new > 10)
			{
			  v_hor_new = 10;
			}
			else if(v_hor_new < 1)
			{
			   if(location.x != pan.getWidth()/2)
			     v_hor_new = 1;   
			   else
			    v_hor_new = 0;  
			}
			location.x +=(int)v_hor_new*x_dir;
			location.y +=(int)v_ver_new*y_dir;
			if((Math.abs(location.x-pan.getWidth()/2) <= 10) && (Math.abs(location.y-pan.getHeight()/2) <= 10))
			{
				this.eat(pan.getFood());
				pan.setFood(pan.getFood());
				pan.setThereIsFood(false);
				eatInc();
				pan.addEatCounter(1);
			}			
		}		
			else{ // if no food on the panel, keep running on the panel.
				this.location.setX(location.getX()+ horSpeed *x_dir);
				this.location.setY(location.getY()+ verSpeed *y_dir);

				if(location.getX() > pan.getWidth()-size/4)
					x_dir = -1;
				else if(location.getX() <  size*0.25)
	 				x_dir = 1;
				if(location.getY() > (int) (pan.getHeight()-30 - size*9/10))
			    		y_dir = -1;
				else if(location.getY() < size/10) 
					y_dir = 1;		
			}
			setChanges(true);
			setChanged();
			this.notifyObservers();
			}
		}
	}

	/**
	 * This function returns the color of the animal.
	 */
	public String getColor(){
		if(Color.red == col)
			return "Red";
		if(Color.blue == col)
			return "Blue";
		return "Natural";
	}

/**
 * This function calls the interrupt for the animal to stop it.
 */
	public void Interrupt() {
		task.cancel(true);
	}
/**
 * This function is the setter for task from the thread pool.
 * @param thisTask
 */
	public void setTask(Future<?> thisTask){
		task = thisTask;
		setChanged();
	}

/**
 * This function is for the loading of the image, according to its color.
 */
	@Override
	public void loadImages(String nm) {
		String c=getColor();
		if(c == "Blue"){
			 try { img1 = ImageIO.read(new File(PICTURE_PATH + "/" +nm +"_b_1.png")); } 
			  catch (IOException e) { System.out.println("Cannot load image"); }
			 try { img2 = ImageIO.read(new File(PICTURE_PATH + "/" +nm +"_b_2.png")); } 
			  catch (IOException e) { System.out.println("Cannot load image"); }
			}
		else if(c == "Red"){
				 try { img1 = ImageIO.read(new File(PICTURE_PATH + "/" +nm +"_r_1.png")); } 
				  catch (IOException e) { System.out.println("Cannot load image"); }
				 try { img2 = ImageIO.read(new File(PICTURE_PATH + "/" +nm +"_r_2.png")); } 
				  catch (IOException e) { System.out.println("Cannot load image"); }
				}
		else if(c == "Natural"){
				 try { img1 = ImageIO.read(new File(PICTURE_PATH + "/" +nm +"_n_1.png")); } 
				  catch (IOException e) { System.out.println("Cannot load image"); }
				 try { img2 = ImageIO.read(new File(PICTURE_PATH + "/" +nm +"_n_2.png")); } 
				  catch (IOException e) { System.out.println("Cannot load image"); }
				}		
	}
	
	/**
	 * This function is the setter for the location of the animal.
	 * @param location
	 */
	public void setLocation(Point location){
		this.location = location;
	}

	/**
	 * This function is the getter of the location of the animal.
	 * @return the location.
	 */
	public Point getLocation(){
		return this.location;
	}
	
	/**
	 * This function is to check if the thread is suspended.
	 * @return a string of the suspension.
	 */
	public String getSus()
	{
		if(threadSuspended==false)
			return "true";
		return "false";
	}

	/**
	 * This function paints an animal according to its type.
	 */
	public void PaintAnimal() {
		if(this instanceof Lion)
		loadImages("lio");
		if(this instanceof Bear)
		loadImages("bea");
		if(this instanceof Giraffe)
		loadImages("grf");
		if(this instanceof Elephant)
		loadImages("elf");
		if(this instanceof Turtle)
		loadImages("trt");
	}
	
	/**
	 * This function is for the clone of the new animal.
	 * @return a clone of the old animal, with new speed.
	 * @throws CloneNotSupportedException 
	 */
	public Object clone() 
	{	
		Object clone=null;		
		try {
			clone=super.clone();
			((Animal) clone).setLocation(new Point(0,0));
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
	
}


