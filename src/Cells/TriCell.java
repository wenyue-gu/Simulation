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
    public TriCell(int x, int y, double width, double height, int status) {
        super(x, y, status);

        if((x+y)%2==0) down=true;
        cellImage = new Polygon();

        if(down){
            ((Polygon) cellImage).getPoints().addAll(
                    (double)y/2*width, (double)x*height,
                    ((double)y/2+1)*width, (double)x*height,
                    ((double)y/2+0.5)*width, (double)(x+1)*height);
        }
        else{
            ((Polygon) cellImage).getPoints().addAll(
                    ((double)y/2+0.5)*width, (double)x*height,
                ((double)y/2+1)*width, (double)(x+1)*height,
                ((double)y/2)*width, (double)(x+1)*height);
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

//    /**
//     * Finds the cell's immediate neighbors and returns a list of them
//     * @param cellGrid is the grid of all the cells
//     * @param type
//     * @return
//     */
//    @Override
//    public ArrayList<Cell> findNeighbours(List<List<Cell>> cellGrid, int type){
//        int[]left = {0,-1};
//        int[]right = {0,1};
//        int[]up = {-1,0};
//        int[]d = {1,0};
//        ArrayList<int[]> coor = new ArrayList<>(Arrays.asList(left, right));
//        coor.add(left);
//        coor.add(right);
//        if(type==4 || type == 10){
//            if(down){
//                coor.add(up);
//            }
//            else{
//                coor.add(d);
//            }
//
//            ArrayList<Cell> ret = findNeighbour(cellGrid, coor);
//            if(type==10){
//                ret = findWrapNeighbour(cellGrid, coor);
//            }
//            return ret;
//        }
//        else if(type==8){
//
//            int[]left2 = {0,-2};
//            int[]right2 = {0,2};
//            int[]upleft = {-1,-1};
//            int[]upright = {-1,1};
//            int[]upleft2 = {-1,-2};
//            int[]upright2 = {-1,2};
//            int[]downleft = {1,-1};
//            int[]downright = {1,1};
//            int[]downleft2 = {1,-2};
//            int[]downright2 = {1,2};
//            coor = new ArrayList<>(Arrays.asList(left, right, left2, right2, up, d, upleft, upright, downleft, downright));
//
//            if(down){
//                coor.add(upleft2);
//                coor.add(upright2);
//            }
//            else{
//                coor.add(downleft2);
//                coor.add(downright2);
//            }
//            ArrayList<Cell> ret = findNeighbour(cellGrid, coor);
//            return ret;
//        }
//        else{
//            return new ArrayList<>();
//        }
//    }
//
//    private ArrayList<Cell> findNeighbour(List<List<Cell>> cellGrid, ArrayList<int[]> coordinate){
//
//        ArrayList<Cell> ret = new ArrayList<>();
//        for(int k=0; k < coordinate.size(); k++){
//            int i = getIndex1() + (coordinate.get(k))[0];
//            int j = getIndex2() + (coordinate.get(k))[1];
//            if (inRange(i,j, cellGrid)){
//                ret.add(cellGrid.get(i).get(j));
//            }
//        }
//        return ret;
//    }
//
//    private ArrayList<Cell> findWrapNeighbour(List<List<Cell>> cellGrid, ArrayList<int[]> coordinate){
//
//        ArrayList<Cell> ret = new ArrayList<>();
//        for(int k=0; k < coordinate.size(); k++){
//            int i = getIndex1() + (coordinate.get(k))[0];
//            int j = getIndex2() + (coordinate.get(k))[1];
//
//            if(i<0) i = cellGrid.size()-1;
//            if(j<0) j = cellGrid.size()-1;
//            if(i==cellGrid.size()) i =0;
//            if(j==cellGrid.size()) j = 0;
//
//            ret.add(cellGrid.get(i).get(j));
//        }
//        return ret;
//    }
//
//    private boolean inRange(int i, int j, List<List<Cell>> cellGrid){
//        return i>-1 && i < cellGrid.size() && j > -1 && j < cellGrid.get(0).size();
//    }


}
