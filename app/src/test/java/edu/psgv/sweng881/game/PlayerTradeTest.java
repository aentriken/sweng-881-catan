package edu.psgv.sweng881.game;

import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayerTradeTest {

    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    @Test
    void shouldDoSomething() {

        Player playerA = players.get(0);
        Player playerB = players.get(1);

        playerA.setNumberResourcesType(TestUtils.Resource.WOOL, 0);
        playerA.setNumberResourcesType(TestUtils.Resource.BRICK, 0);
        playerA.setNumberResourcesType(TestUtils.Resource.ORE, 0);
        playerA.setNumberResourcesType(TestUtils.Resource.GRAIN, 1);
        playerA.setNumberResourcesType(TestUtils.Resource.LUMBER, 0);

        playerB.setNumberResourcesType(TestUtils.Resource.WOOL, 0);
        playerB.setNumberResourcesType(TestUtils.Resource.BRICK, 0);
        playerB.setNumberResourcesType(TestUtils.Resource.ORE, 0);
        playerB.setNumberResourcesType(TestUtils.Resource.GRAIN, 0);
        playerB.setNumberResourcesType(TestUtils.Resource.LUMBER, 1);

        ArrayList<String> fromA = new ArrayList<>(List.of(TestUtils.Resource.GRAIN));
        ArrayList<String> fromB = new ArrayList<>(List.of(TestUtils.Resource.LUMBER));

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        Assertions.assertTrue(game.playerTrade(playerA, playerB, fromA, fromB));
    }

}
