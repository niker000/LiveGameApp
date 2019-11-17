package utils;

import models.CellState;
import models.Generation;
import models.LiveGame;

public class ConditionParser {
    public LiveGame parseConditions(String content) throws IllegalArgumentException {
        String[] conditions = content.split(";");
        StringBuilder stringBuilder;

        for (int i = 0; i < conditions.length; i++) {
            stringBuilder = new StringBuilder(conditions[i].replaceAll("\\s", ""));
            stringBuilder.replace(0, stringBuilder.indexOf(":") + 1, "");
            conditions[i] = stringBuilder.toString();
        }

        int[] fieldSize = parseFieldSize(conditions[0]);
        int iterationQuantity = Integer.parseInt(conditions[1]);
        Generation generation = parseStartGeneration(fieldSize, conditions[conditions.length - 1]);

        return new LiveGame(generation, iterationQuantity);
    }


    private Generation parseStartGeneration(int[] fieldSize, String cells) throws IllegalArgumentException {
        char[][] field = new char[fieldSize[0]][fieldSize[1]];
        char[] cellsStates = cells.toCharArray();

        if (cellsStates.length != (fieldSize[0] * fieldSize[1])) {
            throw new IllegalArgumentException("Population quantity must match the size of the field");
        }
        int numberOfState;

        for (int i = 0; i < fieldSize[0]; i++) {
            for (int j = 0; j < fieldSize[1]; j++) {
                numberOfState = (i + 1) * fieldSize[1] - (fieldSize[1] - j);
                if (CellState.ALIVE.getState() == cellsStates[numberOfState]) {
                    field[i][j] = CellState.ALIVE.getState();
                } else {
                    field[i][j] = CellState.DEAD.getState();
                }
            }
        }

        return new Generation(field);
    }

    private int[] parseFieldSize(String size) throws IllegalArgumentException {
        String[] sizes = size.split("\\D");
        if (sizes.length < 2) {
            throw new IllegalArgumentException("Field must have height and width");
        }

        int[] fieldSize = new int[sizes.length];

        for (int i = 0; i < sizes.length; i++) {
            fieldSize[i] = Integer.valueOf(sizes[i]);
            if (fieldSize[i] == 0) {
                throw new IllegalArgumentException("Height and width mast be bigger then 0");
            }
        }

        return fieldSize;
    }

}
