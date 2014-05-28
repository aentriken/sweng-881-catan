package game;

import java.util.ArrayList;
import java.util.Collections;
import board.*;


/**
 * The main game class of Settlers of Catan
 */
public class Game {

	private Board board;
	private ArrayList<Player> players;
	private Deck deck;


	/**
	 * Constructor for game, creates the Board.
	 * @param givenPlayers the players of the game
	 */
	public Game(ArrayList<Player> givenPlayers) {

		if (givenPlayers.size() < 3 || givenPlayers.size() > 4)
			throw new IllegalArgumentException("Game must be played with three or four players");

		ArrayList<String> names = new ArrayList<String>();
		for (Player p : givenPlayers) {
			names.add(p.getName());
		}
		for (String s : names) {
			if (Collections.frequency(names, s) > 1)
				throw new IllegalArgumentException("Players must have different names");
		}
		
		
		Collections.shuffle(givenPlayers);

		players = givenPlayers;
		board = new Board();
		deck = new Deck();
	}

//TODO: move to Catan Board?
//	/**
//	 * Runs the setup phase of the game
//	 */
//	private void setup() {
//
//		boolean round2 = false;
//
//		for (int i = 0; i < 2; i++) {
//			for (Player p : players) {
//				boolean settSuccess = false;
//				VertexLocation sloc;
//				do{
//					sloc = new VertexLocation(1,1,0); //TODO: user input sloc
//					settSuccess = board.placeStructureNoRoad(sloc, p);
//					if (round2){
//						ArrayList<Tile> capitolTiles = new ArrayList<Tile>();
//						capitolTiles = board.getAdjacentTilesStructure(sloc);
//						for (Tile t : capitolTiles){
//							board.getStructure(sloc).giveResources(t.getType());
//						}
//					}
//				} while (settSuccess = false);
//
//				boolean roadSuccess = false;
//				EdgeLocation rloc;
//				do{
//					rloc = new EdgeLocation(3,3,0); //TODO: user input rloc
//					roadSuccess = board.placeRoad(rloc, p);
//				} while (roadSuccess = false);
//
//			}
//			Collections.reverse(players);
//			round2 = true;
//		}
//
//	}

	/**
	 * Checks if one player has ten or more victory points and more points than any other player
	 * @return whether anyone has one yet
	 */
	public boolean gameOver() {
		return winningPlayer() != null;
	}
	
	/**
	 * Returns the player that has won, or null if game is not finished
	 * @return winning player
	 */
	public Player winningPlayer() {
		
		Player maxVictoryPoints = players.get(0);
		Player secondMaxVictoryPoints = players.get(0);

		for (Player p : players) {

			if (p.getVictoryPoints() > maxVictoryPoints.getVictoryPoints()) {
				maxVictoryPoints = p;
			}
			else if (p.getVictoryPoints() > secondMaxVictoryPoints.getVictoryPoints()) {
				secondMaxVictoryPoints = p;
			}
		}
		
		if (maxVictoryPoints.getVictoryPoints() >= 10 && maxVictoryPoints.getVictoryPoints() > secondMaxVictoryPoints.getVictoryPoints()) {
			return maxVictoryPoints;
		}
		else
			return null;
	}

	/**
	 * Rolls the die and allocates resources to players
	 * @param p the Player rolling (in case Player wants to play dev card first)
	 * @return true if the roll was not a robber (7)
	 */
	public boolean roll(Player p) {

		// RTD
		int roll1 = (int)(Math.random() * 6 + 1);
		int roll2 = (int)(Math.random() * 6 + 1);

		if (roll1 == 7 || roll2 == 7) {
			return false;
		}
		else {
			// Distribute resources
			board.distributeResources(roll1 + roll2);
			
			return true;
		}
	}

	/**
	 * Allows the given Player to move the Robber to the given Location
	 * @param p the Player who moved the Robber
	 * @return if the chosen Location is valid and the move succeeded
	 */
	public boolean moveRobber(Player p, Location loc) {

		Location prev = board.getRobberLocation();

		if (loc.equals(prev)) {
			return false;
		}

		board.setRobberLocation(loc);
		board.getTile(loc).setRobber(true);
		board.getTile(prev).setRobber(false);
		
		return true;
	}

	/**
	 * Allows the given Player to take a card from any Player with a Settlement on the Tile of the given Location
	 * @param p the Player taking a card
	 * @param loc the Location of the Tile
	 */
	public void takeCard(Player p, Player choice) {
	
//TODO: move to CatanBoard
//		ArrayList<Structure> structures = new ArrayList<Structure>(6);
//
//		structures.add(board.getStructure(new VertexLocation(loc.getXCoord(), loc.getYCoord(), 0)));
//		structures.add(board.getStructure(new VertexLocation(loc.getXCoord(), loc.getYCoord(), 1)));
//		structures.add(board.getStructure(new VertexLocation(loc.getXCoord()+1, loc.getYCoord(), 1)));
//		structures.add(board.getStructure(new VertexLocation(loc.getXCoord()-1, loc.getYCoord(), 0)));
//		structures.add(board.getStructure(new VertexLocation(loc.getXCoord(), loc.getYCoord()+1, 1)));
//		structures.add(board.getStructure(new VertexLocation(loc.getXCoord(), loc.getYCoord()-1, 0)));
//
//		ArrayList<Player> playerChoices = new ArrayList<Player>();
//
//		for (Structure s : structures) {
//			if (null != s.getOwner() && !playerChoices.contains(s.getOwner())) {
//				playerChoices.add(s.getOwner());
//			}
//		}

		ArrayList<String> res = new ArrayList<String>(choice.getOwnedResources());
		Collections.shuffle(res);
		
		String result = res.get(0);
		
		choice.setNumberResourcesType(result, choice.getNumberResourcesType(result) - 1);
		
		p.setNumberResourcesType(result, p.getNumberResourcesType(result) - 1);
	}

	/**
	 * If any Player has more than seven cards, they choose half their cards (rounded down) to return to the bank
	 */
	public void halfCards() {

		for (Player p : players) {

			int cap = 7;
			int numbCards = p.getNumberResourcesType("BRICK") +
							p.getNumberResourcesType("WOOL") +
							p.getNumberResourcesType("ORE") +
							p.getNumberResourcesType("GRAIN") +
							p.getNumberResourcesType("LUMBER");
			int currentCards = numbCards;

			boolean done = false;

			do {
				currentCards = p.getNumberResourcesType("BRICK") +
							   p.getNumberResourcesType("WOOL") +
							   p.getNumberResourcesType("ORE") +
							   p.getNumberResourcesType("GRAIN") +
							   p.getNumberResourcesType("LUMBER");

				if (currentCards > cap) {
					int input = 0; //TODO: input
						/* Possible Values:
						 * 0 - LUMBER
						 * 1 - BRICK
						 * 2 - WOOL
						 * 3 - GRAIN
						 * 4 - ORE
						 */
					cap = numbCards / 2;

					switch (input) {
					case 0:
						p.setNumberResourcesType("LUMBER", p.getNumberResourcesType("LUMBER") - 1);
						break;
					case 1:
						p.setNumberResourcesType("BRICK", p.getNumberResourcesType("BRICK") - 1);
						break;
					case 2:
						p.setNumberResourcesType("WOOL", p.getNumberResourcesType("WOOL") - 1);
						break;
					case 3:
						p.setNumberResourcesType("GRAIN", p.getNumberResourcesType("GRAIN") - 1);
						break;
					case 4:
						p.setNumberResourcesType("ORE", p.getNumberResourcesType("ORE") - 1);
						break;
					}
				}
				else {
					done = true;
				}
			} while (!done);
		}
	}

//TODO: move to CatanBoard???
//	/**
//	 * Allows the provided Player to play a dev card
//	 * @param p
//	 */
//	private void playDevCard(Player p) {
//
//		ArrayList<DevCard> cards = p.getHand();
//
//		int input = 0; //TODO: input
//			/* Possible values:
//			 * any index within ArrayList cards
//			 */
//
//		if (input >= 0 && input < cards.size()) {
//			DevCard dC = cards.remove(input);
//
//			if (dC.getType().equals("Knight")) {
//				moveRobber(p);
//				p.incrementNumbKnights();
//				if (!p.hasLargestArmy() && p.getNumbKnights() >= 3) {
//					
//					int mostKnights = p.getNumbKnights();
//					String biggestArmy = p.getName();
//
//					for (Player player : players) {
//						if (player.hasLargestArmy()) {
//							
//							player.setHasLargestArmy(false);
//							player.setVictoryPoints(player.getVictoryPoints() - 1);
//							
//							mostKnights = player.getNumbKnights();
//							biggestArmy = player.getName();
//						}
//					}
//
//					for (Player player : players) {
//						if (player.getNumbKnights() > mostKnights) {
//							mostKnights = player.getNumbKnights();
//							biggestArmy = player.getName();
//						}
//					}
//					
//					for (Player player : players) {
//						if (player.getName().equals(biggestArmy)) {
//							player.setHasLargestArmy(true);
//							player.setVictoryPoints(player.getVictoryPoints() + 1);
//						}
//					}
//				}
//			}
//			else if (dC.getType().equals("Progress")) {
//				if (dC.getSubType().equals("Road Building")) {
//					buyObject(p, 1);
//					buyObject(p, 1);
//				}
//				else if (dC.getSubType().equals("Monoply")) {
//					int res = 0;
//					String choice = "WOOL"; //TODO: input
//					
//					for (Player player : players) {
//						res += player.getNumberResourcesType(choice);
//						player.setNumberResourcesType(choice, 0);
//					}
//					p.setNumberResourcesType(choice, res);
//				}
//				else if (dC.getSubType().equals("Year of Plenty")) {
//					String choice1 = "BRICK"; //TODO: input					
//					p.setNumberResourcesType(choice1, p.getNumberResourcesType(choice1) + 1);
//					
//					String choice2 = "ORE"; //TODO: input
//					p.setNumberResourcesType(choice2, p.getNumberResourcesType(choice2) + 1);
//				}
//			}
//			else if (dC.getType().equals("Victory Point")) {
//				p.setVictoryPoints(p.getVictoryPoints() + 1);
//			}
//		}
//		else {
//			//TODO: throw error about invalid dev card selection
//		}
//	}

	/**
	 * Operates trade between two players with the given resoures
	 * @param a the first Player in the trading
	 * @param b the second Player in the trading
	 * @param fromA the resources being traded from Player a to Player b
	 * @param fromB the resources being traded from Player b to Player a
	 * @return boolean whether the trade was possible
	 */
	public boolean playerTrade(Player a, Player b, ArrayList<String> fromA, ArrayList<String> fromB) {
		
		if (!a.hasResources(fromA) ||  !b.hasResources(fromB)) {
			return false;
		}
		
		for (String res : fromA) {
			a.setNumberResourcesType(res, a.getNumberResourcesType(res) - 1);
			b.setNumberResourcesType(res, b.getNumberResourcesType(res) + 1);
		}
		
		for (String res : fromB) {
			b.setNumberResourcesType(res, b.getNumberResourcesType(res) - 1);
			a.setNumberResourcesType(res, a.getNumberResourcesType(res) + 1);
		}
		
		return true;
	}
	
	/**
	 * Operates trading between given Player and the stock
	 * @param a the Player trading
	 * @param fromA what they are giving up
	 * @param toA what they are asking for
	 * @return whether the trade is possible or not
	 */
	public boolean npcTrade(Player a, ArrayList<String> fromA, ArrayList<String> toA) {
		if (!a.hasResources(fromA))
			return false;
		
		//TODO: check if this npc trade is valid (valid ratios, harbors, etc)
		
		for (String res : fromA) {
			a.setNumberResourcesType(res, a.getNumberResourcesType(res) - 1);
		}
		for (String res : toA) {
			a.setNumberResourcesType(res, a.getNumberResourcesType(res) + 1);
		}
		
		return true;
	}

	/**
	 * Buys Road for given Player
	 * @param p the given Player
	 * @return whether the Player can buy a Road
	 */
	public boolean buyRoad(Player p) {
		
		if (p.getNumberResourcesType("BRICK") < 1 || p.getNumberResourcesType("LUMBER") < 1) {
			return false;
			//TODO: throw error about not enough resources
		}

		// Check Player has not exceeded capacity for object
		int numbRoads = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 3; k++) {
					if (board.getRoad(new EdgeLocation(i, j, k)).getOwner().equals(p))
						numbRoads++;
				}
			}
		}
		if (numbRoads >= 15) {
			return false;
			//TODO: throw error about too many of object owned already
		}
		
		p.setNumberResourcesType("BRICK", p.getNumberResourcesType("BRICK") - 1);
		p.setNumberResourcesType("LUMBER", p.getNumberResourcesType("LUMBER") - 1);

		p.setVictoryPoints(p.getVictoryPoints() + 1);

		return true;
	}

	/**
	 * Buys Settlement for given Player
	 * @param p the given Player
	 * @return whether the Player can buy a Settlement
	 */
	public boolean buySettlement(Player p) {

		// Check Player has sufficient resources
		if (p.getNumberResourcesType("BRICK") < 1 || p.getNumberResourcesType("GRAIN") < 1 || p.getNumberResourcesType("WOOL") < 1 || p.getNumberResourcesType("LUMBER") < 1) {
			return false;
			//TODO: throw error about not enough resources
		}

		// Check Player has not exceeded capacity for object
		int numbSettlements = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 2; k++) {
					if (board.getStructure(new VertexLocation(i, j, k)).getType() == 0 && board.getStructure(new VertexLocation(i, j, k)).getOwner().equals(p))
						numbSettlements++;
				}
			}
		}
		if (numbSettlements >= 5) {
			return false;
			//TODO: throw error about too many of object owned already
		}

		p.setNumberResourcesType("BRICK", p.getNumberResourcesType("BRICK") - 1);
		p.setNumberResourcesType("LUMBER", p.getNumberResourcesType("LUMBER") - 1);
		p.setNumberResourcesType("GRAIN", p.getNumberResourcesType("GRAIN") - 1);
		p.setNumberResourcesType("WOOL", p.getNumberResourcesType("WOOL") - 1);

		p.setVictoryPoints(p.getVictoryPoints() + 1);
		
		return true;
	}
	
	/**
	 * Buys City for given Player
	 * @param p the given Player
	 * @return whether the Player can buy a City
	 */
	public boolean buyCities(Player p) {
		
		// Check Player has sufficient resources
		if (p.getNumberResourcesType("GRAIN") < 2 || p.getNumberResourcesType("ORE") < 3) {
			return false;
			//TODO: throw error about not enough resources
		}

		// Check Player has not exceeded capacity for object
		int numbCities = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 2; k++) {
					if (board.getStructure(new VertexLocation(i, j, k)).getType() == 1 && board.getStructure(new VertexLocation(i, j, k)).getOwner().equals(p))
						numbCities++;
				}
			}
		}
		if (numbCities >= 4) {
			return false;
			//TODO: throw error about too many of object owned already
		}
		
		p.setNumberResourcesType("GRAIN", p.getNumberResourcesType("GRAIN") - 2);
		p.setNumberResourcesType("ORE", p.getNumberResourcesType("ORE") - 3);

		p.setVictoryPoints(p.getVictoryPoints() + 1);

		return true;
	}
	
	/**
	 * Buys DevCard for given Player
	 * @param p the given Player
	 * @return whether the Player can buy a DevCard
	 */
	public boolean buyDevCard(Player p) {
		
		// Check Player has sufficient resources
		if (p.getNumberResourcesType("ORE") < 1 || p.getNumberResourcesType("WOOL") < 1 || p.getNumberResourcesType("GRAIN") < 1) {
			return false;
			//TODO: throw error about not enough resources
		}

		p.setNumberResourcesType("ORE", p.getNumberResourcesType("ORE") - 1);
		p.setNumberResourcesType("WOOL", p.getNumberResourcesType("WOOL") - 1);
		p.setNumberResourcesType("GRAIN", p.getNumberResourcesType("GRAIN") - 1);

		return true;
	}

	/**
	 * Places a Road for the given Player at the given EdgeLocation
	 * @param p the Player placing
	 * @param loc the EdgeLocation to place the ROad
	 * @return whether the Road can go there
	 */
	public boolean placeRoad(Player p, EdgeLocation loc) {
		return board.placeRoad(loc, p);
	}

	/**
	 * Places a Settlement for the given Player at the given VertexLocation
	 * @param p the Player placing
	 * @param loc the VertexLocation to place the Settlement
	 * @return whether the Settlement can go there
	 */
	public boolean placeSettlement(Player p, VertexLocation loc) {
		return board.placeStructure(loc, p);
	}

	/**
	 * Places a City for the given Player at the given VertexLocation
	 * @param p the Player placing
	 * @param loc the VertexLocation to place the City
	 * @return whether the City can go there
	 */
	public boolean placeCity(Player p, VertexLocation loc) {

		Structure s = board.getStructure(loc);

		if (!s.getOwner().equals(p)) {
			return false;
			//TODO: throw error about unowned settlement
		}

		Structure c = new City(s.getLocation());
		c.setOwner(s.getOwner());
		board.setStructure(loc, c);
		
		return true;
	}
	
//TODO: move to CatanBoard
//		DevCard dC = deck.draw();
//		if (null != dC) {
//			p.addDevCard(dC);
//		}
//		else {
//			//TODO: throw error about no dev cards left
//		}
	
	/**
	 * Getter for board's tiles
	 * @return tile array
	 */
	public Tile[][] getTiles(){
		return board.getTiles();
	}
}
