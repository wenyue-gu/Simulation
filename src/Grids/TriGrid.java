package Grids;

import Cells.TriCell;
import View.SimulationViewGUI;
import cellsociety.Cell;
import cellsociety.Grid;

import java.util.*;

public class TriGrid extends Grid {

    public TriGrid(int row, int col, boolean allNeighbour, boolean wrap){
        super(wrap);
        display = new DisplayGrid(row, col);
        if(allNeighbour) numNeighbour = 12;
        else numNeighbour = 3;
        grid = new ArrayList<>();
        for(int i = 0; i<row; i++){
            grid.add(new ArrayList<>());
            for(int j=0; j<col; j++){
                Cell cell = new TriCell(i, j, (double)SimulationViewGUI.SUBSCENE_WIDTH*2/(col+1),
                        (double)SimulationViewGUI.SUBSCENE_HEIGHT/row, 0);
                grid.get(i).add(cell);
                display.setShape(new int[]{i,j}, cell.getCellImage());
            }
        }
    }

    @Override
    public int[] rowH(int[]index) {
        boolean down = false;
        if ((index[0] + index[1]) % 2 == 0) down = true;
        if (down) {
            return new int[]{0, 0, -1, 1, 0, 0, -1, -1, 1, 1, -1, -1};
        }
        return new int[]{0, 0, 1, -1, 0, 0, -1, -1, 1, 1, 1, 1};
    }

    @Override
    public int[] colH(int[]index) {
        return new int[]{-1, 1, 0, 0, -2, 2, -1, 1, -1, 1, -2, 2};
    }

}
