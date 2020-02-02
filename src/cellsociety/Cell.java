package cellsociety;

import java.util.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/** Cell class serves as a superclass
 *    class will be extended by other forms of grid cells specified by Prof
 *    Gets Info from Simulation to specify layout on the simulation screen
 **/
public abstract class Cell{
    private int index1, index2;
    private int currentState;
    private int nextState;
    protected Shape cellImage;

    public Cell (int x, int y, int status){
        index1 = x;
        index2 = y;
        currentState = status;
        nextState = status;
    }
    public int getCurrentState(){
        return currentState;
    }
    public int getNextState(){
        return nextState;
    }
    public int getIndex1(){
        return index1;
    }
    public int getIndex2(){
        return index2;
    }
    public void changeNext(int i){
        nextState = i;
    }
    public void updateState(){
        currentState = nextState;
    }
    public Shape getCellImage(){
        return cellImage;
    }


    public abstract List<Cell> findNeighbours(List<List<Cell>> cellGrid, int type);

    public abstract void updateColor();


}

