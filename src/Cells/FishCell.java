package Cells;

import cellsociety.Cell;
import java.util.ArrayList;
import java.util.List;

public class FishCell extends WatTorCell {

  public FishCell(int x, int y, int width, int height, int status) {
    super(x, y, width, height, status);
  }

  public Cell getCell(){
    return this;
  }



  @Override
  public int getNextState(){
    return nextState;
  }
  public void changeNext(int i){
    nextState = i;
  }
  public void updateState(){
    currentState = nextState;
  }

}
