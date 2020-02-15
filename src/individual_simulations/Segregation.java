package individual_simulations;

import java.util.*;

/**
 * Records the construction and the rules for the Segregation class
 *
 * Example of use:
 *
 * Simulation newSimulation = new Segregation(10, 10, true, "Rectangle", 0.7)
 *
 * This will create a Segregation simulation with a grid of 10x10 cells. The cells will check all its
 * neighbours (8 for rectangle in this case) to determine if it is satisfied, ie if over 70% of its non-blank
 * neighbours are the same color as itself. The grid is defaulted to be created randomly, with approximately the same
 * number of RED, BLUE, and BLANK cells. In order to set the simulation to have cells that follow an established
 * initial configuration, you can call
 *
 * newSimulation.setData(states)
 *
 * where states is a list of list of integers
 */

public class Segregation extends Simulation {
    private int BLUE = 2;
    private int RED = 3;
    private int BLANK = 4;
    ArrayList<int[]> unsatisfied = new ArrayList<>();
    ArrayList<Integer> unusedCell = new ArrayList<>();
    private double satisfyRate;

    /**
     * Create new segregation grid by calling the constructor in the abstract class to create the grid,
     * initialize the grid with random status between BLUE, BLANK, RED, store the list of index in the
     * index list, and set the satisfied rate with the parameter given
     *
     * @param row               row number of cell
     * @param col               column number of cell
     * @param neighbourNumber   true = all neighbours, false = only immediate
     * @param shape             String that tells if should be triangle or rectangle
     * @param satisfied         the satisfaction rate
     */
    public Segregation(int row, int col, boolean neighbourNumber, String shape, double satisfied) {
        super(row, col, neighbourNumber, false, shape);
        grid.iniState(new int[]{BLUE, BLANK, RED});
        createIndices(row, col);
        setSatisfyRate(satisfied);
    }

    private void setSatisfyRate(double satisfyRate) {
        this.satisfyRate = satisfyRate;
    }

    /**
     * get the red, blue, and blank cell numbers (supposedly they will be the same every time)
     * @return hashmap with the information
     */
    public HashMap<String, Integer> frequency() {
        HashMap<String, Integer>ret = new HashMap<>();
        ret.put("RED", grid.getFreq(RED));
        ret.put("BLUE", grid.getFreq(BLUE));
        ret.put("BLANK", grid.getFreq(BLANK));
        return ret;
    }

    /**
     * go through all possible index of the cells
     * call checkSatisfy, which returns true if the cell is satisfied
     * If the cell is not satisfied, the index of the cell is added to the unsatisfied cell list
     * go through a shuffled unsatisfied cell (for random order of placement) and call randPlace to
     * randomly place possible states to update these cells
     */
    @Override
    public void updateGrid() {
        unsatisfied = new ArrayList<>();
        for (int[] index : indices) {
            List<Integer> neighbours = grid.neighbourStatus(index);
            boolean satisfied = checkSatisfy(grid.getCell(index), neighbours);
            if (!satisfied) unsatisfied.add(index);
            //grid.changeNext(index, next);
        }
        Collections.shuffle(unusedCell);
        for (int[] index2 : unsatisfied) {
            randPlace(index2);
        }
        grid.updateAll();
    }


    /**
     * just here because declared in super class
     * @param curCell
     * @param neighbour
     * @return 0
     */
    public int checkAndReact(int curCell, List<Integer> neighbour) {
        return 0;
    }


    private boolean checkSatisfy(int curCell, List<Integer> neighbours) {
        if (curCell == BLANK) {
            unusedCell.add(BLANK);
            return false;
        }
        int same = 0;
        int nonBlank = 0;
        for (int neighbour : neighbours) {
            if (neighbour == curCell) same++;
            if (neighbour != BLANK) nonBlank++;
        }
        double satisfiedRate = 0;
        if (nonBlank > 0) satisfiedRate = (double) same / nonBlank;
        if (satisfiedRate >= satisfyRate) return true;

        unusedCell.add(curCell);
        return false;

    }

    private void randPlace(int[] index) {
        grid.changeNext(index, unusedCell.get(0));
        unusedCell.remove(0);
    }

}