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
            grid.add(new ArrayList<Cell>());
            for(int j=0; j<col; j++){
                Cell cell = new RectCell(i, j, (double)SimulationViewGUI.SUBSCENE_WIDTH/col,
                        (double)SimulationViewGUI.SUBSCENE_HEIGHT/row, 0);
                grid.get(i).add(cell);
            }
        }
    }

    public ArrayList<Integer> neighbourStatus(int[] index){
        ArrayList<Integer> ret = new ArrayList<>();
        int[] rowDelta = {-1,1,0,0,-1,1,-1,1};
        int[] colDelta = {0,0,-1,1,1,-1,-1,1};
        for(int k=0; k < numNeighbour; k++){
            int x = index[0]+rowDelta[k];
            int y = index[1]+colDelta[k];
            if (inRange(x,y)){
                ret.add(grid.get(x).get(y).getCurrentState());
            }
            else if(wrapped){
                x = (x>grid.size())? 0 : grid.size()-1;
                y = (y>grid.get(0).size())? 0 : grid.get(0).size()-1;
                ret.add(grid.get(x).get(y).getCurrentState());
            }
        }
        return ret;

    }

    private boolean inRange(int i, int j){
        return i>-1 && i < grid.size() && j > -1 && j < grid.get(0).size();
    }

}
