package IndividualSimulations;

import cellsociety.Cell;
import cellsociety.Simulation;

import java.util.List;

public class Fire extends Simulation {

    private int EMPTY = 6;
    private int TREE = 5;
    private int BURNING = 3;
    private double probCatch;
    public boolean isTheFinalHit = false;

    /**
     * Subclass which has super class Simulation
     *
     * @param grid which contains all the cells at time t in simulation
     */
    public Fire(List<List<Cell>> grid, double prob) {
        super(grid);
        probCatch = prob;
    }

    /**
     * Go through each cell in the grid
     * findNeighbours finds the cell's neighbours and put them all in an ArrayList
     * checkNeighbourAndChangeNext updates the cell's nextState according to the currentState of the neighbours
     * Then go through each cell again, update its currentState to nextState, and update color accordingly
     */

    public void updateGrid() {
        for (List<Cell> rows : cellGrid) {
            for (Cell cell : rows) {
                checkNeighbourAndChangeNext(cell, cell.findNeighbours(cellGrid, 8));
            }
        }
        for (List<Cell> rows : cellGrid) {
            for (Cell cell : rows) {
                cell.updateColor();
            }
        }
    }

    /**
     * Record the number of "trees" cells in the neighbours list
     * If satisfying requirement cell's nextState will be updated to FIRE; otherwise the cell is set to EMPTY
     *
     * @param cell       cell whose nextState is being updated
     * @param neighbours the 8 neighbour cells (or however many there are) surrounding the cell
     */

    @Override
    public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbours) {
        if (cell.getCurrentState() == BURNING) {
            cell.changeNext(EMPTY);
            for (Cell neighbour : neighbours) {
                if (neighbour.getCurrentState() == TREE) {
                    int status = (Math.random() <= probCatch) ? BURNING : TREE;
                    neighbour.changeNext(status);
                }
            }
        }
    }

    @Override
    public boolean checkToContinue() {
        return isTheFinalHit;
    }

}

