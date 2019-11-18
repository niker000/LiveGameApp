import interfaces.IWrite;
import models.LiveGame;
import utils.ConditionParser;
import utils.ConsoleWriter;
import utils.FileWorker;

import java.io.IOException;

public class Main {
    private final static String CONDITIONS = "src\\main\\resources\\conditions";
    private final static String RESULT = "src\\main\\resources\\game_result";

    public static void main(String[] args) {
        IWrite writer = new ConsoleWriter();
        FileWorker fileWorker = new FileWorker();
        ConditionParser conditionParser = new ConditionParser();
        GameCreator gameCreator = new GameCreator(writer, fileWorker, conditionParser);

        LiveGame liveGame = gameCreator.createGame(CONDITIONS);

        if (!(liveGame == null)) {
            liveGame.play();

            try {
                fileWorker.writeResultToFile(RESULT,liveGame.getGeneration().toString());
            }catch (IOException ex) {
                writer.write(ex.getMessage());
            }

        }
    }
}
