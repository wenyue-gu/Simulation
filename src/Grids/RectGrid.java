package Grids;

import Cells.RectCell;
import View.SimulationViewGUI;
import cellsociety.Cell;
import cellsociety.Grid;
import java.util.*;

public class RectGrid extends Grid {


    public RectGrid(int row, int col, boolean allNeighbour, boolean wrap){
        super(wrap);
        display = new DisplayGrid(row, col);
        if(allNeighbour) numNeighbour = 8;
        else numNeighbour = 4;
        grid = new ArrayList<>();
        for(int i = 0; i<row; i++){
            grid.add(new ArrayList<>());
            for(int j=0; j<col; j++){
                Cell cell = new RectCell(i, j, (double)SimulationViewGUI.SIMULATION_VIEW_WIDTH/col,
                        (double)SimulationViewGUI.SIMULATION_VIEW_HEIGHT/row, 0);
                grid.get(i).add(cell);
                display.setShape(new int[]{i,j}, cell.getCellImage());
            }
        }
    }



    @Override
    public int[] rowH(int[]index) {
        return new int[]{-1, 1, 0, 0, -1, 1, -1, 1};
    }

    @Override
    public int[] colH(int[]index) {
        return new int[]{0, 0, -1, 1, 1, -1, -1, 1};
    }


}
