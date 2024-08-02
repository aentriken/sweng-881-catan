package edu.psgv.sweng881.player;

import edu.psgv.sweng881.board.DevCard;
import edu.psgv.sweng881.game.Game;
import edu.psgv.sweng881.game.GameRunner;
import edu.psgv.sweng881.game.Player;
import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class PlayerTest {

    @ParameterizedTest(name = "TC{index} -> hasLargestArmy:{0}, willHaveLargestArmy:{1}, " +
            "expectedLargestArmy:{2}, expectedVPIncrease:{3}")
    @MethodSource("edu.psgv.sweng881.player.PlayerTestTCs#setHasLargestArmyTestCases")
    void shouldSetHasLargestArmy(boolean hasLargestArmy, boolean willHaveLargestArmy, boolean expectedLargestArmy,
                                 int expectedVPIncrease) {

        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        ReflectionTestUtils.setField(player, "hasLargestArmy", hasLargestArmy);

        int expectedVPs = player.getVictoryPoints() + expectedVPIncrease;

        Game game = new Game(players);

        player.setHasLargestArmy(willHaveLargestArmy);

        Assertions.assertEquals(expectedLargestArmy, player.hasLargestArmy());
        Assertions.assertEquals(expectedVPs, player.getVictoryPoints());
    }

    @ParameterizedTest(name = "TC{index} -> startingHand:{0}, requestedCard:{1}, expectedHand: {2}")
    @MethodSource("edu.psgv.sweng881.player.PlayerTestTCs#removeCardTestCases")
    void shouldRemoveCard(List<Integer> cards, String card, List<Integer> expectedCards) {

        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);

        ArrayList<DevCard> devCards = TestUtils.createDevCardHand(cards);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        ReflectionTestUtils.setField(player, "hand", devCards);

        Game game = new Game(players);

        player.removeCard(card);

        Assertions.assertEquals(expectedCards.get(0), player.getDevCardsType("Knight"));
        Assertions.assertEquals(expectedCards.get(1), player.getDevCardsType("Road building"));
        Assertions.assertEquals(expectedCards.get(2), player.getDevCardsType("Year of plenty"));
        Assertions.assertEquals(expectedCards.get(3), player.getDevCardsType("Monopoly"));
        Assertions.assertEquals(expectedCards.get(4), player.getDevCardsType("Victory Point"));
    }
    @ParameterizedTest(name = "TC{index} -> requestedCard:{1}, expected: {2}")
    @MethodSource("edu.psgv.sweng881.player.PlayerTestTCs#hasCardTestCases")
    void shouldReturnHasCard(List<Integer> cards, String card, boolean expected) {

        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);

        ArrayList<DevCard> devCards = TestUtils.createDevCardHand(cards);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        ReflectionTestUtils.setField(player, "hand", devCards);

        Game game = new Game(players);

        boolean result = player.hasCard(card);

        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest(name = "TC{index} -> resource:{0}, hand:{1}, expected: {2}")
    @MethodSource("edu.psgv.sweng881.player.PlayerTestTCs#numResourceOfTypeTestCases")
    void shouldReturnNumResourcesForType(String resource, HashMap<String, Integer> hand, int expected) {

        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);

        ReflectionTestUtils.setField(player, "resources", hand);
        ReflectionTestUtils.setField(GameRunner.class, "players", players);

        Game game = new Game(players);

        Assertions.assertEquals(expected, player.getNumberResourcesType(resource));

    }


    @ParameterizedTest(name = "TC{index} -> devCard:{0}, initialVPs:{1}, expectedVPs: {2}")
    @MethodSource("edu.psgv.sweng881.player.PlayerTestTCs#addDevCardTestCases")
    void shouldAddDevCard(String devCardType, int initialVPs, int expectedVictoryPoints) {

        DevCard devCard = new DevCard(devCardType);
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        ReflectionTestUtils.setField(player, "victoryPoints", initialVPs);

        Game game = new Game(players);

        player.addDevCard(devCard);

        ArrayList<DevCard> cards = (ArrayList<DevCard>) ReflectionTestUtils.getField(player, "hand");
        Assertions.assertEquals(expectedVictoryPoints, player.getVictoryPoints());
        Assertions.assertTrue(cards.contains(devCard));
    }

    @ParameterizedTest(name = "TC{index} -> resource:{0}, expectedCards:{1}")
    @MethodSource("edu.psgv.sweng881.player.PlayerTestTCs#giveResourceTypeTestCases")
    void shouldGiveResourceType(String resource, int expectedCards) {

        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);

        Game game = new Game(players);

        player.giveResourceType(resource);

        Assertions.assertEquals(expectedCards, player.getNumberResourcesType(resource));
    }

    @ParameterizedTest(name = "TC{index} -> resource:{0}, hand:{1}, expectedHand:{2}")
    @MethodSource("edu.psgv.sweng881.player.PlayerTestTCs#removeResourcesTestCases")
    void shouldRemoveResourceType(ArrayList<String> cardsToRemove, HashMap<String, Integer> hand, HashMap<String, Integer> expectedHand) {

        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        ReflectionTestUtils.setField(player, "resources", hand);

        Game game = new Game(players);

        player.removeResources(cardsToRemove);

        TestUtils.Resource.getAllResources().forEach(resource -> {
            Assertions.assertEquals(expectedHand.get(resource), player.getNumberResourcesType(resource));
        });
    }

    @ParameterizedTest(name = "TC{index} -> resource:{0}, hand:{1}, expectedHand: {2}")
    @MethodSource("edu.psgv.sweng881.player.PlayerTestTCs#addResourcesTestCases")
    void shouldAddResourceType(ArrayList<String> cardsToAdd, HashMap<String, Integer> hand, HashMap<String, Integer> expectedHand) {

        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        ReflectionTestUtils.setField(player, "resources", hand);

        Game game = new Game(players);

        player.addResources(cardsToAdd);

        TestUtils.Resource.getAllResources().forEach(resource -> {
            Assertions.assertEquals(expectedHand.get(resource), player.getNumberResourcesType(resource));
        });
    }



    @ParameterizedTest(name = "TC{index} -> devCards:{0}, cardType:{1}, expectedResult:{2}")
    @MethodSource("edu.psgv.sweng881.player.PlayerTestTCs#getDevCardTypeTestCases")
    void shouldGetDevCardType(ArrayList<DevCard> devCards, String card, int expectedResult) {

        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        Player player = players.get(0);

        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        ReflectionTestUtils.setField(player, "hand", devCards);

        Game game = new Game(players);

        int result = player.getDevCardsType(card);

        Assertions.assertEquals(expectedResult, result);
    }

}
