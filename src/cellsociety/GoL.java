package cellsociety;

import java.util.*;

public class GoL extends Simulation{
    private int DEAD = 0;
    private int ALIVE = 1;
    public GoL(ArrayList<ArrayList<Cell>> grid){
        super(grid);
    }

    @Override
    public void updateGrid(){

        for(ArrayList<Cell> rows: getCellGrid()){
            for(Cell cell:rows){
                checkNeighbourAndChangeNext(cell, cell.findNeighbours(getCellGrid(), 8));
            }
        }
        for(ArrayList<Cell> rows: getCellGrid()){
            for(Cell cell:rows){
                cell.updateColor();
            }
        }
    }

    @Override
    public void checkNeighbourAndChangeNext(Cell cell, ArrayList<Cell> neighbours){
        int alive = 0;
        for(Cell neighbour:neighbours){
            if(neighbour.getCurrentState()==ALIVE) alive++;
        }
        if(cell.getCurrentState()==DEAD && alive==3) cell.changeNext(ALIVE);
        else if(cell.getCurrentState()==ALIVE && (alive==3 || alive==2)) cell.changeNext(ALIVE);
        else cell.changeNext(DEAD);
    }
}
