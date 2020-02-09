package View;

import cellsociety.Cell;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SimulationLineChart {

    private CategoryAxis simulationXValues = new CategoryAxis();
    private NumberAxis simulationYValues = new NumberAxis();
    protected LineChart lineChart;//=new LineChart();

    HashMap<Integer, Integer> hash = new HashMap<>();
    HashMap<Integer, XYChart.Series> seriesMap = new HashMap<>();
    int time = 0;

    public SimulationLineChart() {
        //super();
        simulationXValues.setLabel("Time");
        simulationYValues.setLabel("Frequency of State");
        //updateLineChartData();
        //getLine();

    }

    public LineChart getLine() {
        //NumberAxis xAxis = new NumberAxis();
        //NumberAxis yAxis = new NumberAxis();
        time += 1;
        lineChart = new LineChart(simulationXValues, simulationYValues);

        lineChart.setLayoutX(850);
        lineChart.setLayoutY(470);
        lineChart.setPrefWidth(600);
        lineChart.setPrefHeight(250);

        for (Integer key : hash.keySet()) {
            seriesMap.get(key).getData().add(new XYChart.Data(time, hash.get(key)));
            lineChart.getData().add(seriesMap.get(key));
        }
        return lineChart;
    }

    public void updateLineChartData(Cell myCell) {
        if (!hash.containsKey(myCell.getCurrentState())) {
            hash.put(myCell.getCurrentState(), 1);
            seriesMap.put(myCell.getCurrentState(), new XYChart.Series());
        } else {
            hash.put(myCell.getCurrentState(), hash.get(myCell.getCurrentState()) + 1);
        }
    }

}
