package Cells;

import cellsociety.Cell;
import java.util.ArrayList;
import java.util.List;

public class WaTorCell extends RectCell {
  private int currentRow;
  private int currentCol;
  private int surviveTime;
  private ArrayList<Cell> neighbours;
  private int nextRow;
  private int nextCol;
  private int currentState;

//  private int reproduceTime;


  public WaTorCell(int x, int y, int width, int height, int status) {
    super(x, y, width, height, status);
    currentRow = x;
    currentCol = y;
  }

  public void setNextRow(int newRow){
    nextRow = newRow;
  }

  public void setNextCol(int newCol){
    nextCol = newCol;
  }

  public void setSurviveTime(int newTime){
    surviveTime = newTime;
  }

  public int getSurviveTime(){
    return surviveTime;
  }

  public boolean canReproduce(int elapsedTime, int reproduceTime){
    return (elapsedTime - surviveTime) >= reproduceTime;
  }

//  public void move(int newRow, int newCol){
//    updateState();
//    this.setNextRow(newRow);
//    this.setNextCol(newCol);
//  }



  public ArrayList<Cell> findNeighbours(List<List<Cell>> cellGrid, int type){
    if(type==4){
      int[] rowDelta = {-1,1,0,0};
      int[] colDelta = {0,0,-1,1};
      ArrayList<Cell> ret = findNeighbour(cellGrid,rowDelta,colDelta);
      return ret;
    }
    else if(type==8){
      int[] rowDelta = {-1,1,0,0,-1,1,-1,1};
      int[] colDelta = {0,0,-1,1,1,-1,-1,1};
      ArrayList<Cell> ret = findNeighbour(cellGrid,rowDelta,colDelta);
      return ret;
    }
    else{
      return new ArrayList<>();
    }
  }

  private ArrayList<Cell> findNeighbour(List<List<Cell>> cellGrid, int[] rowDelta, int[] colDelta){
    ArrayList<Cell> ret = new ArrayList<>();
    for(int k=0; k < rowDelta.length; k++){
      int row = getIndex1() + rowDelta[k];
      int col = getIndex2() + colDelta[k];
      if (row < 0){
        int lastRow = cellGrid.size() - 1;
        ret.add(cellGrid.get(lastRow).get(col));
      }
      else if(row >= cellGrid.size()){
        ret.add(cellGrid.get(0).get(col));
      }
      if(col < 0){
        int lastCol = cellGrid.get(row).size() - 1;
        ret.add(cellGrid.get(row).get(lastCol));
      }
      else if(col >= cellGrid.get(row).size()){
        ret.add(cellGrid.get(row).get(0));
      }
    }
    neighbours = ret;
    return ret;
  }

  public List<Cell> findOpenSpaces(){
    List<Cell> emptySpots = new ArrayList<>();
    for(Cell cell : neighbours){
      if(cell.getCurrentState() == 0){
        emptySpots.add(cell);
      }
    }
    if(emptySpots.size() == 0){
      return null;
    }
    return emptySpots;
//    else{
//      int rand = (int)(Math.random() * (emptySpots.size() + 1));
//      return emptySpots.get(rand);
//    }
  }

}
