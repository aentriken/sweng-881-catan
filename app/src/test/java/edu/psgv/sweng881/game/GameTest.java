package edu.psgv.sweng881.game;

import edu.psgv.sweng881.board.Board;
import edu.psgv.sweng881.board.EdgeLocation;
import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    /**
     * Parameterized test for buyDevCard method
     * @param ore - Number of ORE resources
     * @param wool - Number of WOOL resources
     * @param grain - Number of GRAIN resources
     * @param deckEmpty - Whether the deck is empty
     * @param expectedResult - Expected result of the buyDevCard method
     */
    @ParameterizedTest(name = "TC{index} -> ORE: {0}, WOOL: {1}, GRAIN: {2}, DeckEmpty: {3} - expecting {4}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#buyDevCardCombos")
    void shouldBuyDevCard(int ore, int wool, int grain, boolean deckEmpty, int expectedResult) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);
        Player player = players.get(0);

        player.setNumberResourcesType("ORE", ore);
        player.setNumberResourcesType("WOOL", wool);
        player.setNumberResourcesType("GRAIN", grain);

        if (deckEmpty) {
            game.setEmptyDeck();  // Set the deck to be empty for this test case
        }

        int result = game.buyDevCard(player);
        Assertions.assertEquals(expectedResult, result);
    }

    @ParameterizedTest(name = "TC{index} -> playerIndex:{0}, VPs: {1} - expecting {2}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#victoryPointsTestData")
    public void shouldTestOver(int playerIndex, int victoryPoints, boolean expectedGameOver) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();

        // Set the victory points for the specified player
        Player player = players.get(playerIndex);
        player.setVictoryPoints(victoryPoints);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        // Check if the game is over
        boolean isGameOver = game.over();

        assertEquals(expectedGameOver, isGameOver,
                "Game should be over when a player has " + victoryPoints + " victory points.");
    }

    /**
     * Test cases for victory point combinations
     * @param victoryPoints - List of victory points for each player
     * @param expectedResult - expected return value from Game.over()
     */
    @ParameterizedTest(name = "TC{index} -> VPs: {0} - expecting {1}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#victoryPointCombos")
    void shouldTestGameOver(List<Integer> victoryPoints, boolean expectedResult) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();

        IntStream.range(0, 4).forEach(i -> {
            players.get(i).setVictoryPoints(victoryPoints.get(i));
        });

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        Assertions.assertEquals(expectedResult, game.over());
    }

    /**
     * Method for running test cases for playerTrade().
     * @param playerAResources - resource set for playerA
     * @param playerBResources - resource set for playerB
     * @param fromA - resources to take from playerA
     * @param fromB - resources to take from playerB
     * @param expected - expected result
     */
    @ParameterizedTest(name = "TC{index} -> expected: {3}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#playerTradeTestCases")
    void shouldTestPlayerTrade(Map<String, Integer> playerAResources, Map<String, Integer> playerBResources,
                               ArrayList<String> fromA, ArrayList<String> fromB, boolean expected) {

        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player playerA = players.get(0);
        Player playerB = players.get(1);

        ReflectionTestUtils.setField(playerA, "resources", playerAResources);
        ReflectionTestUtils.setField(playerB, "resources", playerBResources);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        Assertions.assertTrue(game.playerTrade(playerA, playerB, fromA, fromB));
    }

    /**
     * Method for running placeRoad() test cases.
     * @param edgeLocation - location the road is being placed at
     * @param player - player placing the road
     * @param isOccupied - whether the given edgeLocation is already occupied or not
     * @param adjacentStructuresMatching - Map depicting which adjacent locations are owned by the placing player
     * @param expected - expected result
     */
    @ParameterizedTest(name = "TC{index} -> expecting {4}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#placeRoadTestCases")
    void shouldPlaceRoad(EdgeLocation edgeLocation, int player, boolean isOccupied,
                           Map<String, Boolean> adjacentStructuresMatching,
                           boolean expected) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);
        Board board = game.getBoard();

        Player placingPlayer = players.get(player - 1);

        // create new list that doesn't contain player placing the road
        List<Player> nonPlacingPlayers = new ArrayList<>(players);
        nonPlacingPlayers.remove(placingPlayer);

        // initialize adjacent roads and structures
        Map<String, Player> adjacentRoadOwnerMap = TestUtils.createAdjacentLocationOwnerMap(placingPlayer, adjacentStructuresMatching, nonPlacingPlayers);

        // iterate Map entries and assign ownership
        adjacentRoadOwnerMap.forEach((key, value) -> TestUtils.setRoadAdjacentStructureOwner(board, edgeLocation, value, key));

        // set edgeLocation owner
        TestUtils.setEdgeLocationOwner(board, edgeLocation, isOccupied);

        // nothing adjacent to attach to
        Assertions.assertEquals(expected, board.placeRoad(edgeLocation, placingPlayer));
    }
    @ParameterizedTest(name = "TC{index} -> ports:{0}, player:{1}, resource:{2}, resources:{3}, hand:{4}, expecting {5}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#npcTradeCombos")
    void shouldPerformNPCTradesAsExpected(List<Boolean> ports, int player, String resource, ArrayList<String> resourcesGiven, HashMap<String, Integer> playerResources, int expectedResult) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();

        Player playerA = players.get(player-1);

        //add ports
        IntStream.range(0, ports.size()).forEach(index -> {
            if (ports.get(index)) {
                playerA.addPort(index);
            }
        });

        ReflectionTestUtils.setField(playerA, "resources", playerResources);
        ReflectionTestUtils.setField(GameRunner.class, "players", players);

        Game game = new Game(players);

        Assertions.assertEquals(expectedResult, game.npcTrade(playerA, resource, resourcesGiven));
    }

    @ParameterizedTest(name = "TC{index} -> brick: {0}, lumber: {1}, roads:{2}, expecting:{3}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#buyRoadTestCases")
    void shouldBuyRoad(int brick, int lumber, int roads, int expected, int expBrick, int expLumber, int expRoads) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);
        player.setNumberResourcesType(TestUtils.Resource.BRICK, brick);
        player.setNumberResourcesType(TestUtils.Resource.LUMBER, lumber);
        ReflectionTestUtils.setField(player, "numbRoads", roads);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);

        Game game = new Game(players);

        Assertions.assertEquals(expected, game.buyRoad(player));
        Assertions.assertEquals(expBrick, player.getNumberResourcesType(TestUtils.Resource.BRICK));
        Assertions.assertEquals(expLumber, player.getNumberResourcesType(TestUtils.Resource.LUMBER));
        Assertions.assertEquals(expRoads, player.getNumbRoads());
    }

    @ParameterizedTest(name = "TC{index} -> brick: {0}, wool:{1}, lumber: {2}, grain:{3}, settlements:{4}, expecting:{5}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#buySettlementTestCases")
    void shouldBuySettlement(int brick, int wool, int lumber, int grain, int settlements,
                             int expected, int expBrick, int expWool, int expLumber, int expGrain, int expSettlements) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);
        player.setNumberResourcesType(TestUtils.Resource.BRICK, brick);
        player.setNumberResourcesType(TestUtils.Resource.WOOL, wool);
        player.setNumberResourcesType(TestUtils.Resource.LUMBER, lumber);
        player.setNumberResourcesType(TestUtils.Resource.GRAIN, grain);
        ReflectionTestUtils.setField(player, "numbSettlements", settlements);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);

        Game game = new Game(players);

        Assertions.assertEquals(expected, game.buySettlement(player));
        Assertions.assertEquals(expBrick, player.getNumberResourcesType(TestUtils.Resource.BRICK));
        Assertions.assertEquals(expWool, player.getNumberResourcesType(TestUtils.Resource.WOOL));
        Assertions.assertEquals(expLumber, player.getNumberResourcesType(TestUtils.Resource.LUMBER));
        Assertions.assertEquals(expGrain, player.getNumberResourcesType(TestUtils.Resource.GRAIN));
        Assertions.assertEquals(expSettlements, player.getNumbSettlements());
    }

    @ParameterizedTest(name = "TC{index} -> grain: {0}, ore: {1}, cities:{2}, expecting:{3}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#buyCityTestCases")
    void shouldBuyCity(int grain, int ore, int cities, int expected, int expGrain, int expOre, int expCities) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);
        player.setNumberResourcesType(TestUtils.Resource.GRAIN, grain);
        player.setNumberResourcesType(TestUtils.Resource.ORE, ore);
        ReflectionTestUtils.setField(player, "numbCities", cities);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);

        Game game = new Game(players);

        Assertions.assertEquals(expected, game.buyCity(player));
        Assertions.assertEquals(expGrain, player.getNumberResourcesType(TestUtils.Resource.GRAIN));
        Assertions.assertEquals(expOre, player.getNumberResourcesType(TestUtils.Resource.ORE));
        Assertions.assertEquals(expCities, player.getNumbCities());
    }

    @ParameterizedTest(name = "TC{index} -> player1Cards: {0}, player2Cards:{1}, player3Cards,:{2}, " +
            "player4Cards:{3}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#halfCardsTestCases")
    void shouldHalfCards(List<Integer> player1Cards, List<Integer> player2Cards, List<Integer> player3Cards,
                         List<Integer> player4Cards) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        Player player3 = players.get(2);
        Player player4 = players.get(3);

        int player1Total = TestUtils.setPlayerCards(player1Cards, player1);
        int player2Total = TestUtils.setPlayerCards(player2Cards, player2);
        int player3Total = TestUtils.setPlayerCards(player3Cards, player3);
        int player4Total = TestUtils.setPlayerCards(player4Cards, player4);

        HashMap<Player, Integer> playerTotalMap = new HashMap<>(Map.of(
                player1, player1Total,
                player2, player2Total,
                player3, player3Total,
                player4, player4Total
        ));

        ReflectionTestUtils.setField(GameRunner.class, "players", players);

        Game game = new Game(players);
        game.halfCards();

        players.forEach(player -> {
            int expectedTotalCards = playerTotalMap.get(player);
            if (expectedTotalCards > 7) {
                expectedTotalCards = expectedTotalCards/2;
            }
            //validate no card values are negative
            TestUtils.Resource.getAllResources().forEach(resource -> {
                if(player.getNumberResourcesType(resource) < 0) {
                    //fail the test
                    Assertions.assertTrue(false);
                }
            });


            Assertions.assertEquals(expectedTotalCards, player.getTotalResources());
        });
    }

    @ParameterizedTest(name = "TC{index} -> resource:{0}, player1:{1}, player2:{2}, player3:{3}, " +
            "player4:{4}, expectedTotalForPlayer{5}:{6}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#takeAllTestCases")
    void shouldTakeAll(String resource, int player1Resources, int player2Resources, int player3Resources,
                       int player4Resources, int player, int expectedTotal) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        players.get(0).setNumberResourcesType(resource, player1Resources);
        players.get(1).setNumberResourcesType(resource, player2Resources);
        players.get(2).setNumberResourcesType(resource, player3Resources);
        players.get(3).setNumberResourcesType(resource, player4Resources);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        Player playerTaking = players.get(player-1);

        game.takeAll(resource, playerTaking);

        Assertions.assertEquals(expectedTotal, playerTaking.getNumberResourcesType(resource));
    }

    @ParameterizedTest(name = "TC{index} -> player1Brick:{0}, player1Wool:{1}, player1Ore:{2}, player1Grain:{3}, player1Lumber:{4}," +
            "player2Brick:{5}, player2Wool:{6}, player2Ore:{7}, player2Grain:{8}, player2Lumber:{9}")
    @MethodSource("edu.psgv.sweng881.game.GameTestTCs#takeCardTestCases")
    void shouldTakeAll(int brick1, int wool1, int ore1, int grain1, int lumber1,
                       int brick2, int wool2, int ore2, int grain2, int lumber2, int expectedTakingTotal, int expectedGivingTotal) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player playerTaking = players.get(0);
        playerTaking.setNumberResourcesType(TestUtils.Resource.BRICK, brick1);
        playerTaking.setNumberResourcesType(TestUtils.Resource.WOOL, wool1);
        playerTaking.setNumberResourcesType(TestUtils.Resource.ORE, ore1);
        playerTaking.setNumberResourcesType(TestUtils.Resource.GRAIN, grain1);
        playerTaking.setNumberResourcesType(TestUtils.Resource.LUMBER, lumber1);

        Player playerGiving = players.get(1);
        playerGiving.setNumberResourcesType(TestUtils.Resource.BRICK, brick2);
        playerGiving.setNumberResourcesType(TestUtils.Resource.WOOL, wool2);
        playerGiving.setNumberResourcesType(TestUtils.Resource.ORE, ore2);
        playerGiving.setNumberResourcesType(TestUtils.Resource.GRAIN, grain2);
        playerGiving.setNumberResourcesType(TestUtils.Resource.LUMBER, lumber2);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        game.takeCard(playerTaking, playerGiving);

        Assertions.assertEquals(expectedTakingTotal, playerTaking.getTotalResources());
        Assertions.assertEquals(expectedGivingTotal, playerGiving.getTotalResources());
    }
}
