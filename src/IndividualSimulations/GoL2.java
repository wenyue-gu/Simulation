package IndividualSimulations;

import cellsociety.Cell;
import Grids.RectGrid;
import cellsociety.Simulation;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class GoL2 extends Simulation{
    private int DEAD = 0;
    private int ALIVE = 1;


    public GoL2(int row, int col, int neighbourNumber, AnchorPane pane){
        super(new ArrayList<>());
        grid = new RectGrid(row, col, neighbourNumber, false);
        grid.iniState(new int[]{DEAD, ALIVE});
        createIndices(row, col);
        grid.addToPane(pane);
    }


    public void updateGrid(){
        for(int[]index:indices) {
            ArrayList<Integer> neighbours = grid.neighbourStatus(index);
            int next = checkAndReact(grid.getCell(index), neighbours);
            grid.changeNext(index, next);
        }
        grid.updateAll();
    }


    private int checkAndReact(int curCellStatus, List<Integer> neighbours){
        int alive = 0;
        int nextStatus;
        for(int neighbourStatus:neighbours){
            if(neighbourStatus==ALIVE) alive++;
        }
        if(curCellStatus==DEAD && alive==3) nextStatus = ALIVE;
        else if(curCellStatus==ALIVE && (alive==3 || alive==2)) nextStatus = ALIVE;
        else nextStatus = DEAD;
        return nextStatus;
    }















    @Override
    public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbour) {

    }

    @Override
    public boolean checkToContinue() {
        return false;
    }

}
