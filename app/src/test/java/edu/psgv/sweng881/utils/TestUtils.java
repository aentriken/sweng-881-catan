package edu.psgv.sweng881.utils;

import edu.psgv.sweng881.board.Board;
import edu.psgv.sweng881.board.EdgeLocation;
import edu.psgv.sweng881.board.Road;
import edu.psgv.sweng881.board.Settlement;
import edu.psgv.sweng881.board.Structure;
import edu.psgv.sweng881.game.Player;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static ArrayList<Player> createGenericPlayerList() {

        Player a = new Player("a", Color.blue);

        Player b = new Player("b", Color.yellow);

        Player c = new Player("c", Color.green);

        Player d = new Player("d", Color.orange);

        return new ArrayList<>(List.of(a, b, c, d));
    }

    public static class Resource {
        public static final String WOOL = "WOOL";
        public static final String BRICK = "BRICK";
        public static final String ORE = "ORE";
        public static final String GRAIN = "GRAIN";
        public static final String LUMBER = "LUMBER";


    }

    public static void setRoadAdjacentStructuresNoOwner(Board board, EdgeLocation edgeLocation) {
        Structure[][][] structures = board.getStructures();
        int x = edgeLocation.getXCoord();
        int y = edgeLocation.getYCoord();
        // coords [x,y] w orientation o
        // o of 0 = top left of hex
        // adjacent structures are at [x, y, 0] & [x, y+1, 1]
        if (edgeLocation.getOrientation() == 0) {
            structures[x][y][0] = new Settlement(x, y, 0);
            structures[x][y+1][1] = new Settlement(x, y+1, 1);
        }
        // o of 1 = top right of hex
        // adjacent structures are at [x, y, 0] & [x+1, y+1, 1]
        else if (edgeLocation.getOrientation() == 1) {
            structures[x][y][0] = new Settlement(x, y, 0);
            structures[x+1][y+1][1] = new Settlement(x+1, y+1, 1);
        }
        // o of 2 = right of hex
        // adjacent structures are at [x+1, y+1, 1] & [x, y-1, 0]
        else {
            structures[x+1][y+1][1] = new Settlement(x+1, y+1, 1);
            structures[x][y-1][0] = new Settlement(x, y-1, 0);
        }
    }

    public static void setRoadAdjacentRoadsNoOwner(Board board, EdgeLocation edgeLocation) {
        Road[][][] roads = board.getRoads();
        int x = edgeLocation.getXCoord();
        int y = edgeLocation.getYCoord();
        // coords [x,y] w orientation o
        // o of 0 = top left of hex
        // adjacent structures are at [x-1, y, 1], [x-1, y, 2], [x, y+1, 2], [x, y, 1]
        if (edgeLocation.getOrientation() == 0) {
            roads[x-1][y][1] = new Road(x-1, y, 1);
            roads[x-1][y][2] = new Road(x-1, y, 2);
            roads[x][y+1][2] = new Road(x, y+1, 2);
            roads[x][y][1] = new Road(x, y, 1);
        }
        // o of 1 = top right of hex
        // adjacent structures are at [x, y, 2], [x, y, 0], [x, y+1, 2], [x+1, y, 0]
        else if (edgeLocation.getOrientation() == 1) {
            roads[x][y][2] = new Road(x, y, 2);
            roads[x][y][0] = new Road(x, y, 0);
            roads[x][y+1][2] = new Road(x, y+1, 2);
            roads[x+1][y][0] = new Road(x+1, y, 0);
        }
        // o of 2 = right of hex
        // adjacent structures are at [x, y, 1], [x, y-1, 0], [x, y-1, 1], [x+1, y, 0]
        else {
            roads[x][y][1] = new Road(x, y, 1);
            roads[x][y-1][0] = new Road(x, y-1, 0);
            roads[x][y-1][1] = new Road(x, y-1, 1);
            roads[x+1][y][0] = new Road(x+1, y, 0);
        }
    }
    
    public static void resetEdgeLocationOwner(Board board, EdgeLocation edgeLocation) {
        Road[][][] roads = board.getRoads();
        int x = edgeLocation.getXCoord();
        int y = edgeLocation.getYCoord();
        int o = edgeLocation.getOrientation();
        roads[x][y][o] = new Road(x, y, o);
    }

}
