package View;

import cellsociety.Simulation;
import cellsociety.SimulationMain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import xml.SimulationXMLFileChooser;
import xml.SimulationException;
import xml.simulationXML;

public class SimulationViewGUI {

    public static final double WIDTH = 2000;
    public static final double HEIGHT = 1024;
    public final static int SUBSCENE_WIDTH = 1350;//800
    public final static int SUBSCENE_HEIGHT = 600;//600

    public final static int SIMULATION_VIEW_WIDTH =800, SIMULATION_VIEW_HEIGHT = 600;

    private final static int LABEL_LAYOUT = 268, LABEL_HEIGHT = 50;
    private final static int BUTTON_LAYOUT = 10;
    private final static int SLIDER_LAYOUT_X = 270, SLIDER_LAYOUT_Y = 80, SLIDER_MAX = 1000, SLIDER_LENGTH = 450, STEP = 10;//270, 80
    private final static int DEFAULT_INT = 0;

    private ResourceBundle myResources = SimulationMain.SIMULATION_RESOURCE;
    private Stage simulationViewStage;
    private Scene simulationViewScene;
    private AnchorPane simulationViewPane;
    private SimulationViewSubscene mySubscene;
    private Slider simulationViewSlider;
    private String font;
    private SimulationViewInfoLabel simulationViewLabel;
    private SimulationViewButton mySimulationStartButton;
    private SimulationViewButton mySimulationStopButton;
    private SimulationViewButton mySimulationStepButton, mySimulationContinueButton;
    private SimulationViewButton mySimulationLoadNewFileButton;
    private boolean stepboolean = false;
    private int slideVal;
    private simulationXML simulationXMLInfo;

    /**
     * Create a view of the given model of a web browser with prompts in the given language.
     */
    public SimulationViewGUI(String language) throws FileNotFoundException, SimulationException {
        font = myResources.getString("FontStylePath");
        setGameScene();
        createBackgroundImage();
        createSubScene();
        createSimulationPane();
        makeSlider(DEFAULT_INT, SLIDER_MAX, STEP);
        //createLineChartView();
        simulationViewPane.getStylesheets().add("resources/default.css");

    }

    private void makeTopButtons(){
        HBox boxWIthButtons = new HBox(BUTTON_LAYOUT);
        boxWIthButtons.setPrefWidth(WIDTH);
        boxWIthButtons.setPrefHeight(LABEL_HEIGHT);
        boxWIthButtons.setLayoutY(BUTTON_LAYOUT);
        boxWIthButtons.setLayoutY(BUTTON_LAYOUT);
        mySimulationStartButton = makeButton("StartCommand", event -> {
            try {
                startSimulation();
            } catch (Exception e) {
                //throw new SimulationException(e.getMessage());
                System.out.println(myResources.getString("StartError"));
            }
        });
        boxWIthButtons.getChildren().add(mySimulationStartButton);

        mySimulationStopButton = makeButton("StopCommand", event -> stopSimulation());
        boxWIthButtons.getChildren().add(mySimulationStopButton);

        mySimulationContinueButton = makeButton("ContinueCommand", event -> continueSimulation());
        boxWIthButtons.getChildren().add(mySimulationContinueButton);

        mySimulationStepButton = makeButton("StepCommand", event -> stepThroughSimulation());
        boxWIthButtons.getChildren().add(mySimulationStepButton);

        mySimulationLoadNewFileButton = makeButton("LoadFileCommand", event -> {
            try {
                loadFile();
                System.out.println("file loaded");
            } catch (Exception e) {
                // TO DO: Have the error handling instance called
//                try {
//                    throw new SimulationException(e.getMessage(), myResources.getString("SelectFile"));
//                } catch (SimulationException ex) {
//                    ex.printStackTrace();
//                }
            }
        });
        boxWIthButtons.getChildren().add(mySimulationLoadNewFileButton);

        simulationViewPane.getChildren().add(boxWIthButtons);
    }

    private void makeBottomLabelScene() throws FileNotFoundException {
        HBox labelAtBottom = new HBox();
        labelAtBottom.setPrefHeight(LABEL_HEIGHT);
        labelAtBottom.setPrefWidth(WIDTH);
        labelAtBottom.setLayoutY(HEIGHT - LABEL_LAYOUT);
        labelAtBottom.setLayoutX(DEFAULT_INT);
        simulationViewLabel = new SimulationViewInfoLabel(myResources.getString("WelcomeMessage"), (int) WIDTH, LABEL_HEIGHT);
        simulationViewLabel.setFont((Font.loadFont(new FileInputStream(new File(font)), Double.parseDouble(myResources.getString("FSizeLabel")))));
        labelAtBottom.setAlignment(Pos.CENTER);
        labelAtBottom.getChildren().addAll(simulationViewLabel);
        simulationViewPane.getChildren().add(labelAtBottom);
    }

    private void startSimulation() throws SimulationException {
            mySubscene.start(simulationXMLInfo);
    }

    private void stopSimulation() {
        mySubscene.getAnimation().stop();
    }

    private void continueSimulation() {
        mySubscene.getAnimation().play();
    }

    private void stepThroughSimulation() {
        if (!stepboolean) {
            mySubscene.getAnimation().stop();
            stepboolean = true;
        } else {
            mySubscene.getAnimation().stop();
            mySubscene.stepb();
        }
    }

    private void loadFile() throws Exception {
        // TO DO: Michelle add xml stuff
        SimulationXMLFileChooser fileChooser = new SimulationXMLFileChooser();
        fileChooser.openFile(simulationViewStage);
        simulationXMLInfo = fileChooser.getSimulationXMLInfo();
        simulationViewLabel.setText(myResources.getString("SimulationTitle") + " " + simulationXMLInfo.getTitle());
    }

    // Display given message as an error in the GUI
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }

    private SimulationViewButton makeButton(String property, EventHandler<ActionEvent> handler) {

        SimulationViewButton result = new SimulationViewButton(myResources.getString(property), SimulationMain.LANGUAGE);
        String label = myResources.getString(property);

        final String IMAGEFILE_SUFFIXES = String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
        if (label.matches(IMAGEFILE_SUFFIXES)) {
            result.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(SimulationMain.DEFAULT_RESOURCE_FOLDER + label))));
        } else {
            result.setText(label);
        }
        result.setOnAction(handler);

        return result;
    }

    /**
     * method to obtain the stage of the view
     *
     * @return baseStage
     */
    public Stage getSimulationViewStage() {
        return simulationViewStage;
    }

    private void setGameScene() throws FileNotFoundException {
        simulationViewPane = new AnchorPane();
        simulationViewScene = new Scene(simulationViewPane, WIDTH, HEIGHT);
        simulationViewStage = new Stage();
        simulationViewStage.setScene(simulationViewScene);
        simulationViewStage.setResizable(false);
    }

    private void createBackgroundImage() {
        Image backgroundImage = new Image(myResources.getString("SimulationBackground"), false);
        BackgroundImage simulationViewBackground;
        simulationViewBackground = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        simulationViewPane.setBackground(new Background(simulationViewBackground));
    }

    private void createSimulationPane() throws FileNotFoundException, SimulationException {
        makeBottomLabelScene();
        makeTopButtons();
    }

    private void makeSlider(int min, int max, int step) {
        simulationViewSlider = new Slider();
        simulationViewSlider.setMax(max);
        simulationViewSlider.setMin(min);
        simulationViewSlider.setShowTickLabels(false);
        simulationViewSlider.setShowTickMarks(true);
        simulationViewSlider.setMajorTickUnit(SLIDER_MAX);
        simulationViewSlider.setBlockIncrement(step);
        simulationViewSlider.setPrefWidth(SLIDER_LENGTH);
        simulationViewSlider.setMaxWidth(SLIDER_MAX);
        simulationViewSlider.setLayoutX(SLIDER_LAYOUT_X);
        simulationViewSlider.setLayoutY(SLIDER_LAYOUT_Y);
        simulationViewSlider.setValueChanging(true);
        simulationViewSlider.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            slideVal = newvalue.intValue();
            mySubscene.factorChange(STEP / (slideVal / 100 + 1) + 1);
        });
        simulationViewPane.getChildren().add(simulationViewSlider);
    }

    private void createSubScene() {
        mySubscene = new SimulationViewSubscene(SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
        simulationViewPane.getChildren().add(mySubscene);
    }

    public AnchorPane getSimulationViewPane(){
        return simulationViewPane;
    }




}



