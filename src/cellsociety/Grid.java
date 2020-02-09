package cellsociety;

import Grids.DisplayGrid;
import java.util.*;

public abstract class Grid{

    protected int numNeighbour;
    protected boolean wrapped;
    protected ArrayList<ArrayList<Cell>> grid;
    protected DisplayGrid display;

    public Grid (boolean wrap){
        wrapped = wrap;
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


    public int getFreq(int status){
        int ret = 0;
        for(List<Cell> rows: grid){
            for(Cell cell:rows){
                if(cell.getCurrentState()==status) ret++;
            }
        }
        return ret;
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



    public ArrayList<int[]> neighbourIndex(int[]index){
        ArrayList<int[]> ret = new ArrayList<>();
        int[] rowDelta = rowH(index);
        int[] colDelta = colH(index);

        for(int k=0; k < numNeighbour; k++){
            int x = index[0]+rowDelta[k];
            int y = index[1]+colDelta[k];
            if (x>-1 && x < grid.size() && y > -1 && y < grid.get(0).size()){
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

    public DisplayGrid getDisplay(){
        return display;
    }


    public abstract int[] rowH(int[]index);

    public abstract int[] colH(int[]index);
}
