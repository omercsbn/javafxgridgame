package sample;

public class LoadGameData {
    Grid grid;
    int currentLevel;
    int currentScore;

    public LoadGameData(Grid grid, int currentLevel, int currentScore) {
        this.grid = grid;
        this.currentLevel = currentLevel;
        this.currentScore = currentScore;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
