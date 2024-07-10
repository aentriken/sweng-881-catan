package edu.psgv.sweng881.game;

import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class GameTest {

    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    @BeforeAll
    public static void setup() {
        ReflectionTestUtils.setField(GameRunner.class, "players", players);
    }

    @Test
    void shouldRollDie() {

        Game game = new Game(players);

        Assertions.assertTrue(game.roll(Mockito.mock(Player.class)) != 0);
    }


}
