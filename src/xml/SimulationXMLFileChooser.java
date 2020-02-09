package xml;

import cellsociety.Pair;
import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class SimulationXMLFileChooser {

  // kind of data files to look for
  public static final String DATA_FILE_EXTENSION = "*.xml";
  // NOTE: generally accepted behavior that the chooser remembers where user left it last
  public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
  private simulationXML simXML;

  public void openFile (Stage primaryStage) throws Exception {
    File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
    while (dataFile != null) {
      try {
        Pair<String, simulationXML> p = new Pair<>(dataFile.getName(), new XMLParser("media").getGame(dataFile));
        // do something "interesting" with the resulting data
        simXML = p.getSecond();
        System.out.println("got data");
//        showMessage(AlertType.INFORMATION, p.getFirst() + "\n" + p.getSecond().toString());
        break;
      }
      catch (SimulationException e) {
        // handle error of unexpected file format
        showMessage(AlertType.ERROR, e.getMessage());
      }
    }
  }

  public simulationXML getSimulationXMLInfo(){
    return simXML;
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

}
