package Editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class New extends JFrame implements ActionListener{
	
	private JFrame frame = new JFrame("New Maze");
	private JButton newmaze = new JButton("Create New Maze");
	private JPanel panels = new JPanel(); //the south panel
	private JPanel paneln = new JPanel(); //the north panel
	private JTextField size = new JTextField();
	private JTextField nlev = new JTextField();
	private JLabel sizel = new JLabel("Level size(3-63): ");
	private JLabel nlevl = new JLabel("Number of levels (1-12): ");

	protected int levelsize;
	protected int levels;
	
	public New() {
		//Creating the window
	    frame.setSize(200, 120);
	    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.setResizable(false);
	    frame.toFront();
	    
	    newmaze.addActionListener(this);
	    
	    size.setPreferredSize(new Dimension(30,20));
	    nlev.setPreferredSize(new Dimension(30,20));
	    paneln.setBackground(Color.WHITE);
	    paneln.add(sizel);
	    paneln.add(size);
	    paneln.add(nlevl);
	    paneln.add(nlev);
	    paneln.add(newmaze);
	    //add the panel to the frame
	    frame.add(paneln/*, BorderLayout.NORTH*/);
	}
	
	public static void main(String[] args) {
		new New();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Button Pressed");
		
		try{
			levelsize = Integer.parseInt(size.getText());
			levels = Integer.parseInt(nlev.getText());
		}
		catch (NumberFormatException ex){
			System.err.println("One or more invalid numbers were entered");
		}
		
		if (levelsize < 3 || levelsize > 63){
			levelsize = 63;
		}
		if (levels < 1 || levels > 12){
			levels = 12;
		}
		
		//Levelsize
		System.out.println("The levelsize is currently: " + levelsize);
		//Number of levels
		System.out.println("The number of levels is currently: " + levels);
	}
}
