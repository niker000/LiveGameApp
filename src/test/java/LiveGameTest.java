import models.Generation;
import models.LiveGame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LiveGameTest {

    @Test
    public void play() {
        char[][] cells = {{'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'X', 'O', 'O'},
                {'O', 'O', 'X', 'O', 'O'},
                {'O', 'O', 'X', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O'}};
        char[][] expected = {{'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O'},
                {'O', 'X', 'X', 'X', 'O'},
                {'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O'}};
        LiveGame liveGame = new LiveGame(new Generation(cells),1);
        liveGame.play();
        char[][] actual = liveGame.getGeneration().getField();
        assertEquals(expected,actual);
    }
}