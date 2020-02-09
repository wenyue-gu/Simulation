package cellsociety;

import java.util.*;

public abstract class Simulation{
    protected List<List<Cell>> cellGrid; //remove this once we have grid class
    private double time = 0;

    protected ArrayList<int[]> indices = new ArrayList<>();
    protected Grid grid;

    public Simulation(List<List<Cell>> grid){
        cellGrid = grid;
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

    public void setData(List<List<Integer>> state){
        grid.iniState(state);
    }

    public void createIndices(int row, int col){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                indices.add(new int[]{i, j});
            }
        }
    }

    public abstract HashMap<String, Integer> frequency();

    public abstract void updateGrid();
    public abstract void checkNeighbourAndChangeNext(Cell cell,  List<Cell> neighbour);

}

