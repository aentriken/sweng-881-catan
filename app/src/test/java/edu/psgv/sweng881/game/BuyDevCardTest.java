package edu.psgv.sweng881.game;

import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class BuyDevCardTest {

    private static final ArrayList<Player> players = TestUtils.createGenericPlayerList();

    @Mock

    private Game game;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        game = new Game(players);
    }

    /**
     * Test case combinations for buying a development card
     * @return Stream of Arguments
     */
    static Stream<Arguments> buyDevCardCombos() {
        return Stream.of(
                Arguments.of(-1, -1, -1, true, 1),  // Negative/Insufficient resources and empty deck
                Arguments.of(0, 0, 0, false, 1),    // Insufficient resources
                Arguments.of(2, 3, 2, false, 0),     // Sufficient resources
                Arguments.of(2, 3, 2, true, 2)     // Sufficient resources but empty deck
        );
    }

    /**
     * Parameterized test for buyDevCard method
     * @param ore - Number of ORE resources
     * @param wool - Number of WOOL resources
     * @param grain - Number of GRAIN resources
     * @param deckEmpty - Whether the deck is empty
     * @param expectedResult - Expected result of the buyDevCard method
     */
    @ParameterizedTest(name = "TC{index} -> ORE: {0}, WOOL: {1}, GRAIN: {2}, DeckEmpty: {3} - expecting {4}")
    @MethodSource("buyDevCardCombos")
    void shouldBuyDevCard(int ore, int wool, int grain, boolean deckEmpty, int expectedResult) {
        Player player = players.get(0);
        player.setNumberResourcesType("ORE", ore);
        player.setNumberResourcesType("WOOL", wool);
        player.setNumberResourcesType("GRAIN", grain);

        if (deckEmpty) {
            game.setEmptyDeck();  // Set the deck to be empty for this test case
        }

        int result = game.buyDevCard(player);
        Assertions.assertEquals(expectedResult, result);
    }
}
