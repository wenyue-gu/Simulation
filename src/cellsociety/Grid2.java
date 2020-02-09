package cellsociety;

import View.SimulationLineChart;
import View.SimulationViewGUI;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class Grid2 {

    //rivate Simulation mySimulation;
    private SimulationLineChart lineChart;
    private List<List<Cell>> grid;

    public Grid2(List<List<Cell>> cells){
       // mySimulation = simulation;
        grid = cells;
        //lineChart = SimulationViewGUI.lineChart;

    }

    public Cell getCell(int row, int col){
        return grid.get(row).get(col);
    }

    public int getSizeOfGridColumns(){
        return grid.get(0).size();
    }

    public int getSizeOfGridRows(){
        return grid.size();
    }

    public void loadGridOnScreen(){

        for(int i = 0; i < getSizeOfGridRows(); i++){
            for (Cell cell: grid.get(i)){
                cell.updateState();
                //cell.updateColor();
            }
        }

       // displayGridOnScreen();

    }

    public LineChart displayGridOnScreen(SimulationLineChart lineChart){
        for (int i=0; i < getSizeOfGridRows(); i++){
            for (int j = 0; j < getSizeOfGridColumns(); j++){
                lineChart.updateLineChartData(this.grid.get(i).get(j));
                this.grid.get(i).get(j).updateColor();

            }
        }

        return lineChart.getLine();
       // simulationPane.getChildren().add(cell)l
    }

}
