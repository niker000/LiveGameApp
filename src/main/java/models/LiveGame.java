package models;

public class LiveGame {
    private Generation generation;
    private long iterationQuantity;

    public LiveGame(Generation generation, long iterationQuantity) {
        this.iterationQuantity = iterationQuantity;
        this.generation = generation;
    }

    public Generation getGeneration() {
        return this.generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public long getIterationQuantity() {
        return iterationQuantity;
    }

    public void setIterationQuantity(long iterationQuantity) {
        this.iterationQuantity = iterationQuantity;
    }

    public void play() {
        while (iterationQuantity != 0) {
            char[][] nextGeneration = copyField(generation.getField());
            char[][] currentGen = generation.getField();
            for (int i = 0; i < generation.getField().length; i++) {
                for (int j = 0; j < generation.getField()[i].length; j++) {
                    char[] neighbours = new char[8];
                    int liveCellsCount = 0;

                    neighbours[0] = currentGen[Math.floorMod(i - 1, currentGen.length)][Math.floorMod(j - 1, currentGen[i].length)];
                    neighbours[1] = currentGen[Math.floorMod(i - 1, currentGen.length)][j];
                    neighbours[2] = currentGen[Math.floorMod(i - 1, currentGen.length)][Math.floorMod(j + 1, currentGen[i].length)];
                    neighbours[3] = currentGen[i][Math.floorMod(j - 1, currentGen[i].length)];
                    neighbours[4] = currentGen[i][Math.floorMod(j + 1, currentGen[i].length)];
                    neighbours[5] = currentGen[Math.floorMod(i + 1, currentGen.length)][Math.floorMod(j - 1, currentGen[i].length)];
                    neighbours[6] = currentGen[Math.floorMod(i + 1, currentGen.length)][j];
                    neighbours[7] = currentGen[Math.floorMod(i + 1, currentGen.length)][Math.floorMod(j + 1, currentGen[i].length)];

                    for (char neighbour : neighbours) {
                        if (neighbour == CellState.ALIVE.getState()) {
                            liveCellsCount++;
                        }
                    }

                    if (currentGen[i][j] == CellState.DEAD.getState()) {
                        if (liveCellsCount == 3) {
                            nextGeneration[i][j] = CellState.ALIVE.getState();
                        }

                    } else if (liveCellsCount < 2 || liveCellsCount > 3) {
                        nextGeneration[i][j] = CellState.DEAD.getState();
                    }
                }
            }

            generation.setField(nextGeneration);
            iterationQuantity--;
        }
    }

    private char[][] copyField(char[][] current) {
        char[][] newField = new char[current.length][current[0].length];

        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[i].length; j++) {
                newField[i][j] = current[i][j];
            }
        }

        return newField;
    }

}
