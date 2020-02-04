package cellsociety;

import View.SimulationViewGUI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class SimulationMain extends Application {
    private static final String TITLE = "Simulation ";// Will get this value from simulation view

    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        SimulationViewGUI display = new SimulationViewGUI("English");
        stage.setTitle(TITLE);
        stage = display.getSimulationViewStage();
        stage.show();
    }

    /**
     * Start of the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}


