package cellsociety;

import Grids.DisplayGrid;
import Grids.RectGrid;
import Grids.TriGrid;

import View.SimulationLineChart;
import View.SimulationViewGUI;

import java.io.FileNotFoundException;
import java.util.*;

public abstract class Simulation{
    private double time = 0;
    //private SimulationViewGUI myGUI=new SimulationViewGUI("English");
    private SimulationLineChart lineChart = new SimulationLineChart();;

    protected ArrayList<int[]> indices = new ArrayList<>();
    protected Grid grid;

    public Simulation(int row, int col, boolean neighbourNumber, boolean wrap, String shape){
        if(shape.equals("Rectangle")) grid = new RectGrid(row, col, neighbourNumber, wrap);
        else if (shape.equals("Triangle")) grid = new TriGrid(row, col, neighbourNumber, wrap);
    }

    /**
     * Call updateGrid (which updates the cells shown on screen) only if a certain amount
     * of time has passed
     * @param elapsedTime
     * @param factor
     */
    public void update(double elapsedTime, int factor){
        time+=elapsedTime;
        if(time>elapsedTime*factor){
            updateGrid();
            time = 0;
        }
    }

    /**
     * Initialize grid data with the data from XML file (state will be a list of list of status for each cell)
     * @param state
     */
    public void setData(List<List<Integer>> state){
        grid.iniState(state);
    }

    /**
     * Create a list of indices of all cells
     * @param row row of grid
     * @param col col of grid
     */
    public void createIndices(int row, int col){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                indices.add(new int[]{i, j});
            }
        }
    }

    /**
     * find the frequency of each cell states and put them in a hashmap
     * @return
     */
    public abstract HashMap<String, Integer> frequency();


    public void updateGrid(){
        for(int[]index:indices) {
            ArrayList<Integer> neighbours = grid.neighbourStatus(index);
            int next = checkAndReact(grid.getCell(index), neighbours);
            grid.changeNext(index, next);
        }
        grid.updateAll();
    }

    public DisplayGrid getDisplay(){
        return grid.getDisplay();
    }

    public  abstract int checkAndReact(int curCell, ArrayList<Integer> neighbour);

}

