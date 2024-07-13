package edu.psgv.sweng881.gui;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import edu.psgv.sweng881.game.Player;
import edu.psgv.sweng881.lib.GraphPaperLayout;


public class GameWindow extends JFrame {
		
	CatanBoard board;
	BottomBar bottom;
	SideBar side;

	JFrame frame;
	public final static int INTERVAL = 20;
	
	
	final static int SCRSIZE = 1000; //TODO specify
	
	
	public GameWindow(ArrayList<Player> players) {
		board = new CatanBoard(players);
		bottom = new BottomBar();
		
		createAndShowGUI();

		try {Thread.sleep(250);} catch (Exception ignored) {}
		board.repaint(); //TODO fix validate
		bottom.repaint();
	}
	
	private void createAndShowGUI() {
		
		frame = new JFrame("Catan");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		Dimension d = new Dimension(5,6);

		frame.setSize(SCRSIZE + 500, SCRSIZE);
		//frame.setLayout(new GraphPaperLayout(d));
		Container content = frame.getContentPane();
		content.setLayout(new GraphPaperLayout(d));
		//content.add(board);
		content.add(board,new Rectangle(0,0,4,4));
		
		side = new SideBar(this);
		content.add(side,new Rectangle(4,0,1,4));
		
		content.add(bottom,new Rectangle(0,4,5,2));
		
		
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		board.repaint();
	}
	
	public CatanBoard getBoard() {
		return board;
	}
}
