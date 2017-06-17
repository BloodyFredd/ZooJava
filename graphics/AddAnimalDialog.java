package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import diet.*;
import animals.*;

/**
 * This is the AddAnimalDialog class in which we build a new dialog.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see JDialog, ActionListener
 */
public class AddAnimalDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JComboBox type,colorOfAnimal;
	private JTextField size, verSpeed, horSpeed;
	protected JButton addAnimal;
	protected ZooPanel pan;
	private JLabel verSpeedLabel, horSpeedLabel, sizeLabel;
	Thread thread;
	private String FoodType;
	private AbstractZooFactory AZF;

	/**
	 * This is the constructor for the AddAnimalDialog
	 * @param P this is a reference to the ZooPanel
	 * @param S this is the name of the dialog
	 */
	public AddAnimalDialog(ZooPanel p, String Foodtype)
	{
		this.setTitle("Add Animal Dialog");
		pan=p;
		this.FoodType=Foodtype;
		setLayout(new BorderLayout());
		String[] CarnianimalStrings = { "Lion"};
		String[] HerbianimalStrings = { "Elephant","Giraffe","Turtle" };
		String[] OmnianimalStrings = {"Bear"};
		String[] colorStrings = { "Blue","Red","Natural" };	
		FlowLayout myFlowLayout = new FlowLayout();
		myFlowLayout.setAlignment(FlowLayout.CENTER);
		myFlowLayout.setHgap(20);
		myFlowLayout.setVgap(20);
		this.setSize(500,200);
		this.setLayout(myFlowLayout);
		if(Foodtype.equals("Herbivore"))
		{
			type=new JComboBox(HerbianimalStrings);
		}
		if(Foodtype.equals("Omnivore"))
		{
			type=new JComboBox(OmnianimalStrings);
		}
		if(Foodtype.equals("Carnivore"))
		{
			type=new JComboBox(CarnianimalStrings);
		}
		colorOfAnimal=new JComboBox(colorStrings);
		size = new JTextField();
		verSpeed = new JTextField();
		horSpeed = new JTextField();
		size.setPreferredSize(new Dimension(80,23));
		verSpeed.setPreferredSize(new Dimension(80,23));
		horSpeed.setPreferredSize(new Dimension(80,23));
		addAnimal = new JButton("Add new animal");
		verSpeedLabel = new JLabel("Vertical Speed:");
		horSpeedLabel = new JLabel("Horizontal Speed:");
		sizeLabel = new JLabel("Size of Animal:");
		this.add(type);
		this.add(colorOfAnimal);
		this.add(sizeLabel);
		this.add(size);
		this.add(verSpeedLabel);
		this.add(verSpeed);
		this.add(horSpeedLabel);
		this.add(horSpeed);
		this.add(addAnimal);
		addAnimal.addActionListener(this);
		size.addActionListener(this);
		verSpeed.addActionListener(this);
		horSpeed.addActionListener(this);
		verSpeed.setText("");
		size.setText("");
		horSpeed.setText("");
		AZF=createAnimalFactory(FoodType);
		
	}	
	
	/**
	 * This is the function that creates an animal factory.
	 * @param foodtype which food type to build.
	 * @return the new animal factory.
	 */
	public AbstractZooFactory createAnimalFactory(String foodtype)
	{
		if(foodtype.equals("Herbivore"))
		{
			return new HerbivoreFactory();
		}
		else if(foodtype.equals("Omnivore"))
		{
			return new OmnivoreFactory();
		}
		else if(foodtype.equals("Carnivore"))
		{
			return new CarnivoreFactory();
		}
		return null;
	}
/**
 * This function checks what button did we push, and implements it accordingly.
 */
	@Override
	public void actionPerformed(ActionEvent ev)
	{ 
		if(ev.getSource()==addAnimal)
		{// if we pushed add animal.
			int x;
			if(size.getText().equals("")==false)
			{// check if not empty and text is okay.
				x = Integer.parseInt(size.getText());
				if((x<50 || x>300) && x==Math.round(x))
				{
					size.setText("");
					JOptionPane.showMessageDialog(this, "You can insert a size bitween 50-300 and only whole numbers");
				}
			}

			if(verSpeed.getText().equals("")==false)
			{// check if not empty and text is okay.
				x = Integer.parseInt(verSpeed.getText());
				if((x<1 || x>10) && x==Math.round(x))
				{
					verSpeed.setText("");
					JOptionPane.showMessageDialog(this, "You can insert a size bitween 1-10 and only whole numbers");
				}
			}

			if(horSpeed.getText().equals("")==false)
			{// check if not empty and text is okay.
				x = Integer.parseInt(horSpeed.getText());
				if((x<1 || x>10) && x==Math.round(x))
				{
					horSpeed.setText("");
					JOptionPane.showMessageDialog(this, "You can insert a size bitween 1-10 and only whole numbers");
				}
			}

			if(horSpeed.getText().equals("")==false && verSpeed.getText().equals("")==false && size.getText().equals("")==false)
			{// check if not empty and text is okay.
				//Animal A=null;
				Color cl=null;
				String cohise=(String) colorOfAnimal.getSelectedItem();
				if(cohise.equals("Blue"))
				{// if the color is blue
					cl=Color.blue;
				}
				if(cohise.equals("Red"))
				{//if the color is red
					cl=Color.red;
				}
				if(((String)colorOfAnimal.getSelectedItem()).equals("Natural"))
				{ // if the color is natural
					cl = null;
				}
				
				AZF.getValues(Integer.parseInt(size.getText()),Integer.parseInt(verSpeed.getText()),Integer.parseInt(horSpeed.getText()),cl);
				Animal A=AZF.produceAnimal(type.getSelectedItem().toString());
				synchronized(this){			
					pan.Animals.add(A);
					pan.LoopSize=pan.Min(pan.Animals.size(),5);		
					if(pan.Animals.size()<=5)
						{
						A.addObserver(pan.obs);
						pan.repaint();
					    pan.Start(A);
						}
				}
					this.dispose();		
				
			}
			else if(horSpeed.getText().equals("") && verSpeed.getText().equals("")&& size.getText().equals(""))
				JOptionPane.showMessageDialog(this, "You need to insert some value");
			else
				JOptionPane.showMessageDialog(this, "Please try again");
			
		}		
	}
	

}
