package View;

import SpecificMenuButton.StartButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SimulationView {
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 1024;
    private final static int SUBSCENE_WIDTH = 800;
    private final static int SUBSCENE_HEIGHT = 600;

    private Stage simulationViewStage;
    private Scene simulationViewScene;
    private AnchorPane simulationViewPane;
    private SimulationViewSubscene mySubscene;

    // Hardcoded version for creating

    private SimulationViewButton myStartButton;

    public SimulationView(){
        setGameScene();
        createBackgroundImage();
        createTabBarWithButtons();
        createSubScene();
        createStartButton();
    }

    /**
     * method to obtain the stage of the view
     * @return baseStage
     */
    public Stage getSimulationViewStage() {
        return simulationViewStage;
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

    private void createStartButton(){
        myStartButton = new StartButton("START");
        myStartButton.setLayoutX(0);
        myStartButton.setLayoutY(0);
        simulationViewPane.getChildren().add(myStartButton);
        myStartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mySubscene.start();
            }
        });
    }
}
