package models;

public enum CellState {
    DEAD('O'),
    ALIVE('X');

    private char state;

    CellState(char state) {
        this.state = state;
    }

    public char getState() {
        return state;
    }
}
