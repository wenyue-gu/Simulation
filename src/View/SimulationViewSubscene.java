package View;

import Cells.RectCell;
import IndividualSimulations.Fire;
import IndividualSimulations.GoL;
import IndividualSimulations.Percolation;
import IndividualSimulations.Segregation;
import IndividualSimulations.WaTor;
import cellsociety.Cell;
import cellsociety.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.SubScene;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import xml.simulationXML;

public class SimulationViewSubscene extends SubScene {

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    private ResourceBundle myResources;
    private Simulation HardCodeSimulation;
    public Timeline animation;
    private AnchorPane mySubscenePane;
    private simulationXML simXMLInfo;
    private double currTime;
    private int factor = 10;

    private int round = 0;

    public SimulationViewSubscene(int width, int height) {
        super(new AnchorPane(), width, height);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
        prefWidth(width);
        prefHeight(height);
        setBackground();
        subsceneLayout();
        beginAnimation();
        //mySubscenePane.getStylesheets().addAll("default.css");
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
     * @return the current time
     */
    public double getCurrTime(){
        return currTime;
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
        currTime = secondDelay;
        // TO DO: Add simulations here for specific button
        HardCodeSimulation.update(secondDelay, factor);
        // Check for fire simulation
        if (HardCodeSimulation.checkToContinue()) {
            animation.stop();
        }
        // update graph info
        updateSeries();
        addSeriesToChart();
    }


    private void determineStateFrequency(){

    }

    public void stepb(){
        HardCodeSimulation.updateGrid();
    }

    public void factorChange(int i){
        factor = i;
        //System.out.println(factor);
    }

    public void start(simulationXML simInfo) {
        this.simXMLInfo = simInfo;
        createHardCodedSimulation();
        animation.play();
    }


    public void createHardCodedSimulation() {
        List<List<Cell>> myListOfList = new ArrayList<>();
        int row = simXMLInfo.getHeight();
        int col = simXMLInfo.getWidth();
        List<List<Integer>> initialConfig = simXMLInfo.getInitialConfig();
        int cellWidth = 800/row;
        int cellHeight = 600/col;
        int status;
        for (int i = 0; i < row; i++){
            myListOfList.add(new ArrayList<Cell>());
            for (int j=0; j< col;j++){
                if(simXMLInfo.isRandom() && simXMLInfo.getTitle().equals("Game of Life")){
                    status = (Math.random() <=0.5) ?0:1;
                }
                else if(simXMLInfo.isRandom() && simXMLInfo.getTitle().equals("Segregation")){
                    status = (Math.random() <=0.5) ?2:3;
                    status = (Math.random() <=0.10) ?4:status;

                }
                else{
                    status = initialConfig.get(i).get(j);
                }
                Cell cell = new RectCell(i, j, cellWidth, cellHeight, status);
                myListOfList.get(i).add(cell);
                mySubscenePane.getChildren().add(cell.getCellImage());
            }
        }
        simulationChooser(myListOfList);
    }

    private void simulationChooser(List<List<Cell>> myListOfList) {
        if(simXMLInfo.getTitle().equals("Game of Life")){
            HardCodeSimulation = new GoL(myListOfList);
        }
        else if(simXMLInfo.getTitle().equals("Percolation")) {
            HardCodeSimulation = new Percolation(myListOfList);
        }
        else if(simXMLInfo.getTitle().equals("Fire")){
            makeFireSimulation();
        }
        else if(simXMLInfo.getTitle().equals("WaTor")){
            createWaTor();
        }
        else if(simXMLInfo.getTitle().equals("Segregation")){
            HardCodeSimulation = new Segregation(myListOfList, 0.75);
        }
    }


    private void createWaTor(){
        List<List<Cell>> myListOfList = new ArrayList<>();
        int row = 100;
        int col = 100;
        int cellWidth = 800/row;
        int cellHeight = 600/col;
        for (int i = 0; i < row; i++){
            myListOfList.add(new ArrayList<>());
            for (int j=0; j< col;j++){
                int status = (Math.random() <=0.05) ?4:5;
                if(i==0 && j==0) status = 1;
                Cell cell = new RectCell(i, j, cellWidth, cellHeight, status);
                myListOfList.get(i).add(cell);
                mySubscenePane.getChildren().add(cell.getCellImage());
            }
        }
        HardCodeSimulation = new WaTor(myListOfList, 3, 5, 2);
    }

    private void makeFireSimulation(){
        List<List<Cell>> myListOfList = new ArrayList<>();
        int row = 100;
        int col = 100;
        int a = 5;
        int b = 3;
        int cellWidth = 800 / row;
        int cellHeight = 600 / col;
        for (int i = 0; i < row; i++) {
            myListOfList.add(new ArrayList<Cell>());
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    int empty = 6;
                    Cell cell = new RectCell(i, j, cellWidth, cellHeight, empty);
                    myListOfList.get(i).add(cell);
                    mySubscenePane.getChildren().add(cell.getCellImage());
                } else if (i == row / 2 - 1 && j == row / 2 - 1) {
                    Cell cell = new RectCell(i, j, cellWidth, cellHeight, b);
                    myListOfList.get(i).add(cell);
                    mySubscenePane.getChildren().add(cell.getCellImage());
                } else {
                    Cell cell = new RectCell(i, j, cellWidth, cellHeight, a);
                    myListOfList.get(i).add(cell);
                    mySubscenePane.getChildren().add(cell.getCellImage());

                }
            }
        }
        HardCodeSimulation = new Fire(myListOfList, 0.50); // change this to actual number from xml
    }

    private void beginAnimation() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
    }


    public void updateSeries() {

        HardCodeSimulation.che





        round += 1;
        for (int i = 0; i < SimulationViewGUI.lineChart.seriesList.size();i++) {
            SimulationViewGUI.lineChart.updateLineChart(round, HardCodeSimulation.cellGrid.getCellProportions()[i], SimulationViewGUI.lineChart.seriesList.get(i));
        }
    }


    public void addSeriesToChart() {
        for (int i = 0; i < .cellColors().length; i++) {
            XYChart.Series line = new XYChart.Series();
            SimulationViewGUI.lineChart.getData().add(line);
            line.setName(Integer.toString(i));

            SimulationViewGUI.lineChart.seriesList.add(line);
            line.getData().add(new XYChart.Data(0, HardCodeSimulation.cellFrequencies()[i]));

        }
    }

}
