package cellsociety;

import cellsociety.Cell;

import java.util.*;

public abstract class Simulation{
    protected List<List<Cell>> cellGrid;
    private double time = 0;

    public Simulation(List<List<Cell>> grid){
        cellGrid = grid;
    }

    /**
     * Call updateGrid (which updates the cells shown on screen) only if a certain amount
     * of time has passed
     * @param elapsedTime
     * @param factor
     */
    public void update(double elapsedTime, int factor){
        time+=elapsedTime;
        if(time>elapsedTime*factor){
            updateGrid();
            time = 0;
        }
    }

    public abstract void updateGrid();
    public abstract void checkNeighbourAndChangeNext(Cell cell,  List<Cell> neighbour);
    public abstract boolean checkToContinue();

}

