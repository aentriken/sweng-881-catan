package edu.psgv.sweng881.game;

import edu.psgv.sweng881.board.Deck;
import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

public class BuyDevCardTest {

    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    @Test
    void shouldDoSomething() {

        Player playerA = players.get(0);
        playerA.setNumberResourcesType(TestUtils.Resource.WOOL, 1);
        playerA.setNumberResourcesType(TestUtils.Resource.ORE, 1);
        playerA.setNumberResourcesType(TestUtils.Resource.GRAIN, 1);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        Game game = new Game(players);

        Deck mockDeck = Mockito.mock(Deck.class);
        ReflectionTestUtils.setField(game, "deck", mockDeck);
        Mockito.when(mockDeck.isEmpty()).thenReturn(true);



        Assertions.assertEquals(2, game.buyDevCard(playerA));
    }

}
