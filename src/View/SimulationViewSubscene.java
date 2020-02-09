package View;

import IndividualSimulations.*;
import cellsociety.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.SubScene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import xml.SimulationException;
import xml.simulationXML;

public class SimulationViewSubscene extends SubScene {

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int LINE_GRAPH_X = 550, LINE_GRAPH_Y = 500, LINE_GRAPH_X_LAYOUT = 800, LINE_GRAPH_Y_LAYOUT = 100;
    private static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    private ResourceBundle myResources;

    private Simulation mySimulation;
    private Timeline animation;
    private AnchorPane mySubscenePane;
    private simulationXML simXMLInfo;
    private int factor = 10;
    private int time = 0;

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    private LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
    private HashMap<String, XYChart.Series> seriesMap = new HashMap<>();
    private ArrayList<XYChart.Series> timeSeriesArrayList = new ArrayList<>();

    public SimulationViewSubscene(int width, int height) {
        super(new AnchorPane(), width, height);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
        prefWidth(width);
        prefHeight(height);
        setBackground();
        subsceneLayout();
        beginAnimation();
        displayLineChart();
        mySubscenePane.getStylesheets().addAll("default.css");
    }

    private void subsceneLayout() {
        setLayoutX(25);
        setLayoutY(124);
    }

    private void setBackground() {
        Image backgroundImage = new Image(myResources.getString("SubImage"), false);
        BackgroundImage subsceneViewBackground;
        subsceneViewBackground = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mySubscenePane = (AnchorPane) this.getRoot();
        mySubscenePane.setBackground(new Background(subsceneViewBackground));
    }

    /**
     * Public method to enable easy acces to the subcene pane
     *
     * @return the pane of the subscene
     */

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

    private void step(double secondDelay){
        mySimulation.update(secondDelay, factor);
        updateSeries();
        time += 1;
    }

    public void stepb(){
        mySimulation.updateGrid();
    }

    public void factorChange(int i){
        factor = i;
    }

    public void start(simulationXML simInfo) throws SimulationException {
        this.simXMLInfo = simInfo;
        if(HardCodeSimulation!=null) HardCodeSimulation.getDisplay().removeFromPane(mySubscenePane);
        makeNewSim();
        HardCodeSimulation.getDisplay().addToPane(mySubscenePane);
        createTimeSeries();
        animation.play();
    }

    private void makeNewSim() throws SimulationException {
        String title = simXMLInfo.getTitle();
        String[] allTitle = {"Game of Life", "Segregation", "Fire", "Percolation", "WaTor"};
        String shape = simXMLInfo.getShape();

        if(title.equals(allTitle[0])) {
            HardCodeSimulation = new GoL2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                    true, shape);
            try {
                mySimulation = new GoL2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                        8, mySubscenePane);
            } catch (FileNotFoundException e) {
                throw new SimulationException(e);
            }
        }
        else if(title.equals(allTitle[1])){
            HardCodeSimulation = new Segregation2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                    true, shape, 0.75);
            try {
                mySimulation = new Segregation2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                        8, mySubscenePane, 0.75);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(title.equals(allTitle[2])){
            HardCodeSimulation = new Fire2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                    true,shape, 0.25);
            try {
                mySimulation = new Fire2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                        8, mySubscenePane, 0.55);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(title.equals(allTitle[3])){
            HardCodeSimulation = new Percolation2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),true, shape);
            try {
                mySimulation = new Percolation2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                        8, mySubscenePane);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(title.equals(allTitle[4])){
            HardCodeSimulation = new WaTor2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                    false,shape, 2,10,2);
            try {
                mySimulation = new WaTor2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                        4, mySubscenePane, 2,10,2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (!simXMLInfo.isRandom()) {
            mySimulation.setData(simXMLInfo.getInitialConfig());
        }

    }




    private void beginAnimation() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);

    }

    public Timeline getAnimation(){
        return animation;
    }

    private void displayLineChart(){
        //mySubscenePane.getChildren().add()
        lineChart.setPrefHeight(LINE_GRAPH_Y);
        lineChart.setPrefWidth(LINE_GRAPH_X);
        lineChart.setLayoutX(LINE_GRAPH_X_LAYOUT);
        lineChart.setLayoutY(LINE_GRAPH_Y_LAYOUT);
        mySubscenePane.getChildren().add(lineChart);
    }

    private void createTimeSeries(){
        HashMap<String, Integer> map = mySimulation.frequency();
        for (String i : map.keySet()){
            XYChart.Series series = new XYChart.Series();
            series.getData().add(new XYChart.Data(time+"", map.get(i)));
            series.setName(i);
            timeSeriesArrayList.add(series);
            lineChart.getData().addAll(series);
        }
    }

    private void updateSeries(){
        HashMap<String, Integer> map = mySimulation.frequency();
        for (XYChart.Series series: timeSeriesArrayList){
            series.getData().add(new XYChart.Data(time+"", map.get(series.getName())));
        }
    }
}
