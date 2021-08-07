package sample;

import javafx.scene.layout.Pane;

public class Grid extends Pane {
    int rows;
    int columns;

    int width;
    int height;

    Cell[][] cells;

    public Grid( int rows, int columns, int width, int height) {
        this.columns = columns;
        this.rows = rows;
        this.width = width;
        this.height = height;

        cells = new Cell[rows][columns];
    }

    /**
     * Add cell to array and to the UI.
     */
    public void add(Cell cell, int row, int column) {

        cells[row][column] = cell;

        double w = width / columns;
        double h = height / rows;
        double x = w * column;
        double y = h * row;

        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(w);
        cell.setPrefHeight(h);

        getChildren().add(cell);

    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public void unhighlight() {
        for( int row=0; row < rows; row++) {
            for( int col=0; col < columns; col++) {
                cells[row][col].unhighlight();
            }
        }
    }

    public boolean isNoWoodAndMirror(){
        boolean isEmpty = true;
        for( int row=0; row < rows; row++) {
            for( int col=0; col < columns; col++) {
                if(cells[row][col].getLife() > 0){
                    isEmpty = false;
                    break;
                }
            }
            if(!isEmpty){
                break;
            }
        }
        return isEmpty;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}