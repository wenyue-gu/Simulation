package IndividualSimulations;

import Cells.RectCell;
import Grids.RectGrid;
import View.SimulationViewGUI;
import cellsociety.Cell;
import cellsociety.Grid;
import cellsociety.Simulation;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class WaTor2 extends Simulation {
  private int FISH = 5;
  private int SHARK = 1;
  private int BLANK = 4;

  private int sharkthreshold;
  private int fishthreshold;
  private int initenergy;

  private List<List<int[]>> status = new ArrayList<>();

  public WaTor2(int row, int col, int neighbourNumber, AnchorPane pane, int energy, int sharkR, int fishR) throws FileNotFoundException {
    super(new ArrayList<>());
    initenergy = energy;
    sharkthreshold = sharkR;
    fishthreshold = fishR;

    grid = new RectGrid(row, col, neighbourNumber, true);
    grid.iniState(new int[]{FISH, BLANK, SHARK});

    createIndices(row, col);
    for (int i = 0; i < row; i++) {
      status.add(new ArrayList<>());
      for (int j = 0; j < col; j++) {
        if(grid.getCell(new int[]{i,j})==SHARK) status.get(i).add(new int[]{0, initenergy});
        else status.get(i).add(new int[]{0, 0});
      }
    }

    grid.addToPane(pane);

  }


  @Override
  public HashMap<String, Integer> frequency() {
    HashMap<String, Integer>ret = new HashMap<>();
    ret.put("FISH", grid.getFreq(FISH));
    ret.put("SHARK", grid.getFreq(SHARK));
    ret.put("BLANK", grid.getFreq(BLANK));

    return ret;
  }

  public void updateGrid(){
    //checkAllShark
    ArrayList<int[]> sharkInd = new ArrayList<>();
    for(int[] index:indices){
      if(grid.getCell(index)==BLANK) grid.changeNext(index, BLANK);
      if(grid.getCell(index)==SHARK) sharkInd.add(index);
    }
    Collections.shuffle(sharkInd);
    for(int[] shark:sharkInd) checkSharkUpdate(shark);


    ArrayList<int[]> fishInd = new ArrayList<>();
    for(int[] index:indices){
      if(grid.getCell(index)==FISH && grid.getCellNext(index)==FISH) fishInd.add(index);
    }
    Collections.shuffle(fishInd);
    for(int[] fish:fishInd) checkFishUpdate(fish);

    grid.updateAll();

  }


  private void checkSharkUpdate(int[] index){
    //round++

    int[] roundEnergy = status.get(index[0]).get(index[1]);
    roundEnergy[0]++;

    ArrayList<int[]> fishNeighbour = grid.neighbourIndexSatisfyingRequirement(index, FISH);
    ArrayList<int[]> emptyNeighbour = grid.neighbourIndexSatisfyingRequirement(index, BLANK);

    if(fishNeighbour.size()>0){
      roundEnergy[1]++;
      status.get(index[0]).set(index[1], roundEnergy);

      Collections.shuffle(fishNeighbour);
      int[] fish = fishNeighbour.get(0);
      boolean reproduced = reproduce(index, fish, true);
      if(!reproduced) moveToEmpty(index, fish, true);
      return;
    }

    roundEnergy[1]--;
    status.get(index[0]).set(index[1], roundEnergy);
    if(roundEnergy[1]<=0){
      sharkDeath(index);
      return;
    }
    if(emptyNeighbour.size()==0){
      return;
    }

    Collections.shuffle(emptyNeighbour);
    int[] space = emptyNeighbour.get(0);
    boolean reproduced = reproduce(index, space, true);
    if(!reproduced) moveToEmpty(index, space, true);

  }

  private void sharkDeath(int[] index) {
    grid.changeNext(index, BLANK);
    status.get(index[0]).set(index[1], new int[]{0,0});
  }

  private void moveToEmpty(int[] index, int[] space, boolean isShark) {
    if(isShark){
      moveTo(index, space, BLANK, SHARK);
      return;
    }
    moveTo(index, space, BLANK, FISH);
  }

  private boolean reproduce(int[] creature, int[] moveSpace, boolean isShark){
    int[] state = status.get(creature[0]).get(creature[1]);
    if(isShark && status.get(creature[0]).get(creature[1])[0]>=sharkthreshold){
      grid.changeNext(creature, SHARK);
      grid.changeNext(moveSpace, SHARK);
      status.get(creature[0]).set(creature[1], new int[]{0,initenergy});
      status.get(moveSpace[0]).set(moveSpace[1], new int[]{1, state[1]});
      return true;
    }
    else if(!isShark && status.get(creature[0]).get(creature[1])[0]>=fishthreshold){
      moveTo(creature, moveSpace, FISH, FISH);
      status.get(moveSpace[0]).set(moveSpace[1], new int[]{1,0});
      return true;
    }
    return false;
  }

  private void moveTo(int[] creature, int[] moveSpace, int fish, int fish2) {
    grid.changeNext(creature, fish);
    grid.changeNext(moveSpace, fish2);
    status.get(moveSpace[0]).set(moveSpace[1], status.get(creature[0]).get(creature[1]));
    status.get(creature[0]).set(creature[1], new int[]{0,0});
  }


  private void checkFishUpdate(int[] index){
    int rount = status.get(index[0]).get(index[1])[0];
    rount++;
    status.get(index[0]).set(index[1], new int[]{rount,0});

    ArrayList<int[]> emptyNeighbour = grid.neighbourIndexSatisfyingRequirement(index, BLANK);

    if(emptyNeighbour.size()==0) return;

    Collections.shuffle(emptyNeighbour);
    int[] space = emptyNeighbour.get(0);
    boolean reproduced = reproduce(index, space, false);
    if(!reproduced) moveToEmpty(index,space,false);

  }

  @Override
  public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbours){

  }

}
