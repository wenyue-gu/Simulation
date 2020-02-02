package Cells;

import cellsociety.Cell;
import java.util.ArrayList;
import java.util.List;

public class SharkCell extends WaTorCell{
  private int currentState = 1;
  private int energy = 100;
  private final int FISH = 6;

  public SharkCell(int x, int y, int width, int height, int status) {
    super(x, y, width, height, status);
  }

  public void decrementEnergy(){
    energy--;
  }

  public void addEnergy(int amount){
    energy = energy + amount;
  }

  public boolean isDead(){
    return energy <= 0;
  }

  public List<Cell> getNearbyFishes(List<List<Cell>> cellGrid, int type){
    ArrayList<Cell> fishes = new ArrayList<>();
    ArrayList<Cell> neighbours = this.findNeighbours(cellGrid, type);
    for(Cell cell: neighbours){
      if(cell.getCurrentState() == FISH){
        fishes.add(cell);
      }
    }
    return fishes;
  }
}
