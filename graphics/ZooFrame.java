package graphics;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;import javax.swing.JOptionPane;

/**
 * This is the ZooFrame class in which we build a new ZooFrame, which includes our main for the program.
 * @author Freddy Maidanik 310017280, Omri Avidan 208902296
 * campus beer-sheva
 * @see JFrame
 */
public class ZooFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
/**
 * This is the main of the program, in here we build the frame to hold all the zoo.
 * @param args
 */
	public static void main(String []args)
	{
		JFrame frame = new JFrame("Zoo");
		frame.setSize(799,599);
		ZooPanel pan = ZooPanel.getInstance();
		JMenu File, Background, Help;
		JMenuBar menuBar;
		JMenuItem Exit1, Image, Green, None, Help1;
		File = new JMenu("File");
		Background = new JMenu("Background");
		Help = new JMenu("Help");
		menuBar = new JMenuBar();
		Exit1 = new JMenuItem("Exit");
		Image = new JMenuItem("Image");
		Green = new JMenuItem("Green");
		None = new JMenuItem("None");
		Help1 = new JMenuItem("Help");		
		menuBar.add(File);
		menuBar.add(Background);
		menuBar.add(Help);
		File.add(Exit1);
		Background.add(Image);
		Background.add(Green);
 		Background.add(None);
		Help.add(Help1);
		// if pushed the button for the green background.
		Green.addActionListener(actionEvent->{
			pan.img=null;
			pan.repaint();
			pan.setBackground(Color.green);

		});
		
		// if pushed the button for the none background.
		None.addActionListener(actionEvent->{
			pan.img=null;
			pan.setBackground(null);
		});
		
		// if pushed the button for the exit.
		Exit1.addActionListener(actionEvent->{
			System.exit(0);
		});
		
		// if pushed the button for the help.
		Help1.addActionListener(actionEvent->{
			JOptionPane.showMessageDialog(frame, "Home Work 3 \n GUI @Threads");
		});
		
		// if pushed the button for the image background.
		Image.addActionListener(actionEvent->{
		     try{ 
		    	 pan.img = ImageIO.read(new File(IDrawable.PICTURE_PATH + "/savanna.jpg"));
		     }
		     catch (IOException e) 
		     {
		    	 System.out.println("Cannot load image");
		     }
			pan.repaint();
		});
		// if pushed the button for the exit,
		pan.Exit.addActionListener(actionEvent->{
			System.exit(0);
		});		
		frame.setJMenuBar(menuBar);
		frame.setContentPane(pan);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setResizable(true);
		frame.setVisible(true);//show the window
		
		
	}

}
