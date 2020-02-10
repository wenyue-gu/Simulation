package xml;

import cellsociety.SimulationMain;

import java.util.ResourceBundle;

public class SimulationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ResourceBundle myResources = SimulationMain.SIMULATION_RESOURCE;

    /**
     * Create an exception based on an issue in our code.
     */
    public SimulationException(String message, Object... values) {
        super(String.format(message, values));
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

}
