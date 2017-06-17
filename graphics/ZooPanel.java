package graphics;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.*;

import food.IEdible;
import plants.Cabbage;
import plants.Lettuce;
import plants.Meat;
import plants.Plant;
import animals.Animal;

/**
 * This is the ZooPanel class in which we build a ZooPanel class in our frame.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see JPanel, Runnable ,ActionListener
 */
public class ZooPanel extends JPanel implements Runnable ,ActionListener {
	
	private static final long serialVersionUID = 1L;
	protected JButton Add_Animal;
	protected JButton Sleep;
	protected JButton Wake_Up;
	protected JButton Clear;
	protected JButton Food;
	protected JButton Info;
	protected JButton Exit;
	protected JButton Decorate;
	protected JButton Duplicate;
	protected JButton Save_state;
	protected JButton Restore_state;
	private JPanel panel;
	private JPanel pan;
	private JPanel panel2;
	private Thread Controller;
	protected ArrayList<Animal> Animals;
	protected Plant P;
	protected Meat M;
	protected BufferedImage img=null;
	private int TotalEatCount;
	protected AddAnimalDialog dial;
	private boolean ThereIsFood;
	private Executor threadPool;
	private final int THREAD_POOL_NUM = 5;
	static private ZooPanel instance=null;
	private String kind=null;
	private JDialog DecorateDialog;
	private JSplitPane pane;
	private JComboBox animalDecorate;
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JLabel Red, Blue;
	private JButton OK;
	private ArrayList<ZooMemento> States;
	protected ZooObserver obs;
	public int LoopSize;
	
	/**
	 * This is the constructor of zoopanel here we will initialize the variables and add the buttons to the panel.
	 */
	private ZooPanel()
	{
		Animals=new ArrayList<Animal>(10);
		threadPool = Executors.newFixedThreadPool(THREAD_POOL_NUM);
		States=new ArrayList<ZooMemento>(3);
		setLayout(new BorderLayout());
		FlowLayout myFlowLayout = new FlowLayout();
		myFlowLayout.setAlignment(FlowLayout.CENTER);
		myFlowLayout.setHgap(20);
		myFlowLayout.setVgap(20);
		obs = new ZooObserver(Animals);
		panel=new JPanel();
		pan = new JPanel();
		panel2 = new JPanel();
		panel.setSize(799,599);
		GridLayout myGridLayout = new GridLayout(1, 1);
		panel.setLayout(myGridLayout);
		pan.setLayout(myGridLayout);
		panel2.setLayout(myGridLayout);
		Add_Animal = new JButton("Add Animal");
		Sleep = new JButton("Sleep");
		Wake_Up = new JButton("Wake Up");
		Clear = new JButton("Clear");
		Food = new JButton("Food");
		Info = new JButton("Info");
		Exit = new JButton("Exit");	
		Decorate = new JButton("Decorate");
		Duplicate = new JButton("Duplicate");
		Save_state = new JButton("Save state");
		Restore_state = new JButton("Restore state");
		P=null;
		M=null;
		panel2.add(Add_Animal);	
		panel2.add(Sleep);
		panel2.add(Wake_Up);
		panel2.add(Clear);
		panel2.add(Food);
		panel2.add(Info);
		pan.add(Decorate);
		pan.add(Duplicate);
		pan.add(Save_state);
		pan.add(Restore_state);
		pan.add(Exit);
		panel.add(panel2);
		panel.add(pan);
		panel.setLayout(new GridLayout(2, 11,0,0));
		this.add(panel,BorderLayout.SOUTH);
		Add_Animal.addActionListener(this);
		Sleep.addActionListener(this);
		Food.addActionListener(this);
		Wake_Up.addActionListener(this);
		Info.addActionListener(this);
		Controller = new Thread(this);
		Clear.addActionListener(this);
		Decorate.addActionListener(this);
		Duplicate.addActionListener(this);
		Restore_state.addActionListener(this);
		Save_state.addActionListener(this);
		Controller.start();		
		TotalEatCount=0;	
		ThereIsFood=false;
	}
	
	/**
	 * This is the function that paints the panel with the animals/food.
	 * @param Graphics g- a graphics package variable
	 */
	   public void paintComponent(Graphics g)   {
		     super.paintComponent(g); 
			     if(img!=null){
			    	 g.drawImage(img,0,0,getWidth(),getHeight(), this);
			     }
			     synchronized(this){
			    	 for(int i=0;i<Animals.size();i++)
			    	 {
			    		 if(i<=4)
			    			 Animals.get(i).drawObject(g);
			    	 }
			     }
			     synchronized(this){
			     if(P!=null)
			     {
			    	 P.drawObject(g);
			     }
			     if(M!=null)
			     {
			    	M.drawObject(g);
			     }
			     }
	   }
	   
	   /**
	    * This is an adder function for the total eat count.
	    * @param num a parameter which we would like to add to the total eat count.
	    */
	   public void addEatCounter(int num){
		   this.TotalEatCount = this.TotalEatCount + num;
	   }
	   
	   /**
	    * This is getter for the food.
	    * @return returns the food that the user chose
	    */	   
	   public IEdible getFood(){
		   synchronized(this){
		   if(M != null)
			   return M;
		   else if(P != null)
			   return P;
		   else return null;
		   }
	   }
	   
	   /**
	    * This is setter for the food.
	    * @param e-a kind of food that we want to set
	    */
	   
	   public void setFood(IEdible e){
		   synchronized(this){
		   if(e!=null)
		   {
			   if(e.equals(M))
				   M = null;
			   else if(e.equals(P))
				   P = null;
		   }
		   }
	   }

		/**
		 * This is the run function for the zoo panel, here we will check if one animal can eat another, and we will repaint accordingly.
		 */	
  @Override
   public void run() {
	  while(Thread.currentThread().isAlive())
	  {
		 synchronized(this){
		 if(ThereIsFood==true)
		    repaint();
		 }
	  }
	    	
	}
  
	/**
	 * This is a function for when an action is performed(pressing a button etc).
	 * @param an event that tells which action has been performed
	 */	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource()==Add_Animal)
		{ // if pushed the add animal button.

			synchronized(this){
			if(Animals.size()==10)
			{
				JOptionPane.showMessageDialog(this, "You cannot add more than 5 animals to the queue");
			}
			else{
			Object[] options = {"Herbivore", "Carnivore", "Omnivore"};
			int n=JOptionPane.showOptionDialog(this,
					"Please choose the type of animal",
					"Animal kind", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]); 
			if(n==JOptionPane.YES_OPTION)
			{
				kind="Herbivore";	
			}
			else if(n==JOptionPane.NO_OPTION)
			{
				kind="Carnivore";
			}
			else if(n==JOptionPane.CANCEL_OPTION)
			{
				kind="Omnivore";
			}
			if(kind!=null)
			{
			dial = new AddAnimalDialog(this,kind);
			dial.setVisible(true);
			}
			}
			}
		}
				
		else if(ev.getSource()==Sleep)
		{ // if pushed the sleep button.
	    	synchronized(this){
					
		    for(int i=0;i<LoopSize;i++)
		    {  	
				Animals.get(i).setSuspended();				
		    }	    	
		  }
		}
		
		if(ev.getSource()==Food)
		{ // if pushed the food button.
			synchronized(this){
			Object[] options = {"Lettuce", "Cabbage", "Meat"};
			int n=JOptionPane.showOptionDialog(this,
					"Please choose food",
					"Food for animals", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]); 
			if(n==JOptionPane.YES_OPTION)
			{
				ThereIsFood=false;
				M=null;
				P=null;
				P=Lettuce.getInstance();
				ThereIsFood=true;
				
			}
			else if(n==JOptionPane.NO_OPTION)
			{
				ThereIsFood=false;
				P=null;
				M=null;
				P=Cabbage.getInstance();
				ThereIsFood=true;
			}
			else if(n==JOptionPane.CANCEL_OPTION)
			{
				ThereIsFood=false;
				P=null;
				M=Meat.getInstance();
				ThereIsFood=true;
			}
			}
		}
	
		    	
		if(ev.getSource()==Wake_Up)
		{ // if pushed the wake up button.
			synchronized(this)
			{
		    	 for(int i=0;i<LoopSize;i++)
		    	 {
		    		 Animals.get(i).setResumed();
		    	 }
			}
		}
		
		if(ev.getSource()==Clear)
		{ // if pushed the clear button.
			synchronized(this)
			{
		    	 for(int i=0;i<LoopSize;i++)
		    	 {
		    		 this.addEatCounter(-Animals.get(i).getEatCount());
		    		 Animals.get(i).setSuspended();
		    		 Animals.get(i).Interrupt();
		    		 Animals.get(i).deleteObserver(obs);
		    	 }
		    	 for(int i=0;i<LoopSize;i++)
		    	 {
		    		 Animals.remove(0);
		    	 }
		    	 LoopSize=Min(Animals.size(),5);
		    	 for(int i=0;i<LoopSize;i++)
		    	 {
		    		 Animals.get(i).addObserver(obs);
					 repaint();
					 Start(Animals.get(i));
		    	 }		    	 		    	 
		    repaint(); 
			}
		}
		
		if(ev.getSource()==Info)
		{ // if pushed the info button.
			synchronized(this){
			String[][] data = new String[Animals.size()+1][7];
            int total =0;
            int i=0;
            String[] Names = {"Animal", "State", "Color","Weight","H.speed","V.speed","EatCount"};
            JTable T = new JTable(data,Names);
            JScrollPane S =new JScrollPane(T);
            for (int j = 0; j < LoopSize; j++) {
            	Animal a = Animals.get(j);
                data[i][0] = a.getAnimalName();
                data[i][1] = "running";
                data[i][2] = a.getColor();
                data[i][3] = String.valueOf(a.getWeight());
                data[i][4] = String.valueOf(a.getHorSpeed());
                data[i][5] = String.valueOf(a.getVerSpeed());
                data[i][6] = String.valueOf(a.getEatCount());
                total = total + a.getEatCount();
                i++;
            }
            for (int j = LoopSize; j < Animals.size(); j++) {
            	Animal a = Animals.get(j);
                data[i][0] = a.getAnimalName();
                data[i][1] = "blocked";
                data[i][2] = a.getColor();
                data[i][3] = String.valueOf(a.getWeight());
                data[i][4] = String.valueOf(a.getHorSpeed());
                data[i][5] = String.valueOf(a.getVerSpeed());
                data[i][6] = String.valueOf(a.getEatCount());
                total = total + a.getEatCount();
                i++;
            }
            data[Animals.size()][0] = "Total";
            int x = Animals.size() - LoopSize;
            data[Animals.size()][1] = "run=" + LoopSize +",blc=" + x;
            data[Animals.size()][6] = String.valueOf(total);
            JOptionPane.showConfirmDialog(null,S , "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null);
		}
		}
		
		if(ev.getSource() == Decorate){
			// if pushed the decorate button.
			int flag=0;
			String[] AnimalStrings=new String[11];
			 Animal A;
			 int j=1;
			 synchronized(this){
				 AnimalStrings[0]="No Animal";
	    	 for(int i=0;i<LoopSize;i++)
	    	 {
	    		A=Animals.get(i);
	    		 if(A.getColor().equals("Natural"))
	    		 {
	    			 AnimalStrings[j]=(i+1)+".["+A.getAnimalName()+": running="+A.getSus()+", Weight="+A.getWeight()+", Color="+A.getColor();
	    			 j++;
	    		 }
	    	 }
			 }
			DecorateDialog = new JDialog();				
			radio1 = new JRadioButton();
			radio2 = new JRadioButton();
			DecorateDialog.setSize(700, 300);
			pane = new JSplitPane();
			Red = new JLabel("Red");
			Blue = new JLabel("Blue");
			OK = new JButton("OK");
			OK.setLayout(new GridLayout(1, 1,20,0));
			DecorateDialog.add(pane);
			animalDecorate = new JComboBox(AnimalStrings);
			animalDecorate.setPreferredSize(new Dimension(450, 25));
			JPanel p1=new JPanel();
			JPanel p2=new JPanel();
			p1.add(Red);
			p1.add(radio1);
			p1.add(Blue);
			p1.add(radio2);
			pane.setLeftComponent(p2);
			pane.setRightComponent(p1);
		    pane.setDividerLocation(500);
		    pane.setDividerSize(10);
		    p1.setBorder(BorderFactory.createTitledBorder("Choose decoration color"));
		    p2.setBorder(BorderFactory.createTitledBorder("Select Animal to decorate"));
		    p2.setLayout(new BorderLayout());
		    p2.add(OK,BorderLayout.SOUTH);
		    p2.add(animalDecorate,BorderLayout.NORTH);
		    Dimension minimumSize = new Dimension(400, 400);
		    p1.setPreferredSize(minimumSize);
		    p2.setPreferredSize(minimumSize);
		    OK.addActionListener(actionEvent->{
		    	synchronized(this){
		    		if(animalDecorate.getSelectedItem() != null){
		    	if((radio2.isSelected() && radio1.isSelected()) || (!radio2.isSelected() && !radio1.isSelected()) || animalDecorate.getSelectedItem().toString().equals("No Animal"))
		    	{
		    		JOptionPane.showMessageDialog(this, "You have to choose an animal and a color!");
		    	}
		    	else
		    	{
		    		int i=Character.getNumericValue(animalDecorate.getSelectedItem().toString().charAt(0))-1;
		    		if(radio1.isSelected() && !radio2.isSelected())
		    		{	    	
		    			Animals.get(i).setCol(Color.red);    	
		    		}
		    		if(radio2.isSelected() && !radio1.isSelected())
		    		{
		    			Animals.get(i).setCol(Color.blue);		    		    	
		    		}
		    		Animals.get(i).PaintAnimal();
		    		DecorateDialog.dispose();
		    	}
		    	}
		    		else
		    			JOptionPane.showMessageDialog(this, "You have to choose an animal and a color!");
		    	}
			     });
		    synchronized(this){
	    	 for(int i=0;i<LoopSize;i++)
	    	 {
	    		 if(Animals.get(i).getColor().equals("Natural"))
	    		 {
	    			flag=1; 
	    		 }
	    	 }
	    	 if(flag==1 && LoopSize>0)
	    		 DecorateDialog.setVisible(true);
	    	 else
	    		 JOptionPane.showMessageDialog(this, "You have no animals for decoration!");
		    }
		}
		
		if(ev.getSource() == Duplicate)
		{ // if pushed the duplicate button.
			String[] AnimalStrings=new String[11];
			 Animal A;
			 int j=1;
			 synchronized(this){
				 AnimalStrings[0]="No Animal";
	    	 for(int i=0;i<LoopSize;i++)
	    	 {
	    		A=Animals.get(i);
	    			 AnimalStrings[j]=(i+1)+".["+A.getAnimalName()+": running="+A.getSus()+", Weight="+A.getWeight()+", Color="+A.getColor();
	    			 j++;
	    	 }
			 }
			JDialog DuplicateDialog = new JDialog();
			DuplicateDialog.setSize(700, 300);
			JSplitPane Dpane = new JSplitPane();
			JSlider VerSpinner = new JSlider(JSlider.HORIZONTAL,0, 10, 5);
			JSlider HorSpinner = new JSlider(JSlider.HORIZONTAL,0, 10, 5);
			VerSpinner.setMajorTickSpacing(2);
			VerSpinner.setMinorTickSpacing(1);
			VerSpinner.setPaintTicks(true);
			VerSpinner.setPaintLabels(true);
			HorSpinner.setMajorTickSpacing(2);
			HorSpinner.setMinorTickSpacing(1);
			HorSpinner.setPaintTicks(true);
			HorSpinner.setPaintLabels(true);
			JLabel HS = new JLabel("Horizontal speed");
			JLabel VS = new JLabel("Vertical speed");
			JButton OK = new JButton("OK");
			OK.setLayout(new GridLayout(1, 1,20,0));
			DuplicateDialog.add(Dpane);
			JComboBox animalClone = new JComboBox(AnimalStrings);
		    animalClone.setPreferredSize(new Dimension(450, 25));
		    HS.setPreferredSize(new Dimension(100, 25));
		    VS.setPreferredSize(new Dimension(100, 25));
			JPanel p1=new JPanel();
			JPanel p2=new JPanel();
			p1.add(HS);
			p1.add(HorSpinner);
			p1.add(VS);
			p1.add(VerSpinner);
			Dpane.setLeftComponent(p2);
			Dpane.setRightComponent(p1);
		    Dpane.setDividerLocation(300);
		    Dpane.setDividerSize(10);
		    p1.setBorder(BorderFactory.createTitledBorder("Speed may be changed"));
		    p2.setBorder(BorderFactory.createTitledBorder("Select Animal to clone"));
		    p2.setLayout(new BorderLayout());
		    p2.add(OK,BorderLayout.SOUTH);
		    p2.add(animalClone,BorderLayout.NORTH);
		    Dimension minimumSize = new Dimension(400, 400);
		    p1.setPreferredSize(minimumSize);
		    p2.setPreferredSize(minimumSize);
		    OK.addActionListener(actionEvent->{
		    	synchronized(this){
		    		Animal C = null;
		    		if(Animals.size() != 0 && !animalClone.getSelectedItem().toString().equals("No Animal")){ // if more than 0 animals.
					try {
						int i=Character.getNumericValue(animalClone.getSelectedItem().toString().charAt(0))-1;
						C = (Animal) Animals.get(i).clone();
						C.setEatCount(0);
					} catch (Exception e) {						
						e.printStackTrace();
					}
		    		C.setHorSpeed(HorSpinner.getValue());
		    		C.setVerSpeed(VerSpinner.getValue());
		    		}
					if(Animals.size()==10)
					{ // if 10 animals.
							JOptionPane.showMessageDialog(this, "you cannot add anymore animals");
					}
					else if(Animals.size() != 0 && !animalClone.getSelectedItem().toString().equals("No Animal"))
						{ // if more than 0 animals.
						Animals.add(C);
						LoopSize=Min(Animals.size(),5);
						
						if(Animals.size()<=5)
						{
							C.addObserver(obs);
							repaint();
							Start(C);		
						}
						}
					else // if 0 animals.
						JOptionPane.showMessageDialog(this, "wrong choise");
					DuplicateDialog.dispose();
		    	}
			     });
	    		 DuplicateDialog.setVisible(true);
		    
		}
		if(ev.getSource() == Save_state)
		{ // if pushed the save state button.
			synchronized(this){
		
			if(States.size()<3)
			{
			ZooMemento Mem=new ZooMemento(Animals, P, M, ThereIsFood);
			States.add(Mem);	
			JOptionPane.showMessageDialog(this, "State saved!");
			}
			else
				JOptionPane.showMessageDialog(this, "Cannot save anymore states!");
			}
		}
		
		if(ev.getSource() == Restore_state)
		{ // if pushed the restore state button.
			if(States.size()==0)
			{
				JOptionPane.showMessageDialog(this, "There are no saved states!");
			}
			else
			{
			JLabel S = new JLabel("Please choose state for restore:");
			JButton state1=new JButton("State 1");
			JButton state2=new JButton("State 2");
			JButton state3=new JButton("State 3");	
			JButton Cancel=new JButton("Cancel");
			JDialog RestoreDialog = new JDialog();
			JPanel pane=new JPanel();
			RestoreDialog.setSize(250, 150);
			RestoreDialog.setResizable(false);
			pane.setLayout(new FlowLayout());
			pane.add(S);
			if(States.size()>=1)
				pane.add(state1);
			if(States.size()>=2)
				pane.add(state2);
			if(States.size()==3)
				pane.add(state3);
			pane.add(Cancel);
			RestoreDialog.add(pane);
			RestoreDialog.setVisible(true);
			Cancel.addActionListener(actionEvent->{
				 RestoreDialog.dispose();
			 });
			
			state1.addActionListener(actionEvent->{
				// first state.
				ZooMemento Z;
				synchronized(this){
					Z=States.get(0).getState(Animals);
					LoopSize=Min(Animals.size(),5);
				 for(int i=0;i<LoopSize;i++)
				 {
					 Animal D=Animals.get(i);
					 if(D.getSuspended())
					 {
						 D.setChanges(true);
						 obs.update(D, "");
					 	 D.notifyObservers();
					 	 D.setSuspended();
					 }
					 Start(D);	
					 this.addEatCounter(D.getEatCount());
				 }
				 
				repaint();
				ThereIsFood=Z.getThereIsFood();
				M=Z.getM();
				P=Z.getP();
				States.remove(0);
				RestoreDialog.dispose();
				}
			 });
			
			state2.addActionListener(actionEvent->{
				// second state.
				ZooMemento Z;
				synchronized(this){
				Z=States.get(1).getState(Animals);
				LoopSize=Min(Animals.size(),5);
				 for(int i=0;i<LoopSize;i++)
				 {
					 Animal D=Animals.get(i);
					 if(D.getSuspended())
					 {
						 D.setChanges(true);
						 obs.update(D, "");
					 	 D.notifyObservers();
					 	 D.setSuspended();
					 }
					 Start(D);	
					 this.addEatCounter(D.getEatCount());
				 }
				
				ThereIsFood=Z.getThereIsFood();
				M=Z.getM();
				P=Z.getP();
				States.remove(1);
				RestoreDialog.dispose();
				}
			 });
			
			state3.addActionListener(actionEvent->{
				// third state.
				ZooMemento Z;
				synchronized(this){
				Z=States.get(2).getState(Animals);
				LoopSize=Min(Animals.size(),5);
				 for(int i=0;i<LoopSize;i++)
				 {
					 Animal D=Animals.get(i);
					 if(D.getSuspended())
					 {
						 D.setChanges(true);
						 obs.update(D, "");
					 	 D.notifyObservers();
					 	 D.setSuspended();
					 }
					 Start(D);	
					 this.addEatCounter(D.getEatCount());
				 }
				
				ThereIsFood=Z.getThereIsFood();
				M=Z.getM();
				P=Z.getP();
				States.remove(2);
				RestoreDialog.dispose();
				}
			 });				
			}
		}
						
	/**
	 * This is getter for the variable "There is food".
	 * @return returns true if there is food, return false if there isn't
	 */	
	}
	public synchronized boolean getThereIsFood()
	{	
		return ThereIsFood;		
	}
	
	/**
	 * This is getter for the variable "There is food".
	 * @param b- can be true or false according to the user's choice
	 */	
	public synchronized void setThereIsFood(boolean b)
	{
		ThereIsFood=b;
	}

	/**
	 * This function is to start the animal in the pool.
	 * @param a the animal which we would like to start running.
	 */
	public void Start(Animal a)
	{
		synchronized(this){
		Future<?> task = ((ExecutorService)threadPool).submit(a);
		a.setTask(task);
		}
	}
	
	/**
	 * this is function that returns the instance of the ZooPanel singleton.
	 * @return the instance.
	 */
	public static ZooPanel getInstance()
	{
	     if (instance == null)
	          synchronized(ZooPanel.class){   
	              if (instance == null)
	                  instance = new ZooPanel();
	          }
	       return instance;

	}   
	
	/**
	 * This function returns the minimum int between two ints.
	 * @param x the first int to check.
	 * @param y the second int to check.
	 * @return the minimum int.
	 */
	public int Min(int x, int y)
	{
		if(x>y)
			return y;
		return x;
	}


}
