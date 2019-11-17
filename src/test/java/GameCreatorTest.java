
import interfaces.IWrite;
import models.Generation;
import models.LiveGame;
import org.junit.Test;

import org.mockito.Mockito;
import utils.ConditionParser;
import utils.ConsoleWriter;
import utils.FileWorker;

import java.io.IOException;

import static org.junit.Assert.*;

public class GameCreatorTest {
    private final String PATH = "src/resources/testFile.txt";
    private IWrite writer = Mockito.mock(ConsoleWriter.class);
    private FileWorker fileWorker = Mockito.mock(FileWorker.class);
    private ConditionParser conditionParser = Mockito.mock(ConditionParser.class);
    private GameCreator gameCreator = new GameCreator(writer, fileWorker, conditionParser);

    @Test
    public void createGame() throws IOException {
        Generation generation = new Generation();
        LiveGame expected = new LiveGame(generation, 10);
        String content = "Field_size : 2x2;\n" +
                "Iterations_quantity : 10;\n" +
                "Population :\n" +
                "O O" +
                "O X;";
        Mockito.when(fileWorker.readConditionFile(PATH)).thenReturn(Mockito.anyString());
        Mockito.when(conditionParser.parseConditions(content)).thenReturn(expected);
        LiveGame actual = gameCreator.createGame(PATH);
        assertNotNull(actual);
    }

    @Test
    public void whenSoursNotCorrect_ThenException() throws IOException {
        Mockito.when(fileWorker.readConditionFile(Mockito.anyString())).thenThrow(IOException.class);
        assertNull(gameCreator.createGame(Mockito.anyString()));
    }

    @Test
    public void whenConditionsNotValid_ThenException() {
        Mockito.when(conditionParser.parseConditions(Mockito.anyString())).thenThrow(IllegalArgumentException.class);
        assertNull(gameCreator.createGame(Mockito.anyString()));
    }
}