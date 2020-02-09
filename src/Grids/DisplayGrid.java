package Grids;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class DisplayGrid{

    private ArrayList<ArrayList<Shape>> grid = new ArrayList<>();

    public DisplayGrid(int row, int col){
        for(int i = 0; i<row; i++){
            grid.add(new ArrayList<>());
            for(int j=0; j<col; j++){
                grid.get(i).add(new Rectangle());
            }
        }
    }

    public void setShape(int[]index, Shape shape){
        grid.get(index[0]).set(index[1],shape);
    }

    public void addToPane(AnchorPane pane){
        for(List<Shape> rows: grid){
            for(Shape cell:rows){
                pane.getChildren().add(cell);
            }
        }
    }

    public void removeFromPane(AnchorPane pane){
        for(List<Shape> rows: grid){
            for(Shape cell:rows){
                pane.getChildren().remove(cell);
            }
        }
    }

}
