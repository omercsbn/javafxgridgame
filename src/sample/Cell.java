package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Cell extends StackPane {

    int column;
    int row;

    int life = -1;

    int opacity = 1;

    private class CellAnimation extends AnimationTimer{

        @Override
        public void handle(long now) {
            opacity -= 0.01;
            setOpacity(opacity);

            if (opacity <= 0) {

                stop();
            }
        }
    }

    public Cell(int row, int column,int life) {

        this.column = column;
        this.row = row;
        this.life = life;

        this.render();
    }

    public void animate(){
        FadeTransition ft = new FadeTransition(Duration.millis(150), this);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ft.play();

        ft.setOnFinished(event -> {
            render();
            FadeTransition ft2 = new FadeTransition(Duration.millis(150), this);
            ft2.setFromValue(0.0);
            ft2.setToValue(1.0);
            ft2.play();
        });

    }

    public void render(){
        this.getStyleClass().remove("cell-wall");
        this.getStyleClass().remove("cell-empty");
        this.getStyleClass().remove("cell-mirror");
        this.getStyleClass().remove("cell-wood");

        switch (life){
            case -1:{
                this.getStyleClass().add("cell-wall");
                break;
            }
            case 0:{
                this.getStyleClass().add("cell-empty");
                break;
            }
            case 1:{
                this.getStyleClass().add("cell-mirror");
                break;
            }
            case 2:{
                this.getStyleClass().add("cell-wood");
                break;
            }
        }
    }



    public void highlight() {
        setOpacity(0.5);
    }

    public void unhighlight() {
        setOpacity(1);
    }

    public void hoverHighlight() {
        getStyleClass().remove("cell-hover-highlight");
        getStyleClass().add("cell-hover-highlight");
    }

    public void hoverUnhighlight() {
        getStyleClass().remove("cell-hover-highlight");
    }

    public String toString() {
        return this.column + "/" + this.row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}