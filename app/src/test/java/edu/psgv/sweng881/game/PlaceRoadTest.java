package edu.psgv.sweng881.game;

import edu.psgv.sweng881.board.Board;
import edu.psgv.sweng881.board.EdgeLocation;
import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlaceRoadTest {

    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    private static final Player placingPlayer = players.get(0);

    static Stream<Arguments> placeRoadTestCases() {
        return Stream.of(

                createTestCase(3, 4, 1, 4, false, false, true, true, true, true, true, true),
                createTestCase(7, 7, 0, 3, true, false, true, true, false, true, true, false),
                createTestCase(7, 7, 1, 4, false, true, false, false, true, false, false, false),
                createTestCase(7, 7, 1, 2, true, false, false, true, false, false, true, false),
                createTestCase(7, 7, 2, 3, false, true, true, false, true, true, false, false),
                createTestCase(7, 7, 1, 1, false, false, true, false, false, true, false, false),
                createTestCase(3, 4, 1, 1, false, false, true, true, false, false, true, true),
                createTestCase(3, 4, 2, 1, true, true, false, false, true, true, false, false),
                createTestCase(3, 4, 0, 2, false, true, true, true, false, false, true, true),
                createTestCase(3, 4, 2, 4, false, true, true, false, false, true, true, true),
                createTestCase(3, 4, 0, 1, true, false, false, true, true, false, false, false),
                createTestCase(3, 4, 2, 3, false, false, true, true, true, false, false, true),
                createTestCase(3, 4, 0, 4, true, true, false, false, true, true, true, false),
                createTestCase(7, 7, 2, 2, true, true, false, false, true, true, true, false),
                createTestCase(7, 7, 2, 1, true, false, false, true, true, false, false, false),
                createTestCase(7, 7, 0, 2, false, true, true, false, false, true, true, false),
                createTestCase(7, 7, 1, 3, true, false, false, true, true, true, false, false),
                createTestCase(7, 7, 2, 4, false, true, true, true, false, false, true, false),
                createTestCase(7, 7, 0, 1, true, true, false, false, true, true, false, false),
                createTestCase(7, 7, 1, 1, false, false, true, true, false, false, true, false),
                createTestCase(3, 4, 0, 4, true, true, false, true, true, false, true, false),
                createTestCase(3, 4, 1, 1, false, false, true, false, false, true, false, true),
                createTestCase(3, 4, 2, 2, true, true, false, true, true, false, true, false),
                createTestCase(3, 4, 0, 3, false, true, true, false, true, true, false, true),
                createTestCase(3, 4, 1, 4, true, false, false, true, false, false, true, false),
                createTestCase(3, 4, 2, 1, false, true, true, false, true, true, false, true),
                createTestCase(3, 4, 0, 1, true, false, true, true, false, true, true, false),
                createTestCase(3, 4, 1, 2, false, true, false, false, true, false, false, true),
                createTestCase(3, 4, 2, 3, true, false, true, true, false, true, true, false),
                createTestCase(7, 7, 1, 2, false, false, true, true, true, true, true, false),
                createTestCase(7, 7, 2, 3, true, true, true, false, false, false, false, false),
                createTestCase(7, 7, 0, 4, false, false, false, true, true, true, true, false),
                createTestCase(7, 7, 1, 1, true, true, true, true, false, false, false, false),
                createTestCase(7, 7, 2, 1, false, false, false, false, true, true, true, false),
                createTestCase(7, 7, 0, 2, true, true, true, true, true, false, false, false),
                createTestCase(7, 7, 1, 3, true, false, false, false, false, true, true, false),
                createTestCase(7, 7, 2, 4, false, true, true, true, true, true, false, false),
                createTestCase(7, 7, 0, 1, true, true, false, false, false, false, true, false),
                createTestCase(3, 4, 2, 4, true, false, true, false, true, false, true, false),
                createTestCase(3, 4, 0, 1, false, true, false, true, false, true, false, true),
                createTestCase(3, 4, 1, 1, true, false, true, false, true, false, true, false),
                createTestCase(3, 4, 2, 2, true, true, false, true, false, true, false, false),
                createTestCase(3, 4, 0, 3, false, true, true, false, true, false, true, true),
                createTestCase(3, 4, 1, 4, true, false, true, true, false, true, false, false),
                createTestCase(3, 4, 2, 1, false, true, false, true, true, false, true, true),
                createTestCase(3, 4, 0, 2, true, false, true, false, true, true, false, false),
                createTestCase(3, 4, 1, 3, false, true, false, true, false, true, true, true)
        );
    }

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
        return Arguments.of(edgeLocation, players.get(player - 1), isOccupied, adjacentStructuresMatching, expected);
    }

    @ParameterizedTest(name = "TC{index} -> expecting {4}")
    @MethodSource("placeRoadTestCases")
    void shouldDoSomething(EdgeLocation edgeLocation, Player placingPlayer, boolean isOccupied,
                           Map<String, Boolean> adjacentStructuresMatching,
                           boolean expected) {

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);
        Board board = game.getBoard();

        // create new list that doesn't contain player placing the road
        List<Player> nonPlacingPlayers = new ArrayList<>(players);
        nonPlacingPlayers.remove(placingPlayer);

        // initialize adjacent roads and structures
        Map<String, Player> adjacentRoadOwnerMap = createAdjacentLocationOwnerMap(placingPlayer, adjacentStructuresMatching, nonPlacingPlayers);

        // iterate Map entries and assign ownership
        adjacentRoadOwnerMap.forEach((key, value) -> TestUtils.setRoadAdjacentStructureOwner(board, edgeLocation, value, key));

        // set edgeLocation owner
        TestUtils.setEdgeLocationOwner(board, edgeLocation, isOccupied);

        // nothing adjacent to attach to
        Assertions.assertEquals(expected, board.placeRoad(edgeLocation, placingPlayer));
    }

    private static Map<String, Player> createAdjacentLocationOwnerMap(Player placingPlayer, Map<String, Boolean> adjacentRoadsMatching, List<Player> nonPlacingPlayers) {
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
}
