package cellsociety;

import java.util.*;

public abstract class Simulation{
    private ArrayList<ArrayList<Cell>> cellGrid;
    private double time = 0;

    public Simulation(ArrayList<ArrayList<Cell>> grid){
        cellGrid = grid;
    }

    public void update(double elapsedTime, int factor){
        time+=elapsedTime;
        if(time>elapsedTime*factor){
            updateGrid();
            time = 0;
        }
    }

    public ArrayList<ArrayList<Cell>> getCellGrid() {
        return cellGrid;
    }

    public abstract void updateGrid();
    public abstract void checkNeighbourAndChangeNext(Cell cell,  ArrayList<Cell> neighbour);

}

