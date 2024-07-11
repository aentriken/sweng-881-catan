package edu.psgv.sweng881.game;

import edu.psgv.sweng881.board.Board;
import edu.psgv.sweng881.board.Deck;
import edu.psgv.sweng881.board.EdgeLocation;
import edu.psgv.sweng881.board.Structure;
import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

public class PlaceRoadTest {

    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    @Test
    void shouldDoSomething() {

        Player playerA = players.get(0);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);
        Board board = game.getBoard();

        EdgeLocation edgeLocation = new EdgeLocation(3, 3, 0);
        TestUtils.setRoadAdjacentRoadsNoOwner(board, edgeLocation);
        TestUtils.setRoadAdjacentStructuresNoOwner(board, edgeLocation);
        TestUtils.resetEdgeLocationOwner(board, edgeLocation);

        // nothing adjacent to attach to
        Assertions.assertFalse(board.placeRoad(edgeLocation, playerA));
    }
}
