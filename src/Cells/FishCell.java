package Cells;

import cellsociety.Cell;
import java.util.ArrayList;
import java.util.List;

public class FishCell extends WaTorCell {
  private int currentState = 6;


  public FishCell(int x, int y, int width, int height, int status) {
    super(x, y, width, height, status);
  }

  public Cell getCell(){
    return this;
  }


}
