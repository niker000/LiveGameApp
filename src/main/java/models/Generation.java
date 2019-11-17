package models;

public class Generation {
    private char[][] field;

    public Generation(char[][] field) {
        this.field = field;
    }

    public Generation() {
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                stringBuilder.append(" ").append(field[i][j]);
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
