package IndividualSimulations;

import cellsociety.Cell;
import cellsociety.Simulation;

import java.io.FileNotFoundException;
import java.util.List;
/**
 * Percolation class holds the rules and update behavior for the percolation simulation
 *
 * @author Michelle Tai
 **/
public class Percolation extends Simulation {
  private int OPEN = 0;
  private int CLOSED = 1;
  private int PERCOLATED = 2;

  /**
   * Constructor for the Percolation class that sets its grid to the grid that is passed in as an argument
   * @param grid is a 2d list that holds all the cells that will appear in the simulation
   * @author Michelle Tai
   * */
  public Percolation(List<List<Cell>> grid) throws FileNotFoundException {
    super(grid);
  }

  /**
   * Updates the cells in the Percolation simulation's grid to the next state
   * Also updated the look of the cell
   * @author Michelle Tai
   * */
  @Override
  public void updateGrid(){
    for(List<Cell> rows: cellGrid){
      for(Cell cell:rows){
        checkNeighbourAndChangeNext(cell, cell.findNeighbours(cellGrid, 8));
      }
    }
    for(List<Cell> rows: cellGrid){
      for(Cell cell:rows){
        cell.updateColor();
      }
    }
  }

  /**
   * Updates the cell's state to percolated if any of its neighbors is percolated & the cell itself is open
   * @param cell the cell that is currently being checked
   * @param neighbours are the neighbors of the cell
   */
  @Override
  public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbours){
    for(Cell neighbour:neighbours){
      if(cell.getCurrentState() == OPEN && neighbour.getCurrentState()== PERCOLATED) {
        cell.changeNext(PERCOLATED);
      }
    }
  }

  @Override
  public boolean checkToContinue() {
    return hasPercolated();
  }

  /**
   * Returns whether or not the simulation has "percolated," aka there a path of percolated blue cells
   * from the left side of the grid to the right side of the grid
   * @return a boolean that indicates whether or not the simuation has percolated
   * @author Michelle Tai
   */
  public boolean hasPercolated(){
    int numOfRows = cellGrid.size();
    int numOfCols = cellGrid.get(0).size();
    boolean hasFirstPerc = false;
    boolean hasLastPerc = false;
    Cell leftCell, rightCell;

    for(int i = 0; i < numOfCols; i++){
      leftCell = cellGrid.get(0).get(i);
      rightCell = cellGrid.get(numOfCols - 1).get(i);
      if(leftCell.getCurrentState() == PERCOLATED){
        hasFirstPerc = true;
      }
      if(rightCell.getCurrentState() == PERCOLATED){
        hasLastPerc = true;
      }
    }
    return hasFirstPerc && hasLastPerc;
  }

  /**
   * Checks whether or not the cells did change. If the cells did not change, all the currentStates are
   * equal to their next states
   * @return a boolean, true is did not change, false if did change
   */
  public boolean didNotChange(){
    for(List<Cell> rows: cellGrid){
      for(Cell cell:rows){
        if(cell.getCurrentState() != cell.getNextState()){
          return false;
        }
      }
    }
    return true;
  }

}
