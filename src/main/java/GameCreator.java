import interfaces.IWrite;
import models.LiveGame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConditionParser;
import utils.FileWorker;

import java.io.IOException;

public class GameCreator {
    private IWrite writer;
    private FileWorker fileWorker;
    private ConditionParser conditionParser;

    public GameCreator(IWrite writer, FileWorker fileWorker, ConditionParser conditionParser) {
        this.writer = writer;
        this.conditionParser = conditionParser;
        this.fileWorker = fileWorker;
    }

    public LiveGame createGame(String conditionSource) {
        Logger logger = LoggerFactory.getLogger(GameCreator.class);
        logger.error("Ты пидор");
        LiveGame liveGame = null;
        try {
            String content = fileWorker.readConditionFile(conditionSource);
            liveGame = conditionParser.parseConditions(content);
        } catch (IOException e) {
            writer.write("Cannot read file");

        } catch (IllegalArgumentException e) {
            writer.write(e.getMessage());
        }

        return liveGame;
    }
}
