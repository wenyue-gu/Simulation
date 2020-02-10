package IndividualSimulations;

import cellsociety.Simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Percolation class holds the rules and update behavior for the percolation simulation
 *
 **/
public class Percolation2 extends Simulation {
  private int OPEN = 0;
  private int CLOSED = 1;
  private int PERCOLATED = 2;

  //private Grid grid;


    /**
     * Create new percolation grid
     * @param row               row number of cell
     * @param col               column number of cell
     * @param neighbourNumber   true = all neighbours, false = only immediate
     */
  public Percolation2(int row, int col, boolean neighbourNumber, String shape){
      super(row, col, neighbourNumber, false, shape);
      grid.iniState(new int[]{OPEN, CLOSED});

      createIndices(row, col);

      boolean findWater = true;
      while(findWater) {
          Collections.shuffle(indices);
          int i = (indices.get(0))[0];
          int j = (indices.get(0))[1];
          if(!(j==col-1)){
              grid.changeNext(new int[]{i,j}, PERCOLATED);
              findWater = false;
          }
      }


      grid.updateAll();
  }


    /**
     * get a hashmap that tells us how many open, percolated, and closed cells there are
     * @return
     */
    @Override
    public HashMap<String, Integer> frequency() {
        HashMap<String, Integer>ret = new HashMap<>();
        ret.put("OPEN", grid.getFreq(OPEN));
        ret.put("PERCOLATED", grid.getFreq(PERCOLATED));
        ret.put("CLOSED", grid.getFreq(CLOSED));
        return ret;
    }


    /**
     * if current cell is percolated or closed, don't change
     * if current cell is open, check if there is neighbour who is percolated. If so, percolate
     * @param curCell       current cell's status
     * @param neighbours    list of integers denoted to the neighbour's status
     * @return              the state the curcell should change to
     */
    public int checkAndReact(int curCell, ArrayList<Integer> neighbours){
      if(curCell==PERCOLATED || curCell==CLOSED) return curCell;
      for(int neighbour:neighbours){
          if(neighbour==PERCOLATED) return PERCOLATED;
      }
      return curCell;
  }


}
