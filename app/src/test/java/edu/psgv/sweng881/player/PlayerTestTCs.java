package edu.psgv.sweng881.player;

import edu.psgv.sweng881.utils.TestUtils;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class PlayerTestTCs {

    static Stream<Arguments> addResourcesTestCases() {
        return Stream.of(
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(1, 1, 0, 1, 1)), TestUtils.createPlayerHand(List.of(2, 1, 1, 2, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 1, 0, 1)), TestUtils.createPlayerHand(List.of(1, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(2, 1, 2, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 0, 1, 1)), TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 1)), TestUtils.createPlayerHand(List.of(1, 2, 1, 1, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(0, 2, 2, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(0, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(0, 0, 0, 2, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(1, 1, 0, 0, 0)), TestUtils.createPlayerHand(List.of(1, 2, 1, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(1, 2, 2, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 0, 1)), TestUtils.createPlayerHand(List.of(1, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(2, 0, 0, 1, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(1, 1, 2, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 0, 0, 1)), TestUtils.createPlayerHand(List.of(1, 0, 0, 0, 1)), TestUtils.createPlayerHand(List.of(2, 1, 0, 0, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(0, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 2, 2, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 0, 0, 0)), TestUtils.createPlayerHand(List.of(1, 1, 0, 0, 1)), TestUtils.createPlayerHand(List.of(2, 2, 0, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(0, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 1, 2, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 0)), TestUtils.createPlayerHand(List.of(1, 0, 0, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 1, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 1, 1)), TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 1)), TestUtils.createPlayerHand(List.of(0, 2, 1, 1, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 0, 1, 0)), TestUtils.createPlayerHand(List.of(0, 1, 0, 0, 1)), TestUtils.createPlayerHand(List.of(0, 1, 0, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 1, 0, 1)), TestUtils.createPlayerHand(List.of(1, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(1, 1, 2, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 0, 1)), TestUtils.createPlayerHand(List.of(0, 0, 1, 0, 0)), TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(1, 1, 0, 1, 1)), TestUtils.createPlayerHand(List.of(1, 1, 1, 2, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 1, 1, 1)), TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 0)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 0, 0)), TestUtils.createPlayerHand(List.of(0, 1, 1, 1, 1)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 1, 1, 1)), TestUtils.createPlayerHand(List.of(1, 0, 0, 0, 0)), TestUtils.createPlayerHand(List.of(2, 0, 1, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 0, 0, 0)), TestUtils.createPlayerHand(List.of(0, 0, 1, 1, 1)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(1, 1, 0, 0, 0)), TestUtils.createPlayerHand(List.of(2, 1, 0, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(0, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 0, 1)), TestUtils.createPlayerHand(List.of(1, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(2, 1, 1, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 1, 1, 0)), TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 1)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 0, 0)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 0)), TestUtils.createPlayerHand(List.of(2, 1, 1, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 1)), TestUtils.createPlayerHand(List.of(0, 1, 0, 1, 0)), TestUtils.createPlayerHand(List.of(0, 1, 1, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 0, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 1, 0, 1)), TestUtils.createPlayerHand(List.of(1, 0, 1, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 0, 1)), TestUtils.createPlayerHand(List.of(0, 1, 0, 1, 0)), TestUtils.createPlayerHand(List.of(0, 2, 0, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 0)), TestUtils.createPlayerHand(List.of(1, 0, 1, 0, 1)), TestUtils.createPlayerHand(List.of(1, 0, 2, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 1, 0)), TestUtils.createPlayerHand(List.of(0, 1, 0, 1, 0)), TestUtils.createPlayerHand(List.of(0, 2, 0, 2, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 1)), TestUtils.createPlayerHand(List.of(0, 0, 1, 0, 1)), TestUtils.createPlayerHand(List.of(0, 0, 2, 0, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 0, 1, 0)), TestUtils.createPlayerHand(List.of(1, 1, 0, 2, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 1)), TestUtils.createPlayerHand(List.of(0, 1, 0, 0, 1)), TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 1, 0, 0)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 0)))
        );
    }

    static Stream<Arguments> giveResourceTypeTestCases() {
        return Stream.of(
                Arguments.of(TestUtils.Resource.BRICK, 1),
                Arguments.of(TestUtils.Resource.WOOL, 1),
                Arguments.of(TestUtils.Resource.ORE, 1),
                Arguments.of(TestUtils.Resource.GRAIN, 1),
                Arguments.of(TestUtils.Resource.LUMBER, 1),
                Arguments.of("", 0)
        );
    }

    static Stream<Arguments> getDevCardTypeTestCases() {
        return Stream.of(
                Arguments.of(TestUtils.createDevCardHand(List.of(0, 0, 0, 0, 0)), "Knight", 0),
                Arguments.of(TestUtils.createDevCardHand(List.of(0, 1, 1, 1, 1)), "Road building", 1),
                Arguments.of(TestUtils.createDevCardHand(List.of(0, 2, 2, 2, 2)), "Year of plenty", 2),
                Arguments.of(TestUtils.createDevCardHand(List.of(0, 0, 0, 0, 0)), "Monopoly", 0),
                Arguments.of(TestUtils.createDevCardHand(List.of(0, 1, 1, 1, 1)), "Victory Point", 1),
                Arguments.of(TestUtils.createDevCardHand(List.of(0, 2, 2, 2, 2)), "Progress", 6),
                Arguments.of(TestUtils.createDevCardHand(List.of(1, 1, 2, 0, 1)), "Progress", 3),
                Arguments.of(TestUtils.createDevCardHand(List.of(1, 2, 0, 1, 2)), "Knight", 1),
                Arguments.of(TestUtils.createDevCardHand(List.of(1, 0, 1, 2, 0)), "Road building", 0),
                Arguments.of(TestUtils.createDevCardHand(List.of(1, 1, 2, 0, 1)), "Year of plenty", 2),
                Arguments.of(TestUtils.createDevCardHand(List.of(1, 2, 0, 1, 2)), "Monopoly", 1),
                Arguments.of(TestUtils.createDevCardHand(List.of(1, 0, 1, 2, 0)), "Victory Point", 0),
                Arguments.of(TestUtils.createDevCardHand(List.of(2, 2, 1, 0, 2)), "Victory Point", 2),
                Arguments.of(TestUtils.createDevCardHand(List.of(2, 0, 2, 1, 0)), "Progress", 3),
                Arguments.of(TestUtils.createDevCardHand(List.of(2, 1, 0, 2, 1)), "Knight", 2),
                Arguments.of(TestUtils.createDevCardHand(List.of(2, 2, 1, 0, 2)), "Road building", 2),
                Arguments.of(TestUtils.createDevCardHand(List.of(2, 0, 2, 1, 0)), "Year of plenty", 2),
                Arguments.of(TestUtils.createDevCardHand(List.of(2, 1, 0, 2, 1)), "Monopoly", 2)
        );
    }

    static Stream<Arguments> addDevCardTestCases() {
        return Stream.of(
                Arguments.of("", 8, 8),
                Arguments.of("", 9, 9),
                Arguments.of("Knight", 9, 9),
                Arguments.of("Knight", 8, 8),
                Arguments.of("Progress", 8, 8),
                Arguments.of("Progress", 9, 9),
                Arguments.of("Victory Point", 9, 10),
                Arguments.of("Victory Point", 8, 9)
        );
    }
    static Stream<Arguments> numResourceOfTypeTestCases() {
        return Stream.of(
                Arguments.of(TestUtils.Resource.BRICK, TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 0)), 0),
                Arguments.of(TestUtils.Resource.BRICK, TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1)), 1),
                Arguments.of(TestUtils.Resource.WOOL, TestUtils.createPlayerHand(List.of(1, 0, 0, 1, 0)), 0),
                Arguments.of(TestUtils.Resource.WOOL, TestUtils.createPlayerHand(List.of(0, 0, 1, 0, 1)), 0),
                Arguments.of(TestUtils.Resource.WOOL, TestUtils.createPlayerHand(List.of(0, 1, 0, 1, 0)), 1),
                Arguments.of(TestUtils.Resource.GRAIN, TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 0)), 0),
                Arguments.of(TestUtils.Resource.GRAIN, TestUtils.createPlayerHand(List.of(0, 0, 0, 1, 1)), 1),
                Arguments.of(TestUtils.Resource.GRAIN, TestUtils.createPlayerHand(List.of(1, 1, 0, 0, 0)), 0),
                Arguments.of(TestUtils.Resource.ORE, TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 0)), 1),
                Arguments.of(TestUtils.Resource.ORE, TestUtils.createPlayerHand(List.of(1, 0, 0, 0, 1)), 0),
                Arguments.of(TestUtils.Resource.ORE, TestUtils.createPlayerHand(List.of(0, 0, 1, 1, 0)), 1),
                Arguments.of(TestUtils.Resource.LUMBER, TestUtils.createPlayerHand(List.of(0, 1, 0, 1, 0)), 0),
                Arguments.of(TestUtils.Resource.LUMBER, TestUtils.createPlayerHand(List.of(0, 0, 1, 0, 1)), 1),
                Arguments.of(TestUtils.Resource.LUMBER, TestUtils.createPlayerHand(List.of(1, 0, 0, 1, 0)), 0),
                Arguments.of(TestUtils.Resource.LUMBER, TestUtils.createPlayerHand(List.of(0, 1, 0, 0, 1)), 1),
                Arguments.of(TestUtils.Resource.LUMBER, TestUtils.createPlayerHand(List.of(1, 0, 1, 0, 0)), 0)
        );
    }
    static Stream<Arguments> removeResourcesTestCases() {
        return Stream.of(
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 1, 0, 1)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1)), TestUtils.createPlayerHand(List.of(0, 0, 0, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 1, 0, 0)), TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 0)), TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 0, 1, 1)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1)), TestUtils.createPlayerHand(List.of(0, 0, 1, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 1, 0, 0)), TestUtils.createPlayerHand(List.of(2, 2, 2, 2, 2)), TestUtils.createPlayerHand(List.of(1, 2, 1, 2, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(2, 0, 1, 2, 0)), TestUtils.createPlayerHand(List.of(2, 0, 1, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 0, 0)), TestUtils.createPlayerHand(List.of(0, 1, 2, 0, 1)), TestUtils.createPlayerHand(List.of(0, 0, 2, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(1, 2, 0, 1, 2)), TestUtils.createPlayerHand(List.of(1, 2, 0, 0, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 0, 0, 1)), TestUtils.createPlayerHand(List.of(1, 2, 0, 1, 2)), TestUtils.createPlayerHand(List.of(1, 2, 0, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(2, 0, 1, 2, 0)), TestUtils.createPlayerHand(List.of(2, 0, 0, 2, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(0, 1, 2, 0, 1)), TestUtils.createPlayerHand(List.of(0, 1, 2, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(1, 2, 0, 1, 2)), TestUtils.createPlayerHand(List.of(1, 1, 0, 1, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(2, 1, 0, 2, 1)), TestUtils.createPlayerHand(List.of(1, 1, 0, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(0, 2, 1, 0, 2)), TestUtils.createPlayerHand(List.of(0, 1, 0, 0, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 0, 1)), TestUtils.createPlayerHand(List.of(1, 0, 2, 1, 0)), TestUtils.createPlayerHand(List.of(0, 0, 2, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(2, 1, 0, 2, 1)), TestUtils.createPlayerHand(List.of(1, 1, 0, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 0, 0, 1)), TestUtils.createPlayerHand(List.of(0, 2, 1, 0, 2)), TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 2, 1, 0)), TestUtils.createPlayerHand(List.of(0, 0, 1, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 0, 0, 0)), TestUtils.createPlayerHand(List.of(2, 1, 0, 2, 1)), TestUtils.createPlayerHand(List.of(1, 0, 0, 2, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(0, 2, 1, 0, 2)), TestUtils.createPlayerHand(List.of(0, 2, 1, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 0)), TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 0)), TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 1, 1)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1)), TestUtils.createPlayerHand(List.of(1, 0, 1, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 0)), TestUtils.createPlayerHand(List.of(2, 2, 2, 2, 2)), TestUtils.createPlayerHand(List.of(2, 2, 1, 2, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 0, 1, 0)), TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 0)), TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 1, 0, 1)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1)), TestUtils.createPlayerHand(List.of(1, 0, 0, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 0, 1, 0)), TestUtils.createPlayerHand(List.of(2, 2, 2, 2, 2)), TestUtils.createPlayerHand(List.of(2, 2, 2, 1, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 0, 1)), TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 0)), TestUtils.createPlayerHand(List.of(0, 0, 0, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 1, 0)), TestUtils.createPlayerHand(List.of(1, 1, 1, 1, 1)), TestUtils.createPlayerHand(List.of(1, 1, 0, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 0, 1)), TestUtils.createPlayerHand(List.of(2, 2, 2, 2, 2)), TestUtils.createPlayerHand(List.of(2, 1, 2, 2, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 1, 1, 1)), TestUtils.createPlayerHand(List.of(2, 0, 1, 2, 0)), TestUtils.createPlayerHand(List.of(1, 0, 0, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 0, 0)), TestUtils.createPlayerHand(List.of(0, 1, 2, 0, 1)), TestUtils.createPlayerHand(List.of(0, 1, 2, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 1, 1, 1)), TestUtils.createPlayerHand(List.of(1, 2, 0, 1, 2)), TestUtils.createPlayerHand(List.of(0, 2, 0, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 0, 0, 0)), TestUtils.createPlayerHand(List.of(2, 0, 1, 2, 0)), TestUtils.createPlayerHand(List.of(1, 0, 1, 2, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 1, 1)), TestUtils.createPlayerHand(List.of(0, 1, 2, 0, 1)), TestUtils.createPlayerHand(List.of(0, 1, 2, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 1, 0, 0)), TestUtils.createPlayerHand(List.of(1, 2, 0, 1, 2)), TestUtils.createPlayerHand(List.of(0, 1, 0, 1, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 0, 1)), TestUtils.createPlayerHand(List.of(2, 0, 1, 2, 0)), TestUtils.createPlayerHand(List.of(1, 0, 1, 2, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 1, 1, 1, 0)), TestUtils.createPlayerHand(List.of(0, 1, 2, 0, 1)), TestUtils.createPlayerHand(List.of(0, 0, 1, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(1, 0, 0, 0, 0)), TestUtils.createPlayerHand(List.of(1, 2, 0, 1, 2)), TestUtils.createPlayerHand(List.of(0, 2, 0, 1, 2))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 1)), TestUtils.createPlayerHand(List.of(1, 0, 2, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 1, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 0, 1, 0)), TestUtils.createPlayerHand(List.of(2, 1, 0, 2, 1)), TestUtils.createPlayerHand(List.of(2, 1, 0, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 0, 1)), TestUtils.createPlayerHand(List.of(0, 2, 1, 0, 2)), TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 0)), TestUtils.createPlayerHand(List.of(1, 0, 2, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 1, 1, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 1, 0)), TestUtils.createPlayerHand(List.of(2, 1, 0, 2, 1)), TestUtils.createPlayerHand(List.of(2, 0, 0, 1, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 1)), TestUtils.createPlayerHand(List.of(0, 2, 1, 0, 2)), TestUtils.createPlayerHand(List.of(0, 2, 0, 0, 1))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 2, 1, 0)), TestUtils.createPlayerHand(List.of(1, 0, 2, 0, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 0, 1, 0, 1)), TestUtils.createPlayerHand(List.of(2, 1, 0, 2, 1)), TestUtils.createPlayerHand(List.of(2, 1, 0, 2, 0))),
                Arguments.of(TestUtils.createCardsGiven(List.of(0, 1, 0, 1, 0)), TestUtils.createPlayerHand(List.of(0, 2, 1, 0, 2)), TestUtils.createPlayerHand(List.of(0, 1, 1, 0, 2)))
        );
    }

    static Stream<Arguments> hasCardTestCases() {
        return Stream.of(
                Arguments.of(List.of(0, 0, 0, 0, 0), "Knight", false),
                Arguments.of(List.of(0, 1, 1, 1, 1), "Road building", true),
                Arguments.of(List.of(0, 0, 0, 0, 0), "Year of plenty", false),
                Arguments.of(List.of(0, 1, 1, 1, 1), "Monopoly", true),
                Arguments.of(List.of(0, 0, 0, 0, 0), "Progress", false),
                Arguments.of(List.of(0, 1, 1, 1, 1), "Victory Point", true),
                Arguments.of(List.of(1, 1, 0, 1, 0), "Victory Point", false),
                Arguments.of(List.of(1, 0, 1, 0, 1), "Knight", true),
                Arguments.of(List.of(1, 1, 0, 1, 0), "Road building", true),
                Arguments.of(List.of(1, 0, 1, 0, 1), "Year of plenty", true),
                Arguments.of(List.of(1, 1, 0, 1, 0), "Monopoly", true),
                Arguments.of(List.of(1, 0, 1, 0, 1), "Progress", true)
        );
    }

    static Stream<Arguments> removeCardTestCases() {
        return Stream.of(
                Arguments.of(List.of(0, 0, 0, 0, 0), "Knight", List.of(0, 0, 0, 0, 0)),
                Arguments.of(List.of(0, 1, 1, 1, 1), "Road building", List.of(0, 0, 1, 1, 1)),
                Arguments.of(List.of(0, 2, 2, 2, 2), "Year of plenty", List.of(0, 2, 1, 2, 2)),
                Arguments.of(List.of(0, 0, 0, 0, 0), "Monopoly", List.of(0, 0, 0, 0, 0)),
                Arguments.of(List.of(0, 1, 1, 1, 1), "Progress", List.of(0, 1, 1, 1, 1)),
                Arguments.of(List.of(0, 2, 2, 2, 2), "Victory Point", List.of(0, 2, 2, 2, 1)),
                Arguments.of(List.of(1, 1, 2, 0, 1), "Victory Point", List.of(1, 1, 2, 0, 0)),
                Arguments.of(List.of(1, 2, 0, 1, 2), "Knight", List.of(0, 2, 0, 1, 2)),
                Arguments.of(List.of(1, 0, 1, 2, 0), "Road building", List.of(1, 0, 1, 2, 0)),
                Arguments.of(List.of(1, 1, 2, 0, 1), "Year of plenty", List.of(1, 1, 1, 0, 1)),
                Arguments.of(List.of(1, 2, 0, 1, 2), "Monopoly", List.of(1, 2, 0, 0, 2)),
                Arguments.of(List.of(1, 0, 1, 2, 0), "Progress", List.of(1, 0, 1, 2, 0)),
                Arguments.of(List.of(2, 2, 1, 0, 2), "Progress", List.of(2, 2, 1, 0, 2)),
                Arguments.of(List.of(2, 0, 2, 1, 0), "Victory Point", List.of(2, 0, 2, 1, 0)),
                Arguments.of(List.of(2, 1, 0, 2, 1), "Knight", List.of(1, 1, 0, 2, 1)),
                Arguments.of(List.of(2, 2, 1, 0, 2), "Road building", List.of(2, 1, 1, 0, 2)),
                Arguments.of(List.of(2, 0, 2, 1, 0), "Year of plenty", List.of(2, 0, 1, 1, 0)),
                Arguments.of(List.of(2, 1, 0, 2, 1), "Monopoly", List.of(2, 1, 0, 1, 1))
        );
    }

    static Stream<Arguments> setHasLargestArmyTestCases() {
        return Stream.of(
                Arguments.of(true, true, true, 0),
                Arguments.of(true, false, false, -1),
                Arguments.of(false, false, false, 0),
                Arguments.of(false, true, true, 1)
        );
    }


}
