package Grids;

import Cells.RectCell;
import View.SimulationViewGUI;
import cellsociety.Cell;
import cellsociety.Grid;
import java.util.*;

public class RectGrid extends Grid {

    public RectGrid(int row, int col, int neighbourNumber, boolean wrap){
        super(neighbourNumber, wrap);
        grid = new ArrayList<>();
        for(int i = 0; i<row; i++){
            grid.add(new ArrayList<>());
            for(int j=0; j<col; j++){
                Cell cell = new RectCell(i, j, (double)SimulationViewGUI.SUBSCENE_WIDTH/col,
                        (double)SimulationViewGUI.SUBSCENE_HEIGHT/row, 0);
                grid.get(i).add(cell);
            }
        }
    }

    public ArrayList<Integer> neighbourStatus(int[] index){
        ArrayList<int[]> neighbours = neighbourIndex(index);
        ArrayList<Integer> ret = new ArrayList<>();
        for(int[] indice:neighbours){
            ret.add(grid.get(indice[0]).get(indice[1]).getCurrentState());
        }

        return ret;
    }

    public ArrayList<int[]> neighbourIndexSatisfyingRequirement(int[]index, int status){
        ArrayList<int[]> neighbours = neighbourIndex(index);
        ArrayList<int[]> ret = new ArrayList<>();
        for(int[] indice:neighbours){
            if(grid.get(indice[0]).get(indice[1]).getCurrentState()==status
            && grid.get(indice[0]).get(indice[1]).getNextState()==status) ret.add(indice);
        }
        return ret;
    }

    private ArrayList<int[]> neighbourIndex(int[]index){
        ArrayList<int[]> ret = new ArrayList<>();
        int[] rowDelta = {-1,1,0,0,-1,1,-1,1};
        int[] colDelta = {0,0,-1,1,1,-1,-1,1};
        for(int k=0; k < numNeighbour; k++){
            int x = index[0]+rowDelta[k];
            int y = index[1]+colDelta[k];
            if (inRange(x,y)){
                ret.add(new int[]{x,y});
            }
            else if(wrapped){
                if(x<0) x = grid.size()-1;
                if(y<0) y = grid.size()-1;
                if(x==grid.size()) x =0;
                if(y==grid.size()) y = 0;
                ret.add(new int[]{x,y});
            }
        }
        return ret;
    }

    private boolean inRange(int i, int j){
        return i>-1 && i < grid.size() && j > -1 && j < grid.get(0).size();
    }

}
