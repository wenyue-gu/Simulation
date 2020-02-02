package IndividualSimulations;

import cellsociety.Simulation;
import cellsociety.Cell;

import java.util.*;

public class Segregation extends Simulation {
    private int BLUE = 2;
    private int RED = 3;
    private int BLANK = 4;


    private int[] UnSat = new int[3];
    private double satisfyRate;

    public Segregation(List<List<Cell>> grid, double satisfy){
        super(grid);
        satisfyRate = satisfy;
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


        Set<Cell> temp = new HashSet<>();
        for (List<Cell> rows : cellGrid) {
            temp.addAll(rows);
        }
        for(Cell cell:temp){
            if(cell.getNextState()==100) {
                    segUpdate(cell);
            }
        }

        for (List<Cell> rows : cellGrid) {
            for (Cell cell : rows) {
                cell.updateColor();
            }
        }

    }

    /**
     * If the cell is blank, increase blank move stat by 1
     * Else, check the cell's neighbours.
     * Record amount of neighbours who is not blank, record number of neighbours with same color
     * Calculate satisfying rate; if it exceeds the set rate, cell's next state remain the same
     * Otherwise, set the next status to 100 (keeping record) and increase # unsatisfied cell of this color by 1
     *
     * @param cell cell whose nextState is being updated
     * @param neighbours the 8 neighbour cells (or however many there are) surrounding the cell
     */
    @Override
    public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbours){
        int Color = cell.getCurrentState();
        int sameColorCell = 0;
        int nonBlankNeighbour = 0;
        double satisfiedRate = 0;
        if(Color==BLANK){
            cell.changeNext(100);
            UnSat[BLANK-2] = UnSat[BLANK-2]+1;
            return;
        }


        for(Cell neighbour:neighbours){
            if(neighbour.getCurrentState()==Color) sameColorCell++;
            if(neighbour.getCurrentState()!=BLANK) nonBlankNeighbour++;
        }

        if(nonBlankNeighbour>0) satisfiedRate = (double)sameColorCell/nonBlankNeighbour;

        if(satisfiedRate>satisfyRate) cell.changeNext(cell.getCurrentState());

        else{
            cell.changeNext(100);
            if(Color==RED){
                UnSat[RED-2] = UnSat[RED-2]+1;
            }
            if(Color==BLUE){
                UnSat[BLUE-2] = UnSat[BLUE-2]+1;
            }
        }


    }

    private void segUpdate(Cell cell){
        boolean set = false;
        int time = 0;
        int type = 0;
        while(!set) {

            if(time==0) type = (new Random()).nextInt(3);
            if(time==1) type = (type+ ((Math.random() <=0.5) ?1:2))%3;
            else type = (type+2)%3;
            if (UnSat[type] > 0) {
                set = true;
                cell.changeNext(type+2);
                UnSat[type] = UnSat[type]-1;
            }
            time++;
        } 
    }





}
