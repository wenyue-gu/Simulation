package IndividualSimulations;

import cellsociety.Simulation;
import cellsociety.Cell;

import java.util.*;

public class WaTor extends Simulation {
  private int FISH = 5;
  private int SHARK = 1;
  private int BLANK = 4;

  private int sharkthreshold;
  private int fishthreshold;
  private int initenergy;

  private List<List<Integer>> SharkEnergy = new ArrayList<>();
  private List<List<Integer>> SharkRound = new ArrayList<>();
  private List<List<Integer>> FishRound = new ArrayList<>();

  public WaTor(List<List<Cell>> grid, int energy, int shark, int fish){
    super(grid);
    initenergy = energy;
    for(List<Cell> rows: cellGrid){
      FishRound.add(new ArrayList<>());
      SharkEnergy.add(new ArrayList<>());
      SharkRound.add(new ArrayList<>());
      for(Cell cell:rows){
        if(cell.getCurrentState()==SHARK) {
          SharkEnergy.get(cell.getIndex1()).add(initenergy);
        }
        else if(cell.getCurrentState()==FISH) {
          SharkEnergy.get(cell.getIndex1()).add(0);
        }
        else{
          SharkEnergy.get(cell.getIndex1()).add(0);
        }
        SharkRound.get(cell.getIndex1()).add(0);
        FishRound.get(cell.getIndex1()).add(0);
      }
    }
    sharkthreshold = shark;
    fishthreshold = fish;
  }

  public void updateGrid(){
    for(List<Cell> rows: cellGrid){
      for(Cell cell:rows){
        if(cell.getCurrentState()==SHARK) {
          checkNeighbourAndChangeNext(cell, cell.findNeighbours(cellGrid, 10));
        }
      }
    }
    for(List<Cell> rows: cellGrid){
      for(Cell cell:rows){
        if(cell.getCurrentState()==FISH) {
          checkNeighbourAndChangeNext(cell, cell.findNeighbours(cellGrid, 10));
        }
      }
    }
    for(List<Cell> rows: cellGrid){
      for(Cell cell:rows){
        cell.updateColor();
      }
    }
  }

  @Override
  public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbours){
    if(cell.getCurrentState()==SHARK){
      SharkMove(cell, neighbours);
      return;
    }
    if(cell.getCurrentState()==FISH){
      FishMove(cell, neighbours);
    }
  }

  private void FishMove(Cell cell, List<Cell> neighbours) {
    if(cell.getNextState()!=FISH){
      return;
    }
    int survived = FishRound.get(cell.getIndex1()).get(cell.getIndex2())+1;
    ArrayList<Cell> EmptyNeighbour = new ArrayList<>();
    for(Cell neighbour:neighbours){
      if(neighbour.getCurrentState()==BLANK && neighbour.getNextState()==BLANK) EmptyNeighbour.add(neighbour);
    }
    if(EmptyNeighbour.size()>0){
      Collections.shuffle(EmptyNeighbour);
      cell.changeNext(BLANK);
      Cell movedTo = EmptyNeighbour.get(0);
      EmptyNeighbour.get(0).changeNext(FISH);
      FishRound.get(movedTo.getIndex1()).set(movedTo.getIndex2(), survived);
      if(survived>fishthreshold) {
        cell.changeNext(FISH);
        FishRound.get(movedTo.getIndex1()).set(movedTo.getIndex2(), 0);
        FishRound.get(cell.getIndex1()).set(cell.getIndex2(), 0);
      }
    }
  }


  private void SharkMove(Cell cell, List<Cell> neighbours) {
    ArrayList<Cell> FishNeighbour = new ArrayList<>();
    ArrayList<Cell> EmptyNeighbour = new ArrayList<>();

    for(Cell neighbour:neighbours){
      if(neighbour.getCurrentState()==FISH && neighbour.getNextState()==FISH) FishNeighbour.add(neighbour);
      if(neighbour.getCurrentState()==BLANK && neighbour.getNextState()==BLANK) EmptyNeighbour.add(neighbour);
    }

    int energy2 = SharkEnergy.get(cell.getIndex1()).get(cell.getIndex2());
    int round = SharkRound.get(cell.getIndex1()).get(cell.getIndex2());
    SharkRound.get(cell.getIndex1()).set(cell.getIndex2(), round);

    if(FishNeighbour.size()>0){
      Cell eaten = moveToPos(cell, FishNeighbour, energy2, round, true);
      sharkReproduce(cell, round+1, eaten);
    }
    else{
      energy2 = energy2-1;
      if(energy2==0){
        cell.changeNext(BLANK);
        SharkEnergy.get(cell.getIndex1()).set(cell.getIndex2(), 0);
        SharkRound.get(cell.getIndex1()).set(cell.getIndex2(), 0);
        return;
      }

      if(EmptyNeighbour.size()>0){
        Cell movedTo = moveToPos(cell, EmptyNeighbour, energy2, round, false);
        sharkReproduce(cell, round+1, movedTo);
      }
    }
  }

  private Cell moveToPos(Cell sharkPos, ArrayList<Cell> neighbour, int energy2, int round, boolean eat) {
    Collections.shuffle(neighbour);
    sharkPos.changeNext(BLANK);
    Cell nextPos = neighbour.get(0);
    neighbour.get(0).changeNext(SHARK);

    if(eat) {
      SharkEnergy.get(nextPos.getIndex1()).set(nextPos.getIndex2(), energy2 + 1);
      SharkEnergy.get(sharkPos.getIndex1()).set(sharkPos.getIndex2(), 0);
      SharkRound.get(nextPos.getIndex1()).set(nextPos.getIndex2(), round + 1);
      SharkRound.get(sharkPos.getIndex1()).set(sharkPos.getIndex2(), 0);
      FishRound.get(nextPos.getIndex1()).set(nextPos.getIndex2(), 0);
      return nextPos;
    }
    SharkEnergy.get(nextPos.getIndex1()).set(nextPos.getIndex2(), energy2);
    SharkEnergy.get(sharkPos.getIndex1()).set(sharkPos.getIndex2(), 0);
    return nextPos;

  }


  private void sharkReproduce(Cell cell, int round, Cell moved) {
    if(round>sharkthreshold){
      cell.changeNext(SHARK);
      SharkRound.get(moved.getIndex1()).set(moved.getIndex2(), 0);
      SharkRound.get(cell.getIndex1()).set(cell.getIndex2(), 0);
      SharkEnergy.get(cell.getIndex1()).set(cell.getIndex2(), initenergy);
    }
  }



  /**
   * Check if there are more "possible" moves for the simulation
   * @return if the simulation should keep going
   */
  @Override
  public boolean checkToContinue(){
    return false;
  }
}
