package edu.psgv.sweng881.game;


import edu.psgv.sweng881.gui.GameWindow;
import edu.psgv.sweng881.utils.TestUtils;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.awt.*;
import java.util.ArrayList;


public class GraphTest extends AssertJSwingJUnitTestCase{

    //(574, 182) - settlement location

    //1256, 159 - place button approx location

    private FrameFixture window;

    @Override
    protected void onSetUp() throws Exception {
        ArrayList<Player> players = TestUtils.createGenericPlayerList();
        ReflectionTestUtils.setField(GameRunner.class, "players", players);
        GameWindow frame = GuiActionRunner.execute(() -> new GameWindow(players));
        window = new FrameFixture(robot(), frame);
        window.show();

    }


    @Test
    public void shouldTestGraph() throws InterruptedException, AWTException {
        // click the start button
        window.close();

        // click a spot on the screen
    }

    @Override
    protected void onTearDown() {
        window.cleanUp();
    }
}
