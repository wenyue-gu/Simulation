package xml;

import cellsociety.Pair;
import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Main extends Application {
    public static final String DATA_FILE_EXTENSION = "*.xml";
    public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);

    @Override
    public void start (Stage primaryStage) throws Exception {
        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        while (dataFile != null) {
            try {
                Pair<String, simulationXML> p = new Pair<>(dataFile.getName(), new XMLParser("media").getGame(dataFile));
                // do something "interesting" with the resulting data
                showMessage(AlertType.INFORMATION, p.getFirst() + "\n" + p.getSecond().toString());
            }
            catch (SimulationException e) {
                // handle error of unexpected file format
                showMessage(AlertType.ERROR, e.getMessage());
            }
            dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);

        }

        // nothing selected, so quit the application
        Platform.exit();
    }

    // display given message to user using the given type of Alert dialog box
    private void showMessage (AlertType type, String message) {
        //new Alert(type, message).showAndWait();
        new Alert(type, message).show();

    }

    // set some sensible defaults when the FileChooser is created
    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }


    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
