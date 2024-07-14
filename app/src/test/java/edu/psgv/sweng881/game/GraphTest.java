package edu.psgv.sweng881.game;


import edu.psgv.sweng881.gui.GameWindow;
import edu.psgv.sweng881.utils.TestUtils;
import org.assertj.swing.core.MouseClickInfo;
import org.assertj.swing.core.NameMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JInternalFrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class GraphTest extends AssertJSwingJUnitTestCase{

    //(574, 182) - settlement location

    //1256, 159 - place button approx location

    private FrameFixture window;
    private GameWindow frame;

    @Override
    protected void onSetUp() throws Exception {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        frame = GuiActionRunner.execute(() -> new GameWindow(players));
        window = new FrameFixture(robot(), frame);
        window.show();
        //window.maximize();
    }


    /**
     * click place
     * click location for settlement
     * click location for road
     *
     * click place
     *
     *
     */
    @Test
    public void graphTestCase1() {
        // click the place button
        window.button("place").click();

        // place settlement
        Point p = new Point(574, 182);
        Component board = window.target();
        robot().click(board, p);

        // assert state
        int state = (int) ReflectionTestUtils.getField(frame.getBoard(), "state");

        //window.panel().
        window.close();


        // click a spot on the screen
    }

    @Override
    protected void onTearDown() {
        window.cleanUp();
    }
}
