package IndividualSimulations;

import Cells.SharkCell;
import cellsociety.Cell;
import cellsociety.Simulation;
import java.util.List;

public class WaTor extends Simulation {
  private final int SHARK = 1;
  private final int FISH = 6;
  private final int EMPTY = 0;

//  protected List<List<Cell>> cellGrid;

  public WaTor(List<List<Cell>> grid){
    super(grid);
//    cellGrid = grid;
  }

  @Override
  public void updateGrid() {
    if()
  }

  @Override
  public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbour) {
    if(cell instanceof SharkCell){
      if(((SharkCell) cell).isDead()){
        cell.changeNext(EMPTY);
        return;
      }
      else if(((SharkCell) cell).getNearbyFishes(cellGrid, 4).size() != 0){
        int rand = (int)(Math.random() * (((SharkCell) cell).getNearbyFishes(cellGrid, 4).size() + 1));

      }
    }

  }
}
