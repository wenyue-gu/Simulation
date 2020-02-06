package View;

import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class SimulationLineChart extends LineChart {

    private NumberAxis simulationXValues = new NumberAxis();
    private NumberAxis myY = new NumberAxis();
    protected ArrayList<Series> seriesList = new ArrayList <XYChart.Series>();

    public SimulationLineChart(Axis axis, Axis axis2) {
        super(axis, axis2);
    }

    public SimulationLineChart(Axis axis, Axis axis2, ObservableList data) {
        super(axis, axis2, data);
    }
}
