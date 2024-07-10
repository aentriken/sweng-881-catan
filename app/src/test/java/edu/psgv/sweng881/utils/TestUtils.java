package edu.psgv.sweng881.utils;

import edu.psgv.sweng881.game.Player;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static ArrayList<Player> createGenericPlayerList() {

        Player a = Mockito.mock(Player.class);
        Mockito.when(a.getName()).thenReturn("a");

        Player b = Mockito.mock(Player.class);
        Mockito.when(b.getName()).thenReturn("b");

        Player c = Mockito.mock(Player.class);
        Mockito.when(c.getName()).thenReturn("c");

        Player d = Mockito.mock(Player.class);
        Mockito.when(d.getName()).thenReturn("d");

        return new ArrayList<>(List.of(a, b, c, d));
    }

}
