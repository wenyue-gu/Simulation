package IndividualSimulations;

import cellsociety.Simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * rock paper scissor
 */
public class RockPaperScissor extends Simulation{
    private int ROCK = 2;
    private int PAPER = 3;
    private int SCISSOR = 5;
    private int threshold;
    //private Grid grid;

    /**
     * Create new rock paper scissor grid
     * @param row               row number of cell
     * @param col               column number of cell
     * @param neighbourNumber   true = all neighbours, false = only immediate
     * @param shape             String that tells if should be triangle or rectangle
     * @param thresh            threshhold for change cell state
     */
    public RockPaperScissor(int row, int col, boolean neighbourNumber, String shape, int thresh){
        super(row, col, neighbourNumber, false, shape);
        grid.iniState(new int[]{ROCK, PAPER, SCISSOR});
        createIndices(row, col);
        threshold = thresh;
    }


    /**
     * ask grid to find the number of rock, paper, and scissor
     * @return      hashmap with the information
     */
    public HashMap<String, Integer> frequency() {
        HashMap<String, Integer>ret = new HashMap<>();
        ret.put("ROCK", grid.getFreq(ROCK));
        ret.put("PAPER", grid.getFreq(PAPER));
        ret.put("SCISSOR", grid.getFreq(SCISSOR));

        return ret;
    }


    /**
     * check if the current cell needs to change state. If there are a certain number or more of its neighbouring cell
     * that is able to beat current cell, change to the status of the neighbouring cell that beats it
     * @param curCellStatus status of current cell
     * @param neighbours    list of status of neighbour current cell
     * @return              status of current cell's next state
     */

    public int checkAndReact(int curCellStatus, List<Integer> neighbours){
        int rock = 0;
        int paper = 0;
        int scissor = 0;

        for(int i : neighbours){
            if(i==ROCK) rock++;
            if(i==PAPER) paper++;
            if(i==SCISSOR) scissor++;
        }

        int thresh = threshold +  + (new Random()).nextInt(2);
        if(curCellStatus==ROCK && paper>thresh) return PAPER;
        if(curCellStatus==SCISSOR && rock>thresh) return ROCK;
        if(curCellStatus==PAPER && scissor>thresh) return SCISSOR;
        return curCellStatus;
    }

}
