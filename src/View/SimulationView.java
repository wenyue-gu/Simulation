package View;

import Cells.RectCell;
import IndividualSimulations.GoL;
import cellsociety.Cell;
import cellsociety.Simulation;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SimulationView {

    private static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private static final double WIDTH = 1000;
    private static final double HEIGHT = 1024;
    private final static int SUBSCENE_WIDTH = 800;
    private final static int SUBSCENE_HEIGHT = 600;






    private Stage simulationViewStage;
    private Scene simulationViewScene;
    private AnchorPane simulationViewPane;
    private SimulationViewSubscene mySubscene;



    // Hardcoded version for creating
    private Simulation HardCodeSimulation;


    public SimulationView(){
        setGameScene();
        createBackgroundImage();
        createTabBarWithButtons();
        createSubScene();
    }

    /**
     * method to obtain the stage of the view
     * @return baseStage
     */
    public Stage getSimulationViewStage() {
        return simulationViewStage;
    }


    private void step(double secondDelay){
        // TO DO: Add simulations here for specific button



    }

    private void setGameScene(){
        simulationViewPane = new AnchorPane();
        simulationViewScene = new Scene(simulationViewPane, WIDTH, HEIGHT);
        simulationViewStage = new Stage();
        simulationViewStage.setScene(simulationViewScene);
        simulationViewStage.setResizable(false);
    }

    private void createBackgroundImage(){
        Image backgroundImage = new Image("Background.jpg", false);
        BackgroundImage simulationViewBackground;
        simulationViewBackground = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        simulationViewPane.setBackground(new Background(simulationViewBackground));
    }

    private void createTabBarWithButtons(){

    }

    private void createSubScene(){
        mySubscene = new SimulationViewSubscene(SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
        simulationViewPane.getChildren().add(mySubscene);
    }

    private void createHardCodedSimulation(){
        List<List<Cell>> myListOfList = new ArrayList<>();
        int row = 10;
        int col = 10;
        int cellWidth = 800/row;
        int cellHeight = 600/col;
        for (int i = 0; i < row; i++){
            myListOfList.add(new ArrayList<Cell>());
            for (int j=0; j< col;j++){
                int status = (Math.random() <=0.5) ?0:1;
                Cell cell = new RectCell(i, j, cellWidth, cellHeight, status);
                myListOfList.get(i).add(cell);
            }
        }
        HardCodeSimulation = new GoL(myListOfList);
    }

    public void startButtonHit (Stage stage) {
        // attach scene to the stage and display it
        //mySubscene = setupGame(SIZE, SIZE, BACKGROUND);
        //stage.setScene(myScene);
        //stage.setTitle(TITLE);
        //stage.show();
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

}
