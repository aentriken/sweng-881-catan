package edu.psgv.sweng881.game;

import edu.psgv.sweng881.gui.GameWindow;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.SwingUtilities;


public class GameRunner {
	
	private static Player currentPlayer;
	private static int numberPlayers;
	private static int index = 0;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static Game game;
	private static Player winner;

	public static void main(String[] args) {
		
		players.add(new Player("DevMaster",	Color.ORANGE , 12,12,12,12,12,2));
		players.add(new Player("Batman",	Color.BLACK));
		players.add(new Player("Spiderman",	Color.RED));
		players.add(new Player("Superman",	Color.BLUE));
						
		numberPlayers = players.size();
		
		SwingUtilities.invokeLater(() -> {
            GameWindow tmp = new GameWindow(players);
            game = tmp.getBoard().getGame();
        });
	}
	
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public static void nextPlayer() {
		currentPlayer = players.get((index + 1) % 4);
		index = (index + 1) % 4;
	}
	
	public static void prevPlayer() {
		currentPlayer = players.get((index - 1) % 4);
		index = (index - 1) % 4;
	}
	
	public static void setFirstPlayer() {
		currentPlayer = players.get(0);
	}
	
	public static void setWinner(Player p) {
		winner = p;
	}
	
	public static Player getWinner() {
		return winner;
	}
	
	public static int getNumbPlayers() {
		return players.size();
	}
	
	public static Player getPlayer(int i) {
		return players.get(i);
	}
}
