package edu.psgv.sweng881.utils;

import edu.psgv.sweng881.game.Player;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static ArrayList<Player> createGenericPlayerList() {

        Player a = new Player("a", Color.blue);

        Player b = new Player("b", Color.yellow);

        Player c = new Player("c", Color.green);

        Player d = new Player("d", Color.orange);

        return new ArrayList<>(List.of(a, b, c, d));
    }

}
