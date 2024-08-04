package exploratory;

import edu.psgv.sweng881.game.Player;
import edu.psgv.sweng881.board.DevCard;
import edu.psgv.sweng881.board.Road;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerResourceManagementTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("TestPlayer", Color.BLUE);
    }

    @Test
    void testAddExcessiveResources() {
        Player player = new Player("Test Player", Color.blue);
        ArrayList<String> excessiveResources = new ArrayList<>(Collections.nCopies(1000, "BRICK"));
        player.addResources(excessiveResources);
        assertTrue(player.getNumberResourcesType("BRICK") >= 1000);
    }

    @Test
    public void testRemoveMoreResourcesThanAvailable() {
        // Set initial resources
        player.setNumberResourcesType("BRICK", 1);

        // Create a list of resources to remove
        ArrayList<String> resourcesToRemove = new ArrayList<>();
        resourcesToRemove.add("BRICK");
        resourcesToRemove.add("BRICK"); // Attempt to remove more than available

        // Remove resources
        player.removeResources(resourcesToRemove);

        // Check if the method correctly handles the case
        assertEquals(0, player.getNumberResourcesType("BRICK"));
    }

    @Test
    void testAddAndRemoveDevelopmentCard() {
        Player player = new Player("Test Player", Color.blue);
        DevCard victoryPointCard = new DevCard("Victory Point");
        player.addDevCard(victoryPointCard);
        assertTrue(player.getDevCardsType("Victory Point") > 0);
        player.removeCard("Victory Point");
        assertEquals(0, player.getDevCardsType("Victory Point"));
    }

    @Test
    void testAddRoad() {
        Player player = new Player("Test Player", Color.blue);
        // Use correct parameters for the Road constructor
        Road road = new Road(1, 2, 0); // Example parameters: startNode, endNode, roadType
        player.addRoad(road);
        assertEquals(1, player.getRoads().size());
    }


    @Test
    void testInvalidResourceHandling() {
        Player player = new Player("Test Player", Color.blue);
        ArrayList<String> invalidResources = new ArrayList<>();
        invalidResources.add("INVALID_RESOURCE");
        assertThrows(IllegalArgumentException.class, () -> {
            player.addResources(invalidResources);
        });
    }
}
