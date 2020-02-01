package IndividualSimulations;
import cellsociety.Cell;
import cellsociety.Simulation;
import java.util.List;

public class Fire extends Simulation {

    private int EMPTRY = 0;
    private int TREE = 1;
    private int BURNING = 2;

    public Fire (List<List<Cell>> grid) {
        super(grid);
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
     * Record the number of "alive" cells in the neighbours list
     * If satisfying requirement cell's nextState will be updated to ALIVE; otherwise the cell dies/stay dead
     *
     * @param cell       cell whose nextState is being updated
     * @param neighbours the 8 neighbour cells (or however many there are) surrounding the cell
     */
    @Override
    public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbours) {
        int alive = 0;
        for (Cell neighbour : neighbours) {
            if (neighbour.getCurrentState() == ALIVE) alive++;
        }
        if (cell.getCurrentState() == DEAD && alive == 3) cell.changeNext(ALIVE);
        else if (cell.getCurrentState() == ALIVE && (alive == 3 || alive == 2)) cell.changeNext(ALIVE);
        else cell.changeNext(DEAD);
    }

    @Override
    public void updateGrid() {

    }

    @Override
    public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbour) {

    }
}
}
