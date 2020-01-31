package cellsociety;

import View.SimulationView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start(Stage primaryStage){
        SimulationView mySimulationView = new SimulationView();
        primaryStage = mySimulationView.getSimulationViewStage();
        primaryStage.show();
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

}

