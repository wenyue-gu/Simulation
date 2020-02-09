package cellsociety;

import javafx.scene.layout.AnchorPane;

import java.util.*;

public abstract class Grid{

    protected int numNeighbour;
    protected boolean wrapped;
    protected ArrayList<ArrayList<Cell>> grid;

    public Grid (int neighbourNumber, boolean wrap){
        wrapped = wrap;
        numNeighbour = neighbourNumber;
    }

    public void iniState(int[]type){
        for(List<Cell> rows: grid){
            for(Cell cell:rows){
                int status = type[(new Random()).nextInt(type.length)];
                cell.changeNext(status);
                cell.updateColor();
            }
        }
    }

    public void iniState(List<List<Integer>> state){
        for(List<Cell> rows: grid){
            for(Cell cell:rows){
                cell.changeNext(state.get(cell.getIndex1()).get(cell.getIndex2()));
                cell.updateColor();
            }
        }
    }

    public int getCell(int[] index){
        return grid.get(index[0]).get(index[1]).getCurrentState();
    }

    public int getCellNext(int[] index){
        return grid.get(index[0]).get(index[1]).getNextState();
    }
    
    public void changeNext(int[]index, int next){
        Cell cell = grid.get(index[0]).get(index[1]);
        cell.changeNext(next);
    }

    public void updateAll(){
        for(List<Cell> rows: grid){
            for(Cell cell:rows){
                cell.updateColor();
            }
        }
    }

    public void addToPane(AnchorPane pane){
        for(List<Cell> rows: grid){
            for(Cell cell:rows){
                pane.getChildren().add(cell.getCellImage());
            }
        }
    }

    public int getFreq(int status){
        int ret = 0;
        for(List<Cell> rows: grid){
            for(Cell cell:rows){
                if(cell.getCurrentState()==status) ret++;
            }
        }
        return ret;
    }

    public abstract ArrayList<Integer> neighbourStatus(int[] index);

    public abstract ArrayList<int[]> neighbourIndexSatisfyingRequirement(int[] index, int fish);
}