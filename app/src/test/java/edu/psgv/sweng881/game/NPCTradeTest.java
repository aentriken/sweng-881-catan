package edu.psgv.sweng881.game;

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
public class NPCTradeTest {

    public static HashMap<String, Integer> createPlayerHand(List<Integer> resources) {
        HashMap<String, Integer> playerResources = new HashMap<>();
        playerResources.put(TestUtils.Resource.BRICK, resources.get(0));
        playerResources.put(TestUtils.Resource.WOOL, resources.get(1));
        playerResources.put(TestUtils.Resource.ORE, resources.get(2));
        playerResources.put(TestUtils.Resource.GRAIN, resources.get(3));
        playerResources.put(TestUtils.Resource.LUMBER, resources.get(4));
        return playerResources;
    }

    public static ArrayList<String> createCardsGiven(List<Integer> resources) {
        ArrayList<String> cardsGiven = new ArrayList<>();
        IntStream.range(0, resources.size()).forEach(index -> {
            //get num cards
            int num = resources.get(index);

            //get resource
            String resource = TestUtils.Resource.getAllResources().get(index);

            //add resources
            IntStream.range(0, num).forEach(val -> {
                cardsGiven.add(resource);
            });
        });
        return cardsGiven;
    }

    static Stream<Arguments> test() {
        return Stream.of(
                Arguments.of(List.of(true, true, false, true, false, false),  1, TestUtils.Resource.GRAIN, createCardsGiven(List.of(0, 0, 0, 0, 2)), createPlayerHand(List.of(2, 2, 4, 4, 4)), 2)
                );
    }

    @ParameterizedTest(name = "TC{index} -> ports:{0}, player:{1}, resource:{2}, resources:{3}, hand:{4}, expecting {5}")
    @MethodSource("npcTradeCombos")
    void shouldPerformNPCTradesAsExpected(List<Boolean> ports, int player, String resource, ArrayList<String> resourcesGiven, HashMap<String, Integer> playerResources, int expectedResult) {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();

        Player playerA = players.get(player-1);

        //add ports
        IntStream.range(0, ports.size()).forEach(index -> {
            if (ports.get(index)) {
                playerA.addPort(index);
            }
        });

        ReflectionTestUtils.setField(playerA, "resources", playerResources);
        ReflectionTestUtils.setField(GameRunner.class, "players", players);

        Game game = new Game(players);

        Assertions.assertEquals(expectedResult, game.npcTrade(playerA, resource, resourcesGiven));
    }

    /**
     * Test case combinations for npc trades
     * @return Stream of Arguments
     */
    static Stream<Arguments> npcTradeCombos() {
        return Stream.of(
                Arguments.of(List.of(true, true, true, true, true, true),  1, TestUtils.Resource.LUMBER, createCardsGiven(List.of(1, 1, 3, 0, 2)), createPlayerHand(List.of(4, 1, 3, 0, 0)), 1),
                Arguments.of(List.of(true, false, false, false, false, false),  2, TestUtils.Resource.BRICK, createCardsGiven(List.of(0, 2, 4, 1, 3)), createPlayerHand(List.of(0, 2, 4, 1, 1)), 1),
                Arguments.of(List.of(true, true, true, true, true, true),  3, TestUtils.Resource.WOOL, createCardsGiven(List.of(1, 3, 0, 2, 4)), createPlayerHand(List.of(1, 3, 0, 0, 2)), 1),
                Arguments.of(List.of(true, false, false, false, false, false),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 4, 1, 3, 0)), createPlayerHand(List.of(2, 4, 1, 1, 3)), 1),
                Arguments.of(List.of(true, true, true, true, true, true),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(3, 0, 2, 4, 1)), createPlayerHand(List.of(3, 0, 0, 2, 4)), 1),
                Arguments.of(List.of(true, false, false, false, false, false),  1, TestUtils.Resource.ORE, createCardsGiven(List.of(4, 1, 3, 0, 2)), createPlayerHand(List.of(4, 1, 1, 3, 0)), 1),
                Arguments.of(List.of(true, true, true, true, true, true),  2, TestUtils.Resource.GRAIN, createCardsGiven(List.of(0, 2, 4, 1, 3)), createPlayerHand(List.of(0, 0, 2, 4, 1)), 1),
                Arguments.of(List.of(true, false, false, false, false, true),  3, TestUtils.Resource.LUMBER, createCardsGiven(List.of(1, 3, 0, 2, 4)), createPlayerHand(List.of(1, 1, 3, 0, 2)), 1),
                Arguments.of(List.of(true, true, true, false, false, false),  4, TestUtils.Resource.LUMBER, createCardsGiven(List.of(1, 3, 0, 0, 2)), createPlayerHand(List.of(4, 1, 3, 0, 2)), 1),
                Arguments.of(List.of(true, false, true, true, true, true),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 4, 1, 1, 3)), createPlayerHand(List.of(0, 2, 4, 1, 3)), 1),
                Arguments.of(List.of(true, true, false, false, false, false),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(3, 0, 0, 2, 4)), createPlayerHand(List.of(1, 3, 0, 2, 4)), 1),
                Arguments.of(List.of(true, true, true, true, true, true),  3, TestUtils.Resource.ORE, createCardsGiven(List.of(4, 1, 1, 3, 0)), createPlayerHand(List.of(2, 4, 1, 3, 0)), 1),
                Arguments.of(List.of(true, false, false, false, false, false),  4, TestUtils.Resource.GRAIN, createCardsGiven(List.of(0, 0, 2, 4, 1)), createPlayerHand(List.of(3, 0, 2, 4, 1)), 2),
                Arguments.of(List.of(true, true, true, false, false, false),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(1, 0, 1, 0, 1)), createPlayerHand(List.of(2, 1, 2, 3, 2)), 2),
                Arguments.of(List.of(true, false, false, true, true, true),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(0, 1, 2, 1, 2)), createPlayerHand(List.of(3, 2, 3, 4, 3)), 2),
                Arguments.of(List.of(true, true, true, false, false, true),  3, TestUtils.Resource.ORE, createCardsGiven(List.of(1, 2, 3, 2, 3)), createPlayerHand(List.of(4, 3, 4, 0, 4)), 1),
                Arguments.of(List.of(true, false, true, true, true, false),  4, TestUtils.Resource.GRAIN, createCardsGiven(List.of(2, 3, 4, 3, 4)), createPlayerHand(List.of(0, 4, 0, 1, 0)), 1),
                Arguments.of(List.of(true, true, false, false, false, true),  1, TestUtils.Resource.LUMBER, createCardsGiven(List.of(3, 4, 0, 4, 0)), createPlayerHand(List.of(1, 0, 1, 0, 1)), 1),
                Arguments.of(List.of(true, false, true, true, true, false),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(1, 2, 1, 2, 3)), createPlayerHand(List.of(2, 3, 4, 3, 4)), 2),
                Arguments.of(List.of(true, true, false, false, true, true),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(2, 3, 2, 3, 4)), createPlayerHand(List.of(3, 4, 0, 4, 0)), 1),
                Arguments.of(List.of(true, false, false, false, true, true),  3, TestUtils.Resource.GRAIN, createCardsGiven(List.of(4, 0, 4, 0, 1)), createPlayerHand(List.of(0, 1, 0, 1, 2)), 1),
                Arguments.of(List.of(true, true, true, true, false, false),  4, TestUtils.Resource.LUMBER, createCardsGiven(List.of(0, 1, 0, 1, 0)), createPlayerHand(List.of(1, 2, 1, 2, 3)), 2),
                Arguments.of(List.of(true, true, true, true, false, false),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(2, 1, 2, 3, 2)), createPlayerHand(List.of(3, 4, 3, 4, 0)), 1),
                Arguments.of(List.of(true, false, false, true, true, true),  2, TestUtils.Resource.ORE, createCardsGiven(List.of(3, 2, 3, 4, 3)), createPlayerHand(List.of(4, 0, 4, 0, 1)), 1),
                Arguments.of(List.of(false, false, true, true, false, false),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(0, 2, 2, 4, 1)), createPlayerHand(List.of(1, 3, 3, 0, 0)), 1),
                Arguments.of(List.of(false, true, false, false, true, true),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(1, 3, 3, 0, 0)), createPlayerHand(List.of(2, 4, 4, 1, 1)), 2),
                Arguments.of(List.of(false, false, true, true, false, false),  3, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 4, 4, 1, 1)), createPlayerHand(List.of(3, 0, 0, 2, 2)), 1),
                Arguments.of(List.of(false, true, true, false, true, true),  4, TestUtils.Resource.WOOL, createCardsGiven(List.of(3, 0, 0, 2, 2)), createPlayerHand(List.of(4, 1, 1, 3, 3)), 2),
                Arguments.of(List.of(false, false, false, true, false, false),  1, TestUtils.Resource.ORE, createCardsGiven(List.of(4, 1, 1, 3, 3)), createPlayerHand(List.of(0, 0, 2, 4, 4)), 1),
                Arguments.of(List.of(false, true, true, false, false, true),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 2, 4, 1, 1)), createPlayerHand(List.of(3, 3, 0, 0, 2)), 1),
                Arguments.of(List.of(false, false, false, true, true, false),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(3, 3, 0, 0, 2)), createPlayerHand(List.of(4, 4, 1, 1, 3)), 2),
                Arguments.of(List.of(false, true, false, true, true, false),  2, TestUtils.Resource.GRAIN, createCardsGiven(List.of(0, 0, 2, 2, 4)), createPlayerHand(List.of(1, 1, 3, 3, 0)), 1),
                Arguments.of(List.of(false, false, true, false, false, true),  3, TestUtils.Resource.LUMBER, createCardsGiven(List.of(1, 1, 3, 3, 0)), createPlayerHand(List.of(0, 2, 4, 4, 1)), 1),
                Arguments.of(List.of(false, true, false, false, true, false),  2, TestUtils.Resource.ORE, createCardsGiven(List.of(2, 4, 1, 1, 3)), createPlayerHand(List.of(3, 0, 0, 2, 4)), 1),
                Arguments.of(List.of(false, false, true, true, false, true),  3, TestUtils.Resource.GRAIN, createCardsGiven(List.of(3, 0, 0, 2, 4)), createPlayerHand(List.of(4, 1, 1, 3, 0)), 1),
                Arguments.of(List.of(true, false, true, true, true, false),  4, TestUtils.Resource.WOOL, createCardsGiven(List.of(4, 0, 3, 4, 2)), createPlayerHand(List.of(3, 1, 2, 0, 1)), 1),
                Arguments.of(List.of(true, true, false, false, true, true),  1, TestUtils.Resource.ORE, createCardsGiven(List.of(0, 1, 4, 0, 3)), createPlayerHand(List.of(4, 2, 3, 1, 2)), 1),
                Arguments.of(List.of(true, false, true, true, false, false),  1, TestUtils.Resource.GRAIN, createCardsGiven(List.of(1, 2, 0, 1, 4)), createPlayerHand(List.of(0, 3, 4, 2, 3)), 1),
                Arguments.of(List.of(true, false, true, true, false, false),  3, TestUtils.Resource.BRICK, createCardsGiven(List.of(1, 4, 0, 3, 1)), createPlayerHand(List.of(2, 0, 1, 4, 0)), 1),
                Arguments.of(List.of(true, true, false, false, true, true),  2, TestUtils.Resource.GRAIN, createCardsGiven(List.of(4, 2, 3, 1, 2)), createPlayerHand(List.of(0, 1, 4, 0, 3)), 1),
                Arguments.of(List.of(true, true, true, true, false, false),  3, TestUtils.Resource.LUMBER, createCardsGiven(List.of(0, 3, 4, 2, 3)), createPlayerHand(List.of(1, 2, 0, 1, 4)), 1),
                Arguments.of(List.of(true, true, true, false, false, true),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(2, 0, 1, 4, 0)), createPlayerHand(List.of(3, 4, 2, 3, 1)), 1),
                Arguments.of(List.of(true, false, false, true, true, false),  2, TestUtils.Resource.BRICK, createCardsGiven(List.of(3, 1, 2, 0, 1)), createPlayerHand(List.of(4, 0, 3, 4, 2)), 1),
                Arguments.of(List.of(true, false, false, true, true, false),  2, TestUtils.Resource.LUMBER, createCardsGiven(List.of(2, 3, 1, 2, 0)), createPlayerHand(List.of(1, 4, 0, 3, 1)), 1),
                Arguments.of(List.of(false, false, false, true, true, false),  4, TestUtils.Resource.ORE, createCardsGiven(List.of(4, 3, 0, 4, 1)), createPlayerHand(List.of(0, 2, 1, 3, 0)), 1),
                Arguments.of(List.of(false, true, true, false, false, true),  3, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 1, 3, 0, 4)), createPlayerHand(List.of(1, 0, 2, 1, 3)), 1),
                Arguments.of(List.of(false, false, false, true, true, false),  4, TestUtils.Resource.WOOL, createCardsGiven(List.of(3, 0, 4, 1, 0)), createPlayerHand(List.of(2, 1, 3, 2, 4)), 1),
                Arguments.of(List.of(false, true, true, false, false, true),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(4, 1, 0, 2, 1)), createPlayerHand(List.of(3, 2, 4, 3, 0)), 1),
                Arguments.of(List.of(false, false, false, true, true, true),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(0, 2, 1, 3, 2)), createPlayerHand(List.of(4, 3, 0, 4, 1)), 1),
                Arguments.of(List.of(false, true, true, true, false, false),  3, TestUtils.Resource.ORE, createCardsGiven(List.of(1, 3, 2, 4, 3)), createPlayerHand(List.of(0, 4, 1, 0, 2)), 1),
                Arguments.of(List.of(false, true, false, false, true, true),  4, TestUtils.Resource.GRAIN, createCardsGiven(List.of(2, 4, 3, 0, 4)), createPlayerHand(List.of(1, 0, 2, 1, 3)), 1),
                Arguments.of(List.of(false, false, true, true, false, false),  1, TestUtils.Resource.GRAIN, createCardsGiven(List.of(0, 4, 1, 0, 2)), createPlayerHand(List.of(1, 3, 2, 4, 3)), 1),
                Arguments.of(List.of(false, true, false, false, true, true),  1, TestUtils.Resource.LUMBER, createCardsGiven(List.of(1, 0, 2, 1, 3)), createPlayerHand(List.of(2, 4, 3, 0, 4)), 1),
                Arguments.of(List.of(false, false, true, true, true, false),  2, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 1, 3, 2, 4)), createPlayerHand(List.of(3, 0, 4, 1, 0)), 1),
                Arguments.of(List.of(false, true, true, false, false, true),  3, TestUtils.Resource.WOOL, createCardsGiven(List.of(3, 2, 4, 3, 0)), createPlayerHand(List.of(4, 1, 0, 2, 1)), 1),
                Arguments.of(List.of(true, true, false, false, true, true),  2, TestUtils.Resource.ORE, createCardsGiven(List.of(2, 0, 0, 3, 1)), createPlayerHand(List.of(1, 4, 4, 2, 0)), 1),
                Arguments.of(List.of(true, false, true, true, false, true),  3, TestUtils.Resource.GRAIN, createCardsGiven(List.of(3, 1, 1, 4, 2)), createPlayerHand(List.of(2, 0, 0, 3, 1)), 1),
                Arguments.of(List.of(true, true, false, false, true, false),  4, TestUtils.Resource.LUMBER, createCardsGiven(List.of(4, 2, 0, 0, 3)), createPlayerHand(List.of(3, 1, 1, 4, 2)), 1),
                Arguments.of(List.of(true, false, true, true, false, true),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(0, 3, 1, 1, 4)), createPlayerHand(List.of(4, 2, 0, 0, 3)), 1),
                Arguments.of(List.of(true, true, false, true, true, false),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(1, 4, 2, 2, 0)), createPlayerHand(List.of(0, 3, 1, 1, 4)), 1),
                Arguments.of(List.of(true, false, true, false, false, true),  3, TestUtils.Resource.ORE, createCardsGiven(List.of(0, 0, 3, 3, 1)), createPlayerHand(List.of(1, 4, 2, 2, 0)), 1),
                Arguments.of(List.of(true, true, false, true, true, false),  4, TestUtils.Resource.GRAIN, createCardsGiven(List.of(1, 1, 4, 4, 2)), createPlayerHand(List.of(0, 0, 3, 3, 1)), 1),
                Arguments.of(List.of(true, true, true, false, false, true),  1, TestUtils.Resource.LUMBER, createCardsGiven(List.of(2, 2, 0, 0, 3)), createPlayerHand(List.of(1, 1, 4, 4, 2)), 1),
                Arguments.of(List.of(true, false, false, true, true, false),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(3, 3, 1, 1, 4)), createPlayerHand(List.of(2, 2, 0, 0, 3)), 1),
                Arguments.of(List.of(true, true, true, false, false, true),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(4, 4, 2, 0, 0)), createPlayerHand(List.of(3, 3, 1, 1, 4)), 1),
                Arguments.of(List.of(true, false, false, true, true, false),  3, TestUtils.Resource.ORE, createCardsGiven(List.of(0, 0, 3, 1, 1)), createPlayerHand(List.of(4, 4, 2, 0, 0)), 1),
                Arguments.of(List.of(true, true, true, false, true, true),  4, TestUtils.Resource.GRAIN, createCardsGiven(List.of(1, 1, 4, 2, 2)), createPlayerHand(List.of(0, 0, 3, 1, 1)), 1),
                Arguments.of(List.of(true, false, false, true, false, false),  1, TestUtils.Resource.LUMBER, createCardsGiven(List.of(2, 0, 0, 3, 3)), createPlayerHand(List.of(1, 1, 4, 2, 2)), 1),
                Arguments.of(List.of(true, true, true, false, true, true),  2, TestUtils.Resource.BRICK, createCardsGiven(List.of(3, 1, 1, 4, 4)), createPlayerHand(List.of(2, 0, 0, 3, 3)), 1),
                Arguments.of(List.of(true, false, true, true, false, false),  3, TestUtils.Resource.WOOL, createCardsGiven(List.of(4, 2, 2, 0, 0)), createPlayerHand(List.of(3, 1, 1, 4, 4)), 1),
                Arguments.of(List.of(true, true, false, false, true, true),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(0, 3, 3, 1, 1)), createPlayerHand(List.of(4, 2, 2, 0, 0)), 1),
                Arguments.of(List.of(true, false, true, true, false, false),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(1, 4, 4, 2, 0)), createPlayerHand(List.of(0, 3, 3, 1, 1)), 1),
                Arguments.of(List.of(false, false, false, true, true, true),  4, TestUtils.Resource.LUMBER, createCardsGiven(List.of(3, 4, 3, 2, 3)), createPlayerHand(List.of(2, 1, 2, 1, 0)), 1),
                Arguments.of(List.of(false, true, true, true, false, false),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(4, 0, 4, 3, 4)), createPlayerHand(List.of(3, 2, 3, 2, 1)), 1),
                Arguments.of(List.of(false, false, false, false, true, true),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(0, 1, 0, 4, 0)), createPlayerHand(List.of(4, 3, 4, 3, 2)), 1),
                Arguments.of(List.of(false, true, true, true, false, false),  2, TestUtils.Resource.ORE, createCardsGiven(List.of(1, 0, 1, 0, 1)), createPlayerHand(List.of(0, 4, 0, 4, 3)), 1),
                Arguments.of(List.of(false, false, false, false, true, true),  3, TestUtils.Resource.GRAIN, createCardsGiven(List.of(2, 1, 2, 1, 0)), createPlayerHand(List.of(1, 0, 1, 0, 4)), 1),
                Arguments.of(List.of(false, true, true, true, false, false),  4, TestUtils.Resource.LUMBER, createCardsGiven(List.of(3, 2, 3, 2, 1)), createPlayerHand(List.of(2, 1, 0, 1, 0)), 1),
                Arguments.of(List.of(false, true, false, false, true, true),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(4, 3, 4, 3, 2)), createPlayerHand(List.of(3, 2, 1, 2, 1)), 1),
                Arguments.of(List.of(false, false, true, true, true, false),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(0, 4, 0, 4, 3)), createPlayerHand(List.of(4, 3, 2, 3, 2)), 1),
                Arguments.of(List.of(false, true, false, false, false, true),  3, TestUtils.Resource.BRICK, createCardsGiven(List.of(1, 0, 1, 0, 4)), createPlayerHand(List.of(0, 4, 3, 4, 3)), 1),
                Arguments.of(List.of(false, false, true, true, true, false),  4, TestUtils.Resource.WOOL, createCardsGiven(List.of(2, 1, 0, 1, 0)), createPlayerHand(List.of(1, 0, 4, 0, 4)), 1),
                Arguments.of(List.of(false, true, false, false, false, true),  1, TestUtils.Resource.ORE, createCardsGiven(List.of(3, 2, 1, 2, 1)), createPlayerHand(List.of(0, 1, 0, 1, 0)), 1),
                Arguments.of(List.of(false, false, true, true, true, false),  2, TestUtils.Resource.GRAIN, createCardsGiven(List.of(4, 3, 2, 3, 2)), createPlayerHand(List.of(1, 2, 1, 0, 1)), 1),
                Arguments.of(List.of(false, true, true, false, false, true),  3, TestUtils.Resource.LUMBER, createCardsGiven(List.of(0, 4, 3, 4, 3)), createPlayerHand(List.of(2, 3, 2, 1, 2)), 1),
                Arguments.of(List.of(false, false, false, true, true, true),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(1, 0, 4, 0, 4)), createPlayerHand(List.of(3, 4, 3, 2, 3)), 1),
                Arguments.of(List.of(false, true, true, false, false, false),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(0, 1, 0, 1, 0)), createPlayerHand(List.of(4, 0, 4, 3, 4)), 1),
                Arguments.of(List.of(false, false, false, true, true, true),  2, TestUtils.Resource.ORE, createCardsGiven(List.of(1, 2, 1, 0, 1)), createPlayerHand(List.of(0, 1, 0, 4, 0)), 1),
                Arguments.of(List.of(false, true, true, false, false, false),  3, TestUtils.Resource.GRAIN, createCardsGiven(List.of(2, 3, 2, 1, 2)), createPlayerHand(List.of(1, 0, 1, 0, 1)), 1),
                Arguments.of(List.of(true, true, false, true, true, false),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(1, 1, 1, 3, 3)), createPlayerHand(List.of(3, 0, 0, 0, 0)), 1),
                Arguments.of(List.of(true, false, true, false, false, true),  2, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 2, 2, 4, 4)), createPlayerHand(List.of(4, 1, 1, 1, 1)), 1),
                Arguments.of(List.of(true, true, false, true, true, false),  3, TestUtils.Resource.WOOL, createCardsGiven(List.of(3, 3, 3, 0, 0)), createPlayerHand(List.of(0, 0, 2, 2, 2)), 1),
                Arguments.of(List.of(true, false, true, false, true, true),  4, TestUtils.Resource.ORE, createCardsGiven(List.of(4, 4, 4, 1, 1)), createPlayerHand(List.of(1, 1, 3, 3, 3)), 1),
                Arguments.of(List.of(true, true, false, true, false, false),  1, TestUtils.Resource.GRAIN, createCardsGiven(List.of(0, 0, 0, 0, 2)), createPlayerHand(List.of(2, 2, 4, 4, 4)), 2),
                Arguments.of(List.of(true, true, true, false, true, true),  2, TestUtils.Resource.LUMBER, createCardsGiven(List.of(1, 1, 1, 1, 3)), createPlayerHand(List.of(3, 3, 0, 0, 0)), 1),
                Arguments.of(List.of(true, false, false, true, false, false),  3, TestUtils.Resource.BRICK, createCardsGiven(List.of(0, 2, 2, 2, 4)), createPlayerHand(List.of(4, 4, 1, 1, 1)), 1),
                Arguments.of(List.of(true, true, true, false, true, true),  4, TestUtils.Resource.WOOL, createCardsGiven(List.of(1, 3, 3, 3, 0)), createPlayerHand(List.of(0, 0, 0, 2, 2)), 1),
                Arguments.of(List.of(true, false, false, true, false, true),  1, TestUtils.Resource.ORE, createCardsGiven(List.of(2, 4, 4, 4, 1)), createPlayerHand(List.of(1, 1, 1, 3, 3)), 1),
                Arguments.of(List.of(true, true, true, false, true, false),  2, TestUtils.Resource.GRAIN, createCardsGiven(List.of(3, 0, 0, 0, 0)), createPlayerHand(List.of(2, 2, 2, 4, 4)), 1),
                Arguments.of(List.of(true, false, true, true, false, true),  3, TestUtils.Resource.LUMBER, createCardsGiven(List.of(4, 1, 1, 1, 1)), createPlayerHand(List.of(3, 3, 3, 0, 0)), 1),
                Arguments.of(List.of(true, true, false, false, true, false),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(0, 0, 2, 2, 2)), createPlayerHand(List.of(4, 4, 4, 1, 1)), 1),
                Arguments.of(List.of(true, false, true, true, false, true),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(1, 1, 3, 3, 3)), createPlayerHand(List.of(0, 0, 0, 0, 2)), 1),
                Arguments.of(List.of(true, true, false, false, true, false),  1, TestUtils.Resource.ORE, createCardsGiven(List.of(2, 2, 4, 4, 4)), createPlayerHand(List.of(1, 1, 1, 1, 3)), 1),
                Arguments.of(List.of(true, false, true, true, false, true),  2, TestUtils.Resource.GRAIN, createCardsGiven(List.of(3, 3, 0, 0, 0)), createPlayerHand(List.of(0, 2, 2, 2, 4)), 1),
                Arguments.of(List.of(true, true, false, true, true, false),  3, TestUtils.Resource.LUMBER, createCardsGiven(List.of(4, 4, 1, 1, 1)), createPlayerHand(List.of(1, 3, 3, 3, 0)), 1),
                Arguments.of(List.of(true, false, true, false, false, true),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(0, 0, 0, 2, 2)), createPlayerHand(List.of(2, 4, 4, 4, 1)), 1),
                Arguments.of(List.of(false, false, false, false, false, true),  3, TestUtils.Resource.WOOL, createCardsGiven(List.of(2, 0, 1, 2, 3)), createPlayerHand(List.of(1, 2, 3, 4, 0)), 1),
                Arguments.of(List.of(false, true, true, true, true, false),  4, TestUtils.Resource.ORE, createCardsGiven(List.of(3, 1, 2, 3, 4)), createPlayerHand(List.of(0, 3, 4, 0, 1)), 1),
                Arguments.of(List.of(false, false, false, false, false, true),  1, TestUtils.Resource.GRAIN, createCardsGiven(List.of(4, 0, 3, 4, 0)), createPlayerHand(List.of(1, 4, 0, 1, 2)), 1),
                Arguments.of(List.of(false, true, true, true, true, true),  2, TestUtils.Resource.LUMBER, createCardsGiven(List.of(0, 1, 4, 0, 1)), createPlayerHand(List.of(2, 0, 1, 2, 3)), 1),
                Arguments.of(List.of(false, true, false, false, false, false),  3, TestUtils.Resource.BRICK, createCardsGiven(List.of(1, 2, 0, 1, 2)), createPlayerHand(List.of(3, 1, 2, 3, 4)), 1),
                Arguments.of(List.of(false, false, true, true, true, true),  4, TestUtils.Resource.WOOL, createCardsGiven(List.of(2, 3, 1, 2, 3)), createPlayerHand(List.of(4, 0, 3, 4, 0)), 1),
                Arguments.of(List.of(false, true, false, false, false, false),  1, TestUtils.Resource.ORE, createCardsGiven(List.of(3, 4, 0, 3, 4)), createPlayerHand(List.of(0, 1, 4, 0, 1)), 1),
                Arguments.of(List.of(false, false, true, true, true, true),  1, TestUtils.Resource.GRAIN, createCardsGiven(List.of(4, 0, 1, 4, 0)), createPlayerHand(List.of(1, 2, 0, 1, 2)), 1),
                Arguments.of(List.of(false, true, true, false, false, false),  2, TestUtils.Resource.LUMBER, createCardsGiven(List.of(0, 1, 2, 0, 1)), createPlayerHand(List.of(2, 3, 1, 2, 3)), 1),
                Arguments.of(List.of(false, false, false, true, true, true),  3, TestUtils.Resource.BRICK, createCardsGiven(List.of(1, 2, 3, 1, 2)), createPlayerHand(List.of(3, 4, 0, 3, 4)), 1),
                Arguments.of(List.of(false, true, true, false, false, false),  4, TestUtils.Resource.WOOL, createCardsGiven(List.of(2, 3, 4, 0, 3)), createPlayerHand(List.of(4, 0, 1, 4, 0)), 1),
                Arguments.of(List.of(false, false, false, true, true, true),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(3, 4, 0, 1, 4)), createPlayerHand(List.of(0, 1, 2, 0, 1)), 1),
                Arguments.of(List.of(false, true, true, true, false, false),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(4, 0, 1, 2, 0)), createPlayerHand(List.of(1, 2, 3, 1, 2)), 1),
                Arguments.of(List.of(false, false, false, false, true, true),  3, TestUtils.Resource.ORE, createCardsGiven(List.of(0, 1, 2, 3, 1)), createPlayerHand(List.of(2, 3, 4, 0, 3)), 1),
                Arguments.of(List.of(false, true, true, true, false, false),  4, TestUtils.Resource.GRAIN, createCardsGiven(List.of(1, 2, 3, 4, 0)), createPlayerHand(List.of(3, 4, 0, 1, 4)), 1),
                Arguments.of(List.of(false, false, false, false, true, true),  1, TestUtils.Resource.LUMBER, createCardsGiven(List.of(0, 3, 4, 0, 1)), createPlayerHand(List.of(4, 0, 1, 2, 0)), 1),
                Arguments.of(List.of(false, true, true, true, true, false),  2, TestUtils.Resource.BRICK, createCardsGiven(List.of(1, 4, 0, 1, 2)), createPlayerHand(List.of(0, 1, 2, 3, 1)), 1),
                Arguments.of(List.of(true, true, false, true, false, true),  1, TestUtils.Resource.GRAIN, createCardsGiven(List.of(0, 2, 4, 1, 0)), createPlayerHand(List.of(2, 4, 1, 3, 0)), 1),
                Arguments.of(List.of(true, false, true, false, true, false),  1, TestUtils.Resource.LUMBER, createCardsGiven(List.of(1, 3, 0, 2, 1)), createPlayerHand(List.of(3, 0, 2, 4, 1)), 1),
                Arguments.of(List.of(true, true, false, true, false, true),  2, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 4, 1, 3, 0)), createPlayerHand(List.of(4, 1, 3, 0, 2)), 1),
                Arguments.of(List.of(true, true, true, false, true, false),  3, TestUtils.Resource.WOOL, createCardsGiven(List.of(3, 0, 2, 4, 1)), createPlayerHand(List.of(0, 2, 4, 1, 3)), 1),
                Arguments.of(List.of(true, false, false, true, false, true),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(4, 1, 3, 0, 2)), createPlayerHand(List.of(1, 3, 0, 2, 4)), 1),
                Arguments.of(List.of(true, true, true, false, true, false),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(0, 2, 4, 1, 3)), createPlayerHand(List.of(0, 4, 1, 3, 0)), 1),
                Arguments.of(List.of(true, false, true, true, false, true),  2, TestUtils.Resource.ORE, createCardsGiven(List.of(1, 3, 0, 2, 4)), createPlayerHand(List.of(1, 0, 2, 4, 1)), 1),
                Arguments.of(List.of(true, true, false, false, true, false),  3, TestUtils.Resource.GRAIN, createCardsGiven(List.of(0, 4, 1, 3, 0)), createPlayerHand(List.of(2, 1, 3, 0, 2)), 1),
                Arguments.of(List.of(true, false, true, true, false, true),  4, TestUtils.Resource.LUMBER, createCardsGiven(List.of(1, 0, 2, 4, 1)), createPlayerHand(List.of(3, 0, 4, 1, 3)), 1),
                Arguments.of(List.of(true, true, false, true, true, false),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 1, 3, 0, 2)), createPlayerHand(List.of(4, 1, 0, 2, 4)), 1),
                Arguments.of(List.of(true, false, true, false, false, true),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(3, 0, 4, 1, 3)), createPlayerHand(List.of(0, 2, 1, 3, 0)), 1),
                Arguments.of(List.of(true, true, false, true, true, false),  3, TestUtils.Resource.ORE, createCardsGiven(List.of(4, 1, 0, 2, 4)), createPlayerHand(List.of(1, 3, 0, 4, 1)), 1),
                Arguments.of(List.of(true, false, true, false, true, true),  4, TestUtils.Resource.GRAIN, createCardsGiven(List.of(0, 2, 1, 3, 0)), createPlayerHand(List.of(2, 4, 1, 0, 2)), 1),
                Arguments.of(List.of(true, true, false, true, false, false),  1, TestUtils.Resource.LUMBER, createCardsGiven(List.of(1, 3, 0, 4, 1)), createPlayerHand(List.of(3, 0, 2, 1, 3)), 1),
                Arguments.of(List.of(true, false, true, false, true, true),  2, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 4, 1, 0, 2)), createPlayerHand(List.of(4, 1, 3, 0, 4)), 1),
                Arguments.of(List.of(true, true, false, true, false, true),  3, TestUtils.Resource.WOOL, createCardsGiven(List.of(3, 0, 2, 1, 3)), createPlayerHand(List.of(0, 2, 4, 1, 0)), 1),
                Arguments.of(List.of(true, false, true, false, true, false),  4, TestUtils.Resource.ORE, createCardsGiven(List.of(4, 1, 3, 0, 4)), createPlayerHand(List.of(1, 3, 0, 2, 1)), 1),
                Arguments.of(List.of(false, false, false, false, false, false),  2, TestUtils.Resource.GRAIN, createCardsGiven(List.of(1, 1, 4, 2, 0)), createPlayerHand(List.of(3, 1, 4, 2, 0)), 2),
                Arguments.of(List.of(false, true, true, true, true, true),  3, TestUtils.Resource.LUMBER, createCardsGiven(List.of(2, 0, 0, 3, 1)), createPlayerHand(List.of(4, 2, 0, 3, 1)), 2),
                Arguments.of(List.of(false, true, false, false, false, false),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(3, 1, 1, 4, 2)), createPlayerHand(List.of(0, 3, 1, 4, 2)), 1),
                Arguments.of(List.of(false, false, true, true, true, true),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(4, 2, 0, 0, 3)), createPlayerHand(List.of(1, 4, 2, 0, 3)), 1),
                Arguments.of(List.of(false, true, true, false, false, false),  2, TestUtils.Resource.ORE, createCardsGiven(List.of(0, 3, 1, 1, 4)), createPlayerHand(List.of(2, 0, 3, 1, 4)), 1),
                Arguments.of(List.of(false, false, false, true, true, true),  3, TestUtils.Resource.GRAIN, createCardsGiven(List.of(1, 4, 2, 0, 0)), createPlayerHand(List.of(3, 1, 4, 2, 0)), 1),
                Arguments.of(List.of(false, true, true, true, false, false),  4, TestUtils.Resource.LUMBER, createCardsGiven(List.of(2, 0, 3, 1, 1)), createPlayerHand(List.of(4, 2, 0, 3, 1)), 1),
                Arguments.of(List.of(false, false, false, false, true, true),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(3, 1, 4, 2, 0)), createPlayerHand(List.of(0, 3, 1, 4, 2)), 1),
                Arguments.of(List.of(false, true, true, true, true, false),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(4, 2, 0, 3, 1)), createPlayerHand(List.of(1, 4, 2, 0, 3)), 1),
                Arguments.of(List.of(false, false, false, false, false, true),  3, TestUtils.Resource.ORE, createCardsGiven(List.of(0, 3, 1, 4, 2)), createPlayerHand(List.of(0, 0, 3, 1, 4)), 1),
                Arguments.of(List.of(false, true, true, true, true, true),  4, TestUtils.Resource.GRAIN, createCardsGiven(List.of(1, 4, 2, 0, 3)), createPlayerHand(List.of(1, 1, 4, 2, 0)), 1),
                Arguments.of(List.of(false, false, false, false, false, false),  1, TestUtils.Resource.LUMBER, createCardsGiven(List.of(2, 0, 3, 1, 4)), createPlayerHand(List.of(2, 0, 0, 3, 1)), 1),
                Arguments.of(List.of(false, true, true, true, true, true),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(3, 1, 4, 2, 0)), createPlayerHand(List.of(3, 1, 1, 4, 2)), 1),
                Arguments.of(List.of(false, false, false, false, false, false),  2, TestUtils.Resource.WOOL, createCardsGiven(List.of(4, 2, 0, 3, 1)), createPlayerHand(List.of(4, 2, 0, 0, 3)), 1),
                Arguments.of(List.of(false, true, true, true, true, true),  3, TestUtils.Resource.BRICK, createCardsGiven(List.of(0, 3, 1, 4, 2)), createPlayerHand(List.of(0, 3, 1, 1, 4)), 1),
                Arguments.of(List.of(false, false, false, false, false, false),  4, TestUtils.Resource.WOOL, createCardsGiven(List.of(1, 4, 2, 0, 3)), createPlayerHand(List.of(1, 4, 2, 0, 0)), 1),
                Arguments.of(List.of(false, true, true, true, true, true),  1, TestUtils.Resource.ORE, createCardsGiven(List.of(0, 0, 3, 1, 4)), createPlayerHand(List.of(2, 0, 3, 1, 1)), 1),
                Arguments.of(List.of(true, true, false, true, false, true),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(4, 3, 2, 1, 0)), createPlayerHand(List.of(4, 3, 2, 1, 0)), 2),
                Arguments.of(List.of(true, true, true, false, true, false),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(0, 4, 3, 2, 1)), createPlayerHand(List.of(0, 4, 3, 2, 1)), 2),
                Arguments.of(List.of(true, false, true, true, false, true),  2, TestUtils.Resource.ORE, createCardsGiven(List.of(1, 0, 4, 3, 2)), createPlayerHand(List.of(1, 0, 4, 3, 2)), 2),
                Arguments.of(List.of(true, true, false, true, true, false),  3, TestUtils.Resource.GRAIN, createCardsGiven(List.of(2, 1, 0, 4, 3)), createPlayerHand(List.of(2, 1, 0, 4, 3)), 2),
                Arguments.of(List.of(true, false, true, false, true, true),  4, TestUtils.Resource.LUMBER, createCardsGiven(List.of(3, 2, 1, 0, 4)), createPlayerHand(List.of(3, 2, 1, 0, 4)), 2),
                Arguments.of(List.of(true, true, false, true, false, true),  1, TestUtils.Resource.BRICK, createCardsGiven(List.of(4, 3, 2, 1, 0)), createPlayerHand(List.of(4, 3, 2, 1, 0)), 2),
                Arguments.of(List.of(true, false, true, false, true, false),  1, TestUtils.Resource.WOOL, createCardsGiven(List.of(0, 4, 3, 2, 1)), createPlayerHand(List.of(0, 4, 3, 2, 1)), 2),
                Arguments.of(List.of(true, true, false, true, false, true),  2, TestUtils.Resource.BRICK, createCardsGiven(List.of(1, 0, 4, 3, 2)), createPlayerHand(List.of(1, 0, 4, 3, 2)), 2),
                Arguments.of(List.of(true, false, true, false, true, false),  3, TestUtils.Resource.WOOL, createCardsGiven(List.of(0, 1, 0, 4, 3)), createPlayerHand(List.of(2, 1, 0, 4, 3)), 2),
                Arguments.of(List.of(true, true, false, true, false, true),  4, TestUtils.Resource.ORE, createCardsGiven(List.of(1, 0, 1, 0, 4)), createPlayerHand(List.of(3, 2, 1, 0, 4)), 2),
                Arguments.of(List.of(true, false, true, false, true, false),  1, TestUtils.Resource.GRAIN, createCardsGiven(List.of(2, 1, 0, 1, 0)), createPlayerHand(List.of(4, 3, 2, 1, 0)), 2),
                Arguments.of(List.of(true, true, false, true, false, true),  2, TestUtils.Resource.LUMBER, createCardsGiven(List.of(3, 2, 1, 0, 1)), createPlayerHand(List.of(0, 4, 3, 2, 1)), 1),
                Arguments.of(List.of(true, false, true, false, true, false),  3, TestUtils.Resource.BRICK, createCardsGiven(List.of(4, 3, 2, 1, 0)), createPlayerHand(List.of(1, 0, 4, 3, 2)), 1),
                Arguments.of(List.of(true, true, false, true, false, true),  4, TestUtils.Resource.WOOL, createCardsGiven(List.of(0, 4, 3, 2, 1)), createPlayerHand(List.of(0, 1, 0, 4, 3)), 1),
                Arguments.of(List.of(true, false, true, false, true, false),  1, TestUtils.Resource.ORE, createCardsGiven(List.of(1, 0, 4, 3, 2)), createPlayerHand(List.of(1, 0, 1, 0, 4)), 1),
                Arguments.of(List.of(true, true, false, true, false, true),  2, TestUtils.Resource.GRAIN, createCardsGiven(List.of(2, 1, 0, 4, 3)), createPlayerHand(List.of(2, 1, 0, 1, 0)), 1),
                Arguments.of(List.of(true, false, true, false, true, false),  3, TestUtils.Resource.LUMBER, createCardsGiven(List.of(3, 2, 1, 0, 4)), createPlayerHand(List.of(3, 2, 1, 0, 1)), 1),
                Arguments.of(List.of(true, true, true, true, true, true),  4, TestUtils.Resource.BRICK, createCardsGiven(List.of(2, 0, 0, 0, 0)), createPlayerHand(List.of(2, 0, 0, 0, 0)), 0)
        );
    }



}
