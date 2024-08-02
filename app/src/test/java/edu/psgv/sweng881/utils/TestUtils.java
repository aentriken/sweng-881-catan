package edu.psgv.sweng881.utils;

import edu.psgv.sweng881.board.Board;
import edu.psgv.sweng881.board.DevCard;
import edu.psgv.sweng881.board.EdgeLocation;
import edu.psgv.sweng881.board.Road;
import edu.psgv.sweng881.board.Structure;
import edu.psgv.sweng881.game.Player;
import org.junit.jupiter.params.provider.Arguments;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

    public static ArrayList<Player> createGenericPlayerList() {

        Player a = new Player("a", Color.blue);

        Player b = new Player("b", Color.yellow);

        Player c = new Player("c", Color.green);

        Player d = new Player("d", Color.orange);

        return new ArrayList<>(List.of(a, b, c, d));
    }

    public static Map<String, Player> createAdjacentLocationOwnerMap(Player placingPlayer, Map<String, Boolean> adjacentRoadsMatching, List<Player> nonPlacingPlayers) {
        Random random = new Random();

        // create Map of AdjacentLocation strings with the player that will own the AdjacentLocation
        Map<String, Player> adjacentRoadOwnerMap = adjacentRoadsMatching.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> {
                    if (e.getValue()) {
                        return placingPlayer;
                    }
                    else {
                        int index = random.nextInt(3);
                        return nonPlacingPlayers.get(index);
                    }
                }
        ));
        return adjacentRoadOwnerMap;
    }

    public static HashMap<String, Integer> createPlayerHand(List<Integer> resources) {
        HashMap<String, Integer> playerResources = new HashMap<>();
        playerResources.put(Resource.BRICK, resources.get(0));
        playerResources.put(Resource.WOOL, resources.get(1));
        playerResources.put(Resource.ORE, resources.get(2));
        playerResources.put(Resource.GRAIN, resources.get(3));
        playerResources.put(Resource.LUMBER, resources.get(4));
        return playerResources;
    }

    public static ArrayList<String> createCardsGiven(List<Integer> resources) {
        ArrayList<String> cardsGiven = new ArrayList<>();
        IntStream.range(0, resources.size()).forEach(index -> {
            //get num cards
            int num = resources.get(index);

            //get resource
            String resource = Resource.getAllResources().get(index);

            //add resources
            IntStream.range(0, num).forEach(val -> {
                cardsGiven.add(resource);
            });
        });
        return cardsGiven;
    }

    public static int setPlayerCards(List<Integer> cards, Player player) {
        player.setNumberResourcesType(Resource.BRICK, cards.get(0));
        player.setNumberResourcesType(Resource.WOOL, cards.get(1));
        player.setNumberResourcesType(Resource.ORE, cards.get(2));
        player.setNumberResourcesType(Resource.GRAIN, cards.get(3));
        player.setNumberResourcesType(Resource.LUMBER, cards.get(4));
        return cards.stream().collect((Collectors.summingInt(Integer::intValue)));
    }

    public static class AdjacentLocation {
        public static final String TOP_LEFT = "top left";
        public static final String TOP_RIGHT = "top right";
        public static final String BOTTOM_LEFT = "bottom left";
        public static final String BOTTOM_RIGHT = "bottom right";
        public static final String UPPER = "upper";
        public static final String LOWER = "lower";
    }

    public static class Resource {
        public static final String WOOL = "WOOL";
        public static final String BRICK = "BRICK";
        public static final String ORE = "ORE";
        public static final String GRAIN = "GRAIN";
        public static final String LUMBER = "LUMBER";

        public static List<String> getAllResources() {
            return List.of(BRICK, WOOL, ORE, GRAIN, LUMBER);
        }
    }

    public static void setRoadAdjacentStructureOwner(Board board, EdgeLocation edgeLocation,
                                                   Player owningPlayer, String location) {
        Road[][][] roads = board.getRoads();
        Structure[][][] structures = board.getStructures();
        int x = edgeLocation.getXCoord();
        int y = edgeLocation.getYCoord();

        // coords [x,y] w orientation o
        // o of 0 = top left of hex
        // adjacent structures are at [x-1, y, 1], [x-1, y, 2], [x, y+1, 2], [x, y, 1]
        if (edgeLocation.getOrientation() == 0) {
            switch (location) {
                case AdjacentLocation.TOP_LEFT -> roads[x-1][y][1].setOwner(owningPlayer);
                case AdjacentLocation.BOTTOM_LEFT -> roads[x-1][y][2].setOwner(owningPlayer);
                case AdjacentLocation.TOP_RIGHT -> roads[x][y+1][2].setOwner(owningPlayer);
                case AdjacentLocation.BOTTOM_RIGHT -> roads[x][y][1].setOwner(owningPlayer);
                case AdjacentLocation.UPPER -> structures[x][y][0].setOwner(owningPlayer);
                case AdjacentLocation.LOWER -> structures[x][y+1][1].setOwner(owningPlayer);
            }
        }
        // o of 1 = top right of hex
        // adjacent structures are at [x, y, 2], [x, y, 0], [x, y+1, 2], [x+1, y, 0]
        else if (edgeLocation.getOrientation() == 1) {
            switch (location) {
                case AdjacentLocation.TOP_LEFT -> roads[x][y+1][2].setOwner(owningPlayer);
                case AdjacentLocation.BOTTOM_LEFT -> roads[x][y][0].setOwner(owningPlayer);
                case AdjacentLocation.TOP_RIGHT -> roads[x+1][y][0].setOwner(owningPlayer);
                case AdjacentLocation.BOTTOM_RIGHT -> roads[x][y][2].setOwner(owningPlayer);
                case AdjacentLocation.UPPER -> structures[x+1][y+1][1].setOwner(owningPlayer);
                case AdjacentLocation.LOWER -> structures[x][y][0].setOwner(owningPlayer);
            }
        }
        // o of 2 = right of hex
        // adjacent structures are at [x, y, 1], [x, y-1, 0], [x, y-1, 1], [x+1, y, 0]
        else {
            switch (location) {
                case AdjacentLocation.TOP_LEFT -> roads[x][y][1].setOwner(owningPlayer);
                case AdjacentLocation.BOTTOM_LEFT -> roads[x][y-1][0].setOwner(owningPlayer);
                case AdjacentLocation.TOP_RIGHT -> roads[x+1][y][0].setOwner(owningPlayer);
                case AdjacentLocation.BOTTOM_RIGHT -> roads[x][y-1][1].setOwner(owningPlayer);
                case AdjacentLocation.UPPER -> structures[x+1][y+1][1].setOwner(owningPlayer);
                case AdjacentLocation.LOWER -> structures[x][y-1][0].setOwner(owningPlayer);
            }
        }
    }

    public static void setEdgeLocationOwner(Board board, EdgeLocation edgeLocation, Boolean isOccupied) {
        Road[][][] roads = board.getRoads();
        int x = edgeLocation.getXCoord();
        int y = edgeLocation.getYCoord();
        int o = edgeLocation.getOrientation();
        if (isOccupied) {
            // doesn't matter what player is the owner
            roads[x][y][o].setOwner(new Player("test", Color.blue));
        }
        else {
            roads[x][y][o] = new Road(x, y, o);
        }

    }

    public static void addResourceToList(List<String> resourceList, String resource, int amount) {
        IntStream.range(0, amount).forEach(amt -> {
            resourceList.add(resource);
        });
    }

    public static Arguments createPlayerTradeTestCaseArguments(int aBrick, int aWool, int aOre, int aLumber, int aGrain,
                                                                int bBrick, int bWool, int bOre, int bLumber, int bGrain,
                                                                int fromABrick, int fromAWool, int fromAOre, int fromALumber, int fromAGrain,
                                                                int fromBBrick, int fromBWool, int fromBOre, int fromBLumber, int fromBGrain,
                                                                boolean expected) {
        Map<String, Integer> playerAResources = new HashMap<>();
        Map<String, Integer> playerBResources = new HashMap<>();
        ArrayList<String> fromA = new ArrayList<>();
        ArrayList<String> fromB = new ArrayList<>();

        playerAResources.put(TestUtils.Resource.BRICK, aBrick);
        playerAResources.put(TestUtils.Resource.WOOL, aWool);
        playerAResources.put(TestUtils.Resource.ORE, aOre);
        playerAResources.put(TestUtils.Resource.LUMBER, aLumber);
        playerAResources.put(TestUtils.Resource.GRAIN, aGrain);

        playerBResources.put(TestUtils.Resource.BRICK, bBrick);
        playerBResources.put(TestUtils.Resource.WOOL, bWool);
        playerBResources.put(TestUtils.Resource.ORE, bOre);
        playerBResources.put(TestUtils.Resource.LUMBER, bLumber);
        playerBResources.put(TestUtils.Resource.GRAIN, bGrain);

        addResourceToList(fromA, TestUtils.Resource.BRICK, fromABrick);
        addResourceToList(fromA, TestUtils.Resource.WOOL, fromAWool);
        addResourceToList(fromA, TestUtils.Resource.ORE, fromAOre);
        addResourceToList(fromA, TestUtils.Resource.LUMBER, fromALumber);
        addResourceToList(fromA, TestUtils.Resource.GRAIN, fromAGrain);

        addResourceToList(fromB, TestUtils.Resource.BRICK, fromBBrick);
        addResourceToList(fromB, TestUtils.Resource.WOOL, fromBWool);
        addResourceToList(fromB, TestUtils.Resource.ORE, fromBOre);
        addResourceToList(fromB, TestUtils.Resource.LUMBER, fromBLumber);
        addResourceToList(fromB, TestUtils.Resource.GRAIN, fromBGrain);

        return Arguments.of(playerAResources, playerBResources, fromA, fromB, expected);
    }

    /**
     * Method to transform raw test cases into more easily used data structures.
     * @param x - x coordinate of edgeLocation
     * @param y - y coordinate of edgeLocation
     * @param o - orientation of edgeLocation
     * @param player - player placing the road
     * @param isOccupied - boolean for if the edgeLocation is already occupied
     * @param tlMatches - boolean for if the edgeLocation to the top left of the given edgeLocation
     *                  is owned by the player
     * @param trMatches - boolean for if the edgeLocation to the top right of the given edgeLocation
     *                  is owned by the player
     * @param blMatches - boolean for if the edgeLocation to the bottom left of the given edgeLocation
     *                  is owned by the player
     * @param brMatches - boolean for if the edgeLocation to the bottom right of the given edgeLocation
     *                  is owned by the player
     * @param uMatches - boolean for if the vertexLocation above the given edgeLocation
     *                  is owned by the player
     * @param lMatches - boolean for if the vertexLocation below the given edgeLocation
     *                  is owned by the player
     * @param expected - expected result for the test case
     * @return Arguments.of(EdgeLocation, Player, isOccupied, Map of AdjacentLocation String to Boolean, expectedResult)
     */
    public static Arguments createTestCase(int x, int y, int o, int player, boolean isOccupied, boolean tlMatches, boolean trMatches,
                                           boolean blMatches, boolean brMatches, boolean uMatches, boolean lMatches, boolean expected) {
        // create EdgeLocation
        EdgeLocation edgeLocation = new EdgeLocation(x, y, o);

        // create Map of AdjacentLocations and whether their owner matches the player or not
        Map<String, Boolean> adjacentStructuresMatching = new HashMap<>();
        adjacentStructuresMatching.put(TestUtils.AdjacentLocation.TOP_LEFT, tlMatches);
        adjacentStructuresMatching.put(TestUtils.AdjacentLocation.TOP_RIGHT, trMatches);
        adjacentStructuresMatching.put(TestUtils.AdjacentLocation.BOTTOM_LEFT, blMatches);
        adjacentStructuresMatching.put(TestUtils.AdjacentLocation.BOTTOM_RIGHT, brMatches);
        adjacentStructuresMatching.put(TestUtils.AdjacentLocation.UPPER, uMatches);
        adjacentStructuresMatching.put(TestUtils.AdjacentLocation.LOWER, lMatches);

        // package it all up into an argument
        return Arguments.of(edgeLocation, player, isOccupied, adjacentStructuresMatching, expected);
    }

    public static ArrayList<DevCard> createDevCardHand(List<Integer> cards) {
        ArrayList<DevCard> devCards = new ArrayList<>();
        IntStream.range(0, cards.get(0)).forEach(val -> {
            devCards.add(new DevCard("Knight"));
        });
        IntStream.range(0, cards.get(1)).forEach(val -> {
            devCards.add(new DevCard("Progress", "Road building"));
        });
        IntStream.range(0, cards.get(2)).forEach(val -> {
            devCards.add(new DevCard("Progress", "Year of plenty"));
        });
        IntStream.range(0, cards.get(3)).forEach(val -> {
            devCards.add(new DevCard("Progress", "Monopoly"));
        });
        IntStream.range(0, cards.get(4)).forEach(val -> {
            devCards.add(new DevCard("Victory Point"));
        });
        return devCards;
    }

}
