package IndividualSimulations;

import cellsociety.Simulation;
import cellsociety.Cell;

import java.util.*;

public class Segregation extends Simulation {
    private int BLUE = 2;
    private int RED = 3;
    private int BLANK = 4;

    private int[] UnSat = {0, 0, 0};
    private ArrayList<Integer> UnSatisfied;

    private double satisfyRate;

    public Segregation(List<List<Cell>> grid, double satisfy) {
        super(grid);
        satisfyRate = satisfy;
        for (int i = 0; i < 3; i++) {
        //    UnSatisfied.add(0);
        }
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
                if (cell.getNextState() == 100) segUpdate(cell);
            }
        }

        for (List<Cell> rows : cellGrid) {
            for (Cell cell : rows) {
                cell.updateColor();
            }
        }

    }

    /**
     * First record the number of unsatisfied cells in the neighbours list
     *
     * @param cell       cell whose nextState is being updated
     * @param neighbours the 8 neighbour cells (or however many there are) surrounding the cell
     */
    @Override
    public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbours) {
        int Color = cell.getCurrentState();
        int sameColorCell = 0;
        int nonBlankNeighbour = 0;
        double satisfiedRate = 0;
        if (Color == BLANK) {
            UnSat[2]++;
            return;
        }
        for (Cell neighbour : neighbours) {
            if (neighbour.getCurrentState() == Color) sameColorCell++;
            if (neighbour.getCurrentState() != BLANK) nonBlankNeighbour++;
        }

        if (nonBlankNeighbour > 0) satisfiedRate = (double) sameColorCell / nonBlankNeighbour;

        if (satisfiedRate > satisfyRate) cell.changeNext(cell.getCurrentState());
        else {
            cell.changeNext(100);
            if (Color == BLUE) UnSat[1]++;
            if (Color == RED) UnSat[0]++;
        }
    }

    /**
     * @param cell the cell whose state is being updated
     */
    private void segUpdate(Cell cell) {
        boolean set = false;
        while (!set) {
            int type = (new Random()).nextInt(3);
            if (UnSat[type] > 0) {
                set = true;
                cell.changeNext(type + 2);
                UnSat[type]--;
            }
        }
    }

    @Override
    public boolean checkToContinue() {
        return false;
    }
}
