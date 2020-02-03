//package IndividualSimulations;
//
//import Cells.SharkCell;
//import Cells.WaTorCell;
//import cellsociety.Cell;
//import cellsociety.Simulation;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WaTor extends Simulation {
//  private final int SHARK = 1;
//  private final int FISH = 6;
//  private final int EMPTY = 0;
//
////  protected List<List<Cell>> cellGrid;
//
//  public WaTor(List<List<Cell>> grid){
//    super(grid);
////    cellGrid = grid;
//    for(List<Cell> rows: cellGrid){
//      for(Cell cell:rows){
//        checkNeighbourAndChangeNext(cell, cell.findNeighbours(cellGrid, 8));
//      }
//    }
//    for(List<Cell> rows: cellGrid){
//      for(Cell cell:rows){
//        cell.updateColor();
//      }
//    }
//  }
//
//  @Override
//  public void updateGrid() {
////    if()
//    List<Cell> aboutToBeOccupiedSpots = new ArrayList<>();
//  }
//
//  @Override
//  public void checkNeighbourAndChangeNext(WaTorCell cell, List<Cell> neighbour) {
//      if(cell.isDead()) {
//        cell.changeNext(EMPTY);
//        return;
//      }
//      else if(((SharkCell) cell).getNearbyFishes(cellGrid, 4).size() != 0){
//        int rand = (int)(Math.random() * (((SharkCell) cell).getNearbyFishes(cellGrid, 4).size() + 1));
//
//      }
//    }
//
//  }
//
//  @Override
//  public boolean checkToContinue() {
//    return false;
//  }
//}
