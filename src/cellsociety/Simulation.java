package cellsociety;

import View.SimulationLineChart;
import View.SimulationViewGUI;

import java.io.FileNotFoundException;
import java.util.*;

public abstract class Simulation{
    protected List<List<Cell>> cellGrid;
    private double time = 0;
    private SimulationViewGUI myGUI=new SimulationViewGUI("English");
    private SimulationLineChart lineChart = new SimulationLineChart();;

    public Simulation(List<List<Cell>> grid) throws FileNotFoundException {
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
           Grid2 gr=new Grid2(cellGrid);
           //lineChart = new SimulationLineChart();
            gr.displayGridOnScreen(lineChart);
           // myGUI.createLineChartView();
            //lineChart.updateLineChartData();
            myGUI.getSimulationViewPane().getChildren().add(lineChart.getLine());
            time = 0;
        }
    }

    public abstract void updateGrid();
    public abstract void checkNeighbourAndChangeNext(Cell cell,  List<Cell> neighbour);
    public abstract boolean checkToContinue();


    public void createLineChartView(){
        //simulationViewPane.getChildren().add(lineChart.getLine());
    }

//    public SimulationLineChart getLine(){
//        return lineChart;
//    }

}

