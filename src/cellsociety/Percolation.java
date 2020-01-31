package cellsociety;

import java.util.ArrayList;

public class Percolation extends Simulation {
  private int OPEN = 0;
  private int CLOSED = 1;
  private int PERCOLATED = 2;
  private boolean hasChanged;
  public Percolation(ArrayList<ArrayList<Cell>> grid){
    super(grid);
  }

  @Override
  public void updateGrid(){

    for(ArrayList<Cell> rows: getCellGrid()){
      for(Cell cell:rows){
        checkNeighbourAndChangeNext(cell, cell.findNeighbours(getCellGrid(), 8));
      }
    }
    for(ArrayList<Cell> rows: getCellGrid()){
      for(Cell cell:rows){
        cell.updateColor();
      }
    }
  }

  @Override
  public void checkNeighbourAndChangeNext(Cell cell, ArrayList<Cell> neighbours){
    for(Cell neighbour:neighbours){
      if(cell.getCurrentState() == OPEN && neighbour.getCurrentState()== PERCOLATED) {
        cell.changeNext(PERCOLATED);
//        hasChanged = true;
      }
    }
  }

  public boolean hasPercolated(){
    return leftRightHasPercolated(this.getCellGrid()) && topBotHasPercolated(this.getCellGrid());
  }

  public boolean didNotChange(){
    boolean didNotUpdate = false;
    for(ArrayList<Cell> rows: getCellGrid()){
      for(Cell cell:rows){
        if(cell.getCurrentState() != cell.ne)
      }
    }
  }

  private boolean leftRightHasPercolated(ArrayList<ArrayList<Cell>> grid){
    int numOfRows = grid.size();
    int numOfCols = grid.get(0).size();
    boolean hasFirstPerc = false;
    boolean hasLastPerc = false;
    Cell leftCell, rightCell;

    for(int i = 0; i < numOfRows; i++){
      leftCell = grid.get(i).get(0);
      rightCell = grid.get(i).get(numOfCols - 1);
      if(leftCell.getCurrentState() == PERCOLATED){
        hasFirstPerc = true;
      }
      if(rightCell.getCurrentState() == PERCOLATED){
        hasLastPerc = true;
      }
    }
    return hasFirstPerc && hasLastPerc;
  }

  private boolean topBotHasPercolated(ArrayList<ArrayList<Cell>> grid){
    int numOfRows = grid.size();
    int numOfCols = grid.get(0).size();
    boolean hasFirstPerc = false;
    boolean hasLastPerc = false;
    Cell topCell, botCell;

    for(int i = 0; i < numOfCols; i++){
      topCell = grid.get(0).get(i);
      botCell = grid.get(numOfRows - 1).get(i);
      if(topCell.getCurrentState() == PERCOLATED){
        hasFirstPerc = true;
      }
      if(botCell.getCurrentState() == PERCOLATED){
        hasLastPerc = true;
      }
    }
    return hasFirstPerc && hasLastPerc;
  }
}
