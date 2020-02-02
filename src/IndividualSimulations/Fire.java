package IndividualSimulations;
import cellsociety.Cell;
import cellsociety.Simulation;
import java.util.List;
import java.util.Random;

public class Fire extends Simulation {

    private int EMPTY = 6;
    private int TREE = 5;
    private int BURNING = 3;

    public boolean isTheFinalHit = false;

    public Fire(List<List<Cell>> grid) {
        super(grid);
    }

    private boolean isFirst = true;
    private Cell tempCell;

    /**
     * Go through each cell in the grid
     * findNeighbours finds the cell's neighbours and put them all in an ArrayList
     * checkNeighbourAndChangeNext updates the cell's nextState according to the currentState of the neighbours
     * Then go through each cell again, update its currentState to nextState, and update color accordingly
     */

    public void updateGrid() {

        List<Cell> nextCells;

        //Cell cell;
        if (isFirst == true){
            Cell cell = cellGrid.get(cellGrid.size()/2-1).get(cellGrid.get(0).size()/2-1);
            nextCells = cell.findNeighbours(cellGrid, 8);
            checkNeighbourAndChangeNext(cell, cell.findNeighbours(cellGrid, 8));
            cell.updateColor();
            Random rand = new Random();
            tempCell = nextCells.get(rand.nextInt(nextCells.size()));
            isFirst = false;
        }
        else{
            //tempCell = cellGrid.get(cellGrid.size()/2 -1).get(cellGrid.get(0).size()/2 -1);
            nextCells = tempCell.findNeighbours(cellGrid, 8);
            checkNeighbourAndChangeNext(tempCell, tempCell.findNeighbours(cellGrid, 8));
            tempCell.updateColor();
            Random rand = new Random();
            tempCell = nextCells.get(rand.nextInt(nextCells.size()));
        }


//        for (List<Cell> rows : cellGrid) {
//            for (Cell cell : rows) {
//                checkNeighbourAndChangeNext(cell, cell.findNeighbours(cellGrid, 8));
//            }
//        }
//        for (List<Cell> rows : cellGrid) {
//            for (Cell cell : rows) {
//                cell.updateColor();
//            }
//        }
    }

    /**
     * Record the number of "alive" cells in the neighbours list
     * If satisfying requirement cell's nextState will be updated to ALIVE; otherwise the cell dies/stay dead
     *
     * @param cell       cell whose nextState is being updated
     * @param neighbours the 8 neighbour cells (or however many there are) surrounding the cell
     */
    @Override
    public void checkNeighbourAndChangeNext(Cell cell, List<Cell> neighbours) {
          double probCatch = 0.50;

//        if (cell.getIndex1() == 0 || cell.getIndex1() == 99|| cell.getIndex2() == 0 || cell.getIndex2() == 99){
//            isTheFinalHit = true;
//        }
        //decideEndOfGame(cell);
        //for (Cell neighbour : neighbours){

          if (cell.getCurrentState() == BURNING && checkNeigborAtEnd(neighbours)){
            cell.changeCurrent(EMPTY);
        }
              if (cell.getCurrentState() == TREE) {
                  double tempProb = new Random().nextDouble();
                  if (tempProb <= probCatch){
                      cell.changeCurrent(BURNING);
                  }
              }
              else if (cell.getCurrentState() == BURNING && !checkNeigborAtEnd(neighbours)){
                  //cell.changeNext(EMPTY);
                  return;
              }

              else if (cell.getCurrentState() == BURNING){
                  cell.changeNext(EMPTY);
              }
          }
          // These values that seem to be magic values must be changed using number of rows and cols decided by team or XML file

//        int alive = 0;
//        for (Cell neighbour : neighbours) {
//            if (neighbour.getCurrentState() == ALIVE) alive++;
//        }
//        if (cell.getCurrentState() == DEAD && alive == 3) cell.changeNext(ALIVE);
//        else if (cell.getCurrentState() == ALIVE && (alive == 3 || alive == 2)) cell.changeNext(ALIVE);
//        else cell.changeNext(DEAD);
//    }

    //}

    private void decideEndOfGame(Cell cell){
        if (cell.getIndex1() == 0 || cell.getIndex1() == 99|| cell.getIndex2() == 0 || cell.getIndex2() == 99){
            isTheFinalHit = true;
        }
    }

    private boolean checkNeigborAtEnd(List<Cell> neighbours){
        for (Cell neighbour: neighbours){
            boolean end = neighbour.getIndex1() == 0 || neighbour.getIndex1() == 99|| neighbour.getIndex2() == 0 || neighbour.getIndex2() == 99;
            if(neighbour.getCurrentState() == EMPTY && end) {
                isTheFinalHit = true;
                return  true;
            }
        }
        return false;
    }

    @Override
    public boolean checkToContinue(){
        return isTheFinalHit;
    }

}

