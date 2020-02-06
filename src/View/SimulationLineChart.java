package View;

import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class SimulationLineChart extends LineChart {

    private NumberAxis simulationXValues = new NumberAxis();
    private NumberAxis simulationYValues = new NumberAxis();
    //protected ArrayList<Series> seriesList = new ArrayList <XYChart.Series>();

    public SimulationLineChart(Axis axisX, Axis axisY) {
        super(axisX, axisY);
        simulationXValues = (NumberAxis) axisX;
        simulationYValues = (NumberAxis) axisY;

        simulationXValues.setLabel("Time/Round");
        simulationYValues.setLabel("Percentage");
        this.setCreateSymbols(false);

    }

    public SimulationLineChart(Axis axis, Axis axis2, ObservableList data) {
        super(axis, axis2, data);
    }

    public void updateLineChart(int round, double prop, XYChart.Series line) {
        line.getData().add(new XYChart.Data(round, prop));
    }
}
