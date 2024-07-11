package edu.psgv.sweng881.game;

import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class GameOverTest {

    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    /**
     * Test case combinations for victory points
     * @return Stream of Arguments
     */
    static Stream<Arguments> victoryPointCombos() {
        return Stream.of(
                Arguments.of(List.of(2, 5, 8, 10), true),
                Arguments.of(List.of(2, 4, 6, 9), false)
        );
    }

    /**
     * Test cases for victory point combinations
     * @param victoryPoints - List of victory points for each player
     * @param expectedResult - expected return value from Game.over()
     */
    @ParameterizedTest(name = "TC{index} -> VPs: {0} - expecting {1}")
    @MethodSource("victoryPointCombos")
    void shouldEndGameWhenPlayerHasTenVPs(List<Integer> victoryPoints, boolean expectedResult) {

        IntStream.range(0, 4).forEach(i -> {
            players.get(i).setVictoryPoints(victoryPoints.get(i));
        });

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        Assertions.assertEquals(expectedResult, game.over());
    }

}
