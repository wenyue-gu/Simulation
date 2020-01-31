package View;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SimulationView {
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 1024;
    private Stage simulationViewStage;
    private Scene simulationViewScene;
    private AnchorPane simulationViewPane;
    private SimulationViewSubscene mySubscene;


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


    private void step(){
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
        mySubscene = new SimulationViewSubscene(600, 400);
        simulationViewPane.getChildren().add(mySubscene);
    }

}
