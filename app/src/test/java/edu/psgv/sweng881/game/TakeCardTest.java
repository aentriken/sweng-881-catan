package edu.psgv.sweng881.game;

import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

public class TakeCardTest {


    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    @Test
    void shouldTakeACardAndGiveItToPlayer() {
        Player playerA = players.get(0);
        Player choicePlayer = players.get(1);

        playerA.setNumberResourcesType(TestUtils.Resource.WOOL, 0);
        playerA.setNumberResourcesType(TestUtils.Resource.BRICK, 0);
        playerA.setNumberResourcesType(TestUtils.Resource.ORE, 0);
        playerA.setNumberResourcesType(TestUtils.Resource.GRAIN, 0);
        playerA.setNumberResourcesType(TestUtils.Resource.LUMBER, 0);

        choicePlayer.setNumberResourcesType(TestUtils.Resource.WOOL, 0);
        choicePlayer.setNumberResourcesType(TestUtils.Resource.BRICK, 0);
        choicePlayer.setNumberResourcesType(TestUtils.Resource.ORE, 0);
        choicePlayer.setNumberResourcesType(TestUtils.Resource.GRAIN, 0);
        choicePlayer.setNumberResourcesType(TestUtils.Resource.LUMBER, 1);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        game.takeCard(playerA, choicePlayer);

        Assertions.assertEquals(0, choicePlayer.getNumberResourcesType(TestUtils.Resource.LUMBER));
        Assertions.assertEquals(1, playerA.getNumberResourcesType(TestUtils.Resource.LUMBER));
    }

}
