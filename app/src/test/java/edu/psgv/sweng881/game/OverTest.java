package edu.psgv.sweng881.game;

import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.ArrayList;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OverTest {

    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    private Game game;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        game = new Game(players);
    }

    @ParameterizedTest
    @MethodSource("victoryPointsTestData")
    public void testGameOverWhenWinningPlayerExists(int playerIndex, int victoryPoints, boolean expectedGameOver) {
        // Set the victory points for the specified player
        Player player = players.get(playerIndex);
        player.setVictoryPoints(victoryPoints);

        // Check if the game is over
        boolean isGameOver = game.over();

        assertEquals(expectedGameOver, isGameOver,
                "Game should be over when a player has " + victoryPoints + " victory points.");
    }

    // Method providing test data for different victory points scenarios
    static Stream<Arguments> victoryPointsTestData() {
        return Stream.of(
                Arguments.of(0, 9, false),   // Player 0 has less than 10 victory points
                Arguments.of(1, 10, true),   // Player 1 has exactly 10 victory points
                Arguments.of(2, 11, true)    // Player 2 has more than 10 victory points
        );
    }
}
