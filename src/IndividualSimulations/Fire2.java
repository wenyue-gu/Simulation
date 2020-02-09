package IndividualSimulations;

import Grids.RectGrid;
import cellsociety.Cell;
import cellsociety.Grid;
import cellsociety.Simulation;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

public class Fire2 extends Simulation {

    private int EMPTY = 6;
    private int TREE = 5;
    private int BURNING = 3;
    private double probCatch;

    public Fire2(int row, int col, int neighbourNumber, AnchorPane pane, double prob) {
        super(new ArrayList<>());
        grid = new RectGrid(row, col, neighbourNumber, false);
        grid.iniState(new int[]{TREE});
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                indices.add(new int[]{i, j});
                if(i==0 || j==0 || i==row-1 || j==col-1) grid.changeNext(new int[]{i,j}, EMPTY);
            }
        }
        boolean findFire = true;
        while(findFire) {
            Collections.shuffle(indices);
            int i = (indices.get(0))[0];
            int j = (indices.get(0))[1];
            if(!(i==0 || j==0 || i==row-1 || j==col-1)){
                grid.changeNext(new int[]{i,j}, BURNING);
                findFire = false;
            }
        }

        grid.updateAll();
        probCatch = prob;
        grid.addToPane(pane);
    }


    public void updateGrid() {
        for(int[]index:indices) {
            ArrayList<Integer> neighbours = grid.neighbourStatus(index);
            int next = checkAndReact(grid.getCell(index), neighbours);
            grid.changeNext(index, next);
        }
        grid.updateAll();
    }


    private int checkAndReact(int curCell,  ArrayList<Integer> neighbours){
        if(curCell==BURNING) return EMPTY;
        if(curCell==EMPTY) return EMPTY;
        int numBurn = 0;
        for(int n:neighbours){
            if(n==BURNING) numBurn++;
        }
        if(numBurn==0) return TREE;
        int status = TREE;
        for(int i = 0; i<numBurn; i++){
            status = (Math.random() <= probCatch) ? BURNING : status;
        }
        return status;
    }




    @Override
    public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbours) {

    }

    @Override
    public boolean checkToContinue() {
        return false;
    }

}

