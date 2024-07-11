package edu.psgv.sweng881.game;

import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

class TakeAllTest {

    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    @Test
    void shouldDoSomething() {

        players.forEach(player -> {
            player.setNumberResourcesType(TestUtils.Resource.WOOL, 1);
            player.setNumberResourcesType(TestUtils.Resource.BRICK, 1);
            player.setNumberResourcesType(TestUtils.Resource.ORE, 1);
            player.setNumberResourcesType(TestUtils.Resource.GRAIN, 1);
            player.setNumberResourcesType(TestUtils.Resource.LUMBER, 1);
        });

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        Player playerTaking = players.get(0);

        game.takeAll(TestUtils.Resource.BRICK, playerTaking);

        Assertions.assertEquals(4, playerTaking.getNumberResourcesType(TestUtils.Resource.BRICK));

    }

}
