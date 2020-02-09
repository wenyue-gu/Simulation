package View;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class SimulationLineChart extends LineChart {

    private CategoryAxis simulationXValues = new CategoryAxis();
    private NumberAxis simulationYValues = new NumberAxis();
    private LineChart<String,Number> lineChart;
    protected   ArrayList<Series> seriesList = new ArrayList <XYChart.Series>();

    public SimulationLineChart(CategoryAxis axisX, NumberAxis axisY) {
        super(axisX, axisY);
        simulationXValues = axisX;
        simulationYValues = axisY;
        simulationXValues.setLabel("Time");
        simulationYValues.setLabel("Frequency of State");
        this.setCreateSymbols(false);

    }

    public SimulationLineChart(Axis axis, Axis axis2, ObservableList data) {
        super(axis, axis2, data);
    }

    public void updateLineChart(int round, double prop, XYChart.Series line) {
        line.getData().add(new XYChart.Data(round, prop));
    }

}
