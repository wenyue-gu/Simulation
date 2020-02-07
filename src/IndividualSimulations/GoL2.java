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
    private RectGrid cellGrid;
    ArrayList<int[]> indices = new ArrayList<>();


    public GoL2(int row, int col, int neighbourNumber, AnchorPane pane){
        super(new ArrayList<>());
        cellGrid = new RectGrid(row, col, neighbourNumber, false);
        cellGrid.iniState(new int[]{DEAD, ALIVE});
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                indices.add(new int[]{i,j});
            }
        }
        cellGrid.addToPane(pane);
    }


    public GoL2(int row, int col, int neighbourNumber, AnchorPane pane, List<List<Integer>>state){
        this(row, col, neighbourNumber, pane);
        cellGrid.iniState(state);
    }



    /**
     * Go through each cell in the grid
     * findNeighbours finds the cell's neighbours and put them all in an ArrayList
     * checkNeighbourAndChangeNext updates the cell's nextState according to the currentState of the neighbours
     * (cont is set to be false (simulation can end) and will become true (simulation should continue) if there is at
     * least one cell whose next state is different from its current state)
     * Then go through each cell again, update its currentState to nextState, and update color accordingly
     */

    public void updateGrid(){
        for(int[]index:indices) {
            ArrayList<Integer> neighbours = cellGrid.neighbourStatus(index);
            int next = checkAndReact(cellGrid.getCell(index), neighbours);
            cellGrid.changeNext(index, next);
        }
        cellGrid.updateAll();
    }

    /**
     * Record the number of "alive" cells in the neighbours list
     * If satisfying requirement cell's nextState will be updated to ALIVE; otherwise the cell dies/stay dead
     */
    public int checkAndReact(int curCellStatus, List<Integer> neighbours){
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
