package edu.psgv.sweng881.game;

import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static edu.psgv.sweng881.utils.TestUtils.addResourceToList;
import static edu.psgv.sweng881.utils.TestUtils.createPlayerTradeTestCaseArguments;

public class PlayerTradeTest {

    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    @ParameterizedTest(name = "TC{index} -> expected: {5}")
    @MethodSource("playerTradeTestCases")
    void shouldTestTrade(Map<String, Integer> playerAResources, Map<String, Integer> playerBResources,
                         ArrayList<String> fromA, ArrayList<String> fromB, boolean expected) {

        Player playerA = players.get(0);
        Player playerB = players.get(1);

        ReflectionTestUtils.setField(playerA, "resources", playerAResources);
        ReflectionTestUtils.setField(playerB, "resources", playerBResources);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        Assertions.assertTrue(game.playerTrade(playerA, playerB, fromA, fromB));
    }

    static Stream<Arguments> playerTradeTestCases() {
        return Stream.of(
                createPlayerTradeTestCaseArguments(2, 2, 1, 0, 1, 0, 2, 1, 2, 1, 0, 2, 0, 2, 1, 0, 1, 0, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 0, 2, 1, 2, 1, 0, 2, 0, 2, 1, 0, 1, 0, 2, 0, 2, 1, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 1, 0, 2, 0, 2, 1, 0, 1, 0, 2, 0, 2, 1, 0, 1, 0, 2, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 2, 1, 0, 1, 0, 2, 0, 2, 1, 0, 1, 0, 2, 1, 2, 1, 0, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 1, 0, 2, 0, 2, 1, 0, 1, 0, 2, 1, 2, 1, 0, 2, 0, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(2, 1, 0, 1, 0, 1, 0, 2, 0, 2, 0, 2, 0, 2, 1, 2, 1, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 1, 0, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 2, 1, 1, 0, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 0, 2, 2, 1, 1, 0, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 0, 2, 2, 1, 1, 0, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 0, 2, 2, 1, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 0, 2, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, true),
                createPlayerTradeTestCaseArguments(0, 0, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 0, 2, 2, 1, 1, 0, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 0, 2, 2, 1, 1, false),
                createPlayerTradeTestCaseArguments(0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 2, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 0, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 2, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 2, 0, 0, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 0, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, 0, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 2, 1, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 1, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, 0, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, 0, 2, 0, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 1, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 2, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 0, 2, 1, 1, 0, 2, 2, 1, 0, 0, 2, 2, 1, 0, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 2, 2, 1, 0, 0, 2, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 2, 2, 1, 0, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 2, 2, 1, 0, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 1, 0, 2, 2, 1, 0, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 2, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 1, 0, 0, 2, 1, 1, 0, 2, 2, 1, 0, 0, 2, 2, 1, 0, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 2, 1, 0, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 2, 2, 1, 0, 0, 2, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 1, 0, 2, 2, 1, 0, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 2, 2, 1, 0, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 0, 2, 1, 1, 0, 2, 2, 1, 0, 0, 2, 2, 1, 0, 0, 2, 1, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 2, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 2, 2, 1, 0, 0, 2, 2, 1, 0, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 2, 1, 0, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 2, 2, 1, 0, 0, 2, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 1, 0, 2, 2, 1, 0, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 2, 0, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, 1, 2, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 0, 1, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 2, 0, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 0, 1, 2, 0, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, 1, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 0, 1, 2, true),
                createPlayerTradeTestCaseArguments(2, 1, 0, 1, 2, 0, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 0, 1, 2, 0, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 0, 1, true),
                createPlayerTradeTestCaseArguments(2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 0, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, 1, 2, 0, 1, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 0, 1, 2, 0, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 0, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 0, true),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 2, 1, 0, 2, 1, 1, 0, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 2, 1, 0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 2, 1, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0, 2, 1, 0, 0, 2, 1, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 0, 2, 2, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0, 2, 1, 0, true),
                createPlayerTradeTestCaseArguments(0, 0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 2, 1, 0, 2, 1, 1, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 1, 0, 2, 1, 0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 2, 1, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 2, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0, 2, 1, 0, 0, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 0, 2, 1, 0, 2, 2, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 2, 1, 0, 2, 1, 1, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 1, 0, 2, 1, 0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 2, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0, 2, 1, 0, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 0, 2, 1, 0, 2, 2, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 2, 1, 0, 2, 1, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 1, 0, 2, 1, 0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 2, 2, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0, 2, 1, 0, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 0, 2, 1, 0, 2, 2, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0, true),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 2, 1, 0, 2, 1, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 2, 2, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0, 2, 1, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, false),
                createPlayerTradeTestCaseArguments(1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, false),
                createPlayerTradeTestCaseArguments(1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, false),
                createPlayerTradeTestCaseArguments(1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, 2, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 0, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, false),
                createPlayerTradeTestCaseArguments(2, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, false),
                createPlayerTradeTestCaseArguments(2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, false),
                createPlayerTradeTestCaseArguments(2, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 2, 1, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 2, 1, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 0, 2, 1, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, 1, 0, 2, 1, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, 1, 0, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, 1, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, 1, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, 1, 0, false),
                createPlayerTradeTestCaseArguments(0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, 1, false),
                createPlayerTradeTestCaseArguments(0, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, false),
                createPlayerTradeTestCaseArguments(0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, false)
        );
    }



}
