package IndividualSimulations;

import cellsociety.Simulation;
import cellsociety.Cell;
import java.util.*;

public class GoL extends Simulation {
    private int DEAD = 0;
    private int ALIVE = 1;
    private boolean cont;

    public GoL(List<List<Cell>> grid){
        super(grid);
    }

    @Override
    public void setData(List<List<Integer>> state) {

    }

    @Override
    public HashMap<String, Integer> frequency() {
        return null;
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
        cont = true;
        for(List<Cell> rows: cellGrid){
            for(Cell cell:rows){
                checkNeighbourAndChangeNext(cell, cell.findNeighbours(cellGrid, 8));
            }
        }
        for(List<Cell> rows: cellGrid){
            for(Cell cell:rows){
                if(cell.getCurrentState()!=cell.getNextState()) cont = false;
                cell.updateColor();
            }
        }
    }

    /**
     * Record the number of "alive" cells in the neighbours list
     * If satisfying requirement cell's nextState will be updated to ALIVE; otherwise the cell dies/stay dead
     * @param cell cell whose nextState is being updated
     * @param neighbours the 8 neighbour cells (or however many there are) surrounding the cell
     */
    @Override
    public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbours){
        int alive = 0;
        for(Cell neighbour:neighbours){
            if(neighbour.getCurrentState()==ALIVE) alive++;
        }
        if(cell.getCurrentState()==DEAD && alive==3) cell.changeNext(ALIVE);
        else if(cell.getCurrentState()==ALIVE && (alive==3 || alive==2)) cell.changeNext(ALIVE);
        else cell.changeNext(DEAD);
    }

}
