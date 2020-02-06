package Cells;

import cellsociety.Cell;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import java.util.*;
import java.util.List;

/**
 * Cell holds information about neighbors and has a Rectangle Shape. The status of the cell reflects the color of the cell
 * @author Lucy Gu
 */

public class TriCell extends Cell {

    private boolean down = false;
    /**
     * Constructor for the RectCell class
     * @param x is the x position on the scene
     * @param y is the y position on the scene
     * @param width is the width of the drawn cell
     * @param height is the height of the drawn cell
     * @param status is the current state of the cell
     */
    public TriCell(int x, int y, int width, int height, int status) {
        super(x, y, status);

        if((x+y)%2==0) down=true;
        cellImage = new Polygon();

        if(down){
            ((Polygon) cellImage).getPoints().addAll(
                    (double)x/2*width, (double)y*height,
                    ((double)x/2+1)*width, (double)y*height,
                    ((double)x/2+0.5)*width, (double)(y+1)*height);
        }



        cellImage.setFill(ColorList[status]);

    }

    /**
     * Updates the color of the cell, which is also updates the state of the cell to the next state
     * @author Lucy Gu
     */
    public void updateColor(){
        updateState();
        cellImage.setFill(ColorList[getCurrentState()]);
    }

    /**
     * Finds the cell's immediate neighbors and returns a list of them
     * @param cellGrid is the grid of all the cells
     * @param type
     * @return
     */
    @Override
    public ArrayList<Cell> findNeighbours(List<List<Cell>> cellGrid, String type){
        if(type.equals("Edge") || type.equals("WrapEdge")){
            int[] rowDelta = {-1,1,0,0};
            int[] colDelta = {0,0,-1,1};
            ArrayList<Cell> ret = findNeighbour(cellGrid,rowDelta,colDelta);
            if(type.equals("WrapEdge")){
                ret = findWrapNeighbour(cellGrid, rowDelta, colDelta);
            }
            return ret;
        }
        else if(type.equals("Full")){
            int[] rowDelta = {-1,1,0,0,-1,1,-1,1};
            int[] colDelta = {0,0,-1,1,1,-1,-1,1};
            ArrayList<Cell> ret = findNeighbour(cellGrid, rowDelta, colDelta);
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

    private ArrayList<Cell> findWrapNeighbour(List<List<Cell>> cellGrid, int[] rowDelta, int[] colDelta){

        ArrayList<Cell> ret = new ArrayList<>();
        for(int k=0; k < rowDelta.length; k++){
            int i = getIndex1() + rowDelta[k];
            int j = getIndex2() + colDelta[k];

            if(i<0) i = cellGrid.size()-1;
            if(j<0) j = cellGrid.size()-1;
            if(i==cellGrid.size()) i =0;
            if(j==cellGrid.size()) j = 0;

            ret.add(cellGrid.get(i).get(j));
        }
        return ret;
    }

    private boolean inRange(int i, int j, List<List<Cell>> cellGrid){
        return i>-1 && i < cellGrid.size() && j > -1 && j < cellGrid.get(0).size();
    }


}
