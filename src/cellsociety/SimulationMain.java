package cellsociety;

import Model.SimulationViewGUIModel;
//import View.SimulationViewGUI;
import View.SimulationViewGUI;
import javafx.application.Application;
import javafx.stage.Stage;
import java.awt.*;
import java.io.FileNotFoundException;

public class SimulationMain extends Application{
        // convenience constants
        private static final int WIDTH = 1000;
        private static final int HEIGHT = 1024;
        private static final String TITLE = "";// Will get this value from simulation view
        //public static final String DEFAULT_START_PAGE = "https://users.cs.duke.edu/rcd";
        private static final Dimension DEFAULT_SIZE = new Dimension(WIDTH, HEIGHT);//800,600

        @Override
        public void start (Stage stage) throws Exception {
            SimulationViewGUIModel model = new SimulationViewGUIModel();
            SimulationViewGUI display = new SimulationViewGUI("English");
            stage.setTitle(TITLE);
            stage = display.getSimulationViewStage();
            // add our user interface components to Frame and show it
            //stage.setScene(display.getSimulationViewStage());
            //stage.setScene(display.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
            stage.show();
            // start somewhere, less typing for debugging
            //display.showPage(DEFAULT_START_PAGE);
        }

        /**
         * Start of the program.
         */
        public static void main (String[] args) {
            launch(args);
        }
    }


