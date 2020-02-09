package IndividualSimulations;

import cellsociety.Simulation;

import java.util.*;

public class Segregation2 extends Simulation {
    private int BLUE = 2;
    private int RED = 3;
    private int BLANK = 4;
    ArrayList<int[]> unsatisfied = new ArrayList<>();
    ArrayList<Integer> unusedCell = new ArrayList<>();
    //private Grid grid;

    private double satisfyRate;

    /**
     * Create new segregation grid
     * @param row               row number of cell
     * @param col               column number of cell
     * @param neighbourNumber   true = all neighbours, false = only immediate
     */
    public Segregation2(int row, int col, boolean neighbourNumber, String shape, double satisfied) {
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
     * @return
     */
    @Override
    public HashMap<String, Integer> frequency() {
        HashMap<String, Integer>ret = new HashMap<>();
        ret.put("RED", grid.getFreq(RED));
        ret.put("BLUE", grid.getFreq(BLUE));
        ret.put("BLANK", grid.getFreq(BLANK));
        return ret;
    }

    /**
     * check all the cells and see if they are satisfied; if not, collect index
     * for all unsatisfied cells do random placement
     */
    @Override
    public void updateGrid() {
        unsatisfied = new ArrayList<>();
        for (int[] index : indices) {
            ArrayList<Integer> neighbours = grid.neighbourStatus(index);
            int next = checkSatisfy(grid.getCell(index), neighbours);
            if (next==100) unsatisfied.add(index);
            grid.changeNext(index, next);
        }
        //Collections.shuffle(unsatisfied);
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
     * @return
     */
    public int checkAndReact(int curCell, ArrayList<Integer> neighbour) {
        return 0;
    }


    private int checkSatisfy(int curCell, ArrayList<Integer> neighbours) {
        if (curCell == BLANK) {
            unusedCell.add(BLANK);
            return 100;
        }
        int same = 0;
        int nonBlank = 0;
        for (int neighbour : neighbours) {
            if (neighbour == curCell) same++;
            if (neighbour != BLANK) nonBlank++;
        }
        double satisfiedRate = 0;
        if (nonBlank > 0) satisfiedRate = (double) same / nonBlank;
        if (satisfiedRate >= satisfyRate) return curCell;

        unusedCell.add(curCell);
        return 100;

    }


    private void randPlace(int[] index) {
        grid.changeNext(index, unusedCell.get(0));
        unusedCell.remove(0);
    }


}
