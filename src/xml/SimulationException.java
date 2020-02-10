package xml;


import cellsociety.SimulationMain;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ResourceBundle;

public class SimulationException extends RuntimeException {
    // for serialization
    private static final long serialVersionUID = 1L;
    private ResourceBundle myResources = SimulationMain.SIMULATION_RESOURCE;

    /**
     * Create an exception based on an issue in our code.
     */
    public SimulationException(String message, Object... values) {
        super(String.format(message, values));
        //showError(myResources.getString("TitleError"));
    }

    /**
     * Create an exception based on a caught exception with a different message.
     */
    public SimulationException(Throwable cause, String message, Object... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public SimulationException(Throwable cause) {
        super(cause);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
        }
    }
}
