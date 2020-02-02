package Cells;

import cellsociety.Cell;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class RectCell extends Cell {
    private Paint[] ColorList = {Color.WHITE, Color.BLACK, Color.BLUE, Color.RED, Color.WHITE, Color.GREEN};
    //private Rectangle cellImage;


    public RectCell(int x, int y, int width, int height, int status) {
        super(x, y, status);
        cellImage = new Rectangle();
        ((Rectangle) cellImage).setWidth(width);
        ((Rectangle) cellImage).setHeight(height);
        ((Rectangle) cellImage).setX((x+0.5)*((Rectangle) cellImage).getWidth());
        ((Rectangle) cellImage).setY((y+0.5)*((Rectangle) cellImage).getHeight());
        cellImage.setFill(ColorList[status]);
    }

    public void updateColor(){
        updateState();
        cellImage.setFill(ColorList[getCurrentState()]);
    }

    @Override
    public ArrayList<Cell> findNeighbours(List<List<Cell>> cellGrid, int type){
        if(type==4){
            int[] rowDelta = {-1,1,0,0};
            int[] colDelta = {0,0,-1,1};
            ArrayList<Cell> ret = findNeighbour(cellGrid,rowDelta,colDelta);
            return ret;
        }
        else if(type==8){
            int[] rowDelta = {-1,1,0,0,-1,1,-1,1};
            int[] colDelta = {0,0,-1,1,1,-1,-1,1};
            ArrayList<Cell> ret = findNeighbour(cellGrid,rowDelta,colDelta);
            return ret;
        }
        else{
            return new ArrayList<>();
        }
    }

    private ArrayList<Cell> findNeighbour(List<List<Cell>> cellGrid, int[] rowDelta, int[] colDelta){

        ArrayList<Cell> ret = new ArrayList<>();
        for(int k=0; k < rowDelta.length; k++){
            int i = getIndex1() + rowDelta[k];
            int j = getIndex2() + colDelta[k];
            if (inRange(i,j, cellGrid)){
                ret.add(cellGrid.get(i).get(j));
            }
        }
        return ret;
    }

    private boolean inRange(int i, int j, List<List<Cell>> cellGrid){
        return i>-1 && i < cellGrid.size() && j > -1 && j < cellGrid.get(0).size();
    }


}
