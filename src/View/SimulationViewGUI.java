package View;

import Model.SimulationViewGUIModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import javax.imageio.ImageIO;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulationViewGUI {

    private static final double WIDTH = 1000;
    private static final double HEIGHT = 1024;
    private final static int SUBSCENE_WIDTH = 800;
    private final static int SUBSCENE_HEIGHT = 600;

    private Stage simulationViewStage;
    private Scene simulationViewScene;
    private AnchorPane simulationViewPane;
    private SimulationViewSubscene mySubscene;

    private static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
    public static final String STYLESHEET = "default.css";
    public static final String BLANK = " ";

    private WebView myPage;
    private Label simulationViewLabel;
    private SimulationViewButton mySimulationStartButton;
    private SimulationViewButton mySimulationStopButton;
    private SimulationViewButton mySimulationStepButton;
    private SimulationViewButton mySimulationLoadNewFileButton;
    private ResourceBundle myResources;
    private SimulationViewGUIModel mySimulationViewModel;


    /**
     * Create a view of the given model of a web browser with prompts in the given language.
     */
    public SimulationViewGUI(SimulationViewGUIModel model, String language) {
        mySimulationViewModel = model;
        // use resources for labels
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    }

    private Node makeBottomButtonScene() {
        HBox boxWIthButtons = new HBox();



        return boxWIthButtons;
    }

    private Node makeTopLabelScene() {
        HBox labelAtTop = new HBox();
        labelAtTop.setPrefHeight(30);
        return labelAtTop;
    }


    /**
     * Returns scene for the browser so it can be added to stage.
     */
    public Scene makeScene(int width, int height) {

        BorderPane root = new BorderPane();
        // must be first since other panels may refer to page
        VBox vbox = new VBox(8);
        root.setCenter(makePageDisplay());
        //vbox.getChildren().addAll(makeInputPanel(), makeVerticalButtons());
        vbox.setPrefHeight(40);
        root.setTop(vbox);
        root.setBottom(makeBottomButtonScene());
        //root.setBottom(makeInformationPanel());
        // control the navigation
        //enableButtons();
        // create scene to hold UI
        Scene scene = new Scene(root, width, height);
        // activate CSS styling
        scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        return scene;
    }


    /**
     * Display given URL.
     */
    public void showPage(String url) {
//        URL valid = mySimulationViewModel.go(url);
//        if (valid != null) {
//            update(valid);
//        } else {
//            showError("Could not load " + url);
//        }
    }


    // Display given message as information in the GUI
    private void showStatus(String message) {
        simulationViewLabel.setText(message);
    }

    // Display given message as an error in the GUI
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }

    // move to the next URL in the history
//    private void next() {
//        update(myModel.next());
//    }

    //set Home URL
//    private void setHome() {
//
//        update(myModel.getHomeURL());
//    }

    private void displayHome() {

    }

    // move to the previous URL in the history
//    private void back() {
//        update(myModel.back());
//    }

    // update just the view to display given URL
    private void update(URL url) {
        String urlText = url.toString();
        myPage.getEngine().load(urlText);
       // myURLDisplay.setText(urlText);
        //enableButtons();
    }

//    private void changeText() {
//        myURLDisplay.setText(myModel.getHomeURL().toString());
//    }

    // only enable buttons when useful to user
//    private void enableButtons() {
//        mySimulationStopButton.setDisable(!myModel.hasPrevious());
//        mySimulationStepButton.setDisable();
//        //myNextButton.setDisable(! myModel.hasNext());
//    }

    // convenience method to create HTML page display
    private Node makePageDisplay() {
        myPage = new WebView();
        // catch "browsing" events within web page
        //myPage.getEngine().getLoadWorker().stateProperty().addListener(new LinkListener());
        return myPage;
    }

    // make user-entered URL/text field and back/next buttons
//    private Node makeInputPanel() {
//        HBox result = new HBox();
//        // create buttons, with their associated actions
//        // old style way to do set up callback (anonymous class)
//
//        mySimulationStartButton = makeButton("BackCommand", event -> startSimulation());
//        result.getChildren().add(myBackButton);
//        // new style way to do set up callback (lambdas)
//        myNextButton = makeButton("NextCommand", event -> next());
//        result.getChildren().add(myNextButton);
//        // if user presses button or enter in text field, load/show the URL
//        ShowPage showHandler = new ShowPage();
//        result.getChildren().add(makeButton("GoCommand", showHandler));
//        myURLDisplay = makeInputField(40, showHandler);
//        result.getChildren().add(myURLDisplay);
//        return result;
//    }

//    private void startSimulation() {
//    }
//
//    private Node makeVerticalButtons() {
//        HBox additions = new HBox();
//        myHomePageUrlButton = makeButton("HomeCommand", event -> changeText());
//        additions.getChildren().add(myHomePageUrlButton);
//
//        myHomePageShowButton = makeButton("SetHomeCommand", event -> setHome());
//        additions.getChildren().add(myHomePageShowButton);
//        return additions;
//    }
//
//    // make the panel where "would-be" clicked URL is displayed
//    private Node makeInformationPanel() {
//        // BLANK must be non-empty or status label will not be displayed in GUI
//        myStatus = new Label(BLANK);
//        return myStatus;
//    }

    // makes a button using either an image or a label
    private Button makeButton(String property, EventHandler<ActionEvent> handler) {
        // represent all supported image suffixes
        final String IMAGEFILE_SUFFIXES = String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
        Button result = new Button();
        String label = myResources.getString(property);
        if (label.matches(IMAGEFILE_SUFFIXES)) {
            result.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(DEFAULT_RESOURCE_FOLDER + label))));
        } else {
            result.setText(label);
        }
        result.setOnAction(handler);
        return result;
    }

    // make text field for input
//    private TextField makeInputField(int width, EventHandler<ActionEvent> handler) {
//        TextField result = new TextField();
//        result.setPrefColumnCount(width);
//        result.setOnAction(handler);
//        return result;
//    }


    // display page
    // very old style way create a callback (inner class)
//    private class ShowPage implements EventHandler<ActionEvent> {
//        @Override
//        public void handle(ActionEvent event) {
//            showPage(myURLDisplay.getText());
//        }
//    }

    // Inner class to deal with link-clicks and mouse-overs Mostly taken from
    //   http://blogs.kiyut.com/tonny/2013/07/30/javafx-webview-addhyperlinklistener/
//    private class LinkListener implements ChangeListener<Worker.State> {
//        public static final String HTML_LINK = "href";
//        public static final String EVENT_CLICK = "click";
//        public static final String EVENT_MOUSEOVER = "mouseover";
//        public static final String EVENT_MOUSEOUT = "mouseout";
//
//        @Override
//        public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
//            if (newState == Worker.State.SUCCEEDED) {
//                EventListener listener = event -> {
//                    final String href = ((Element) event.getTarget()).getAttribute(HTML_LINK);
//                    if (href != null) {
//                        String domEventType = event.getType();
//                        if (domEventType.equals(EVENT_CLICK)) {
//                            showPage(href);
//                        } else if (domEventType.equals(EVENT_MOUSEOVER)) {
//                            showStatus(href);
//                        } else if (domEventType.equals(EVENT_MOUSEOUT)) {
//                            showStatus(BLANK);
//                        }
//                    }
//                };
//                Document doc = myPage.getEngine().getDocument();
//                NodeList nodes = doc.getElementsByTagName("a");
//                for (int k = 0; k < nodes.getLength(); k += 1) {
//                    EventTarget node = (EventTarget) nodes.item(k);
//                    node.addEventListener(EVENT_CLICK, listener, false);
//                    node.addEventListener(EVENT_MOUSEOVER, listener, false);
//                    node.addEventListener(EVENT_MOUSEOUT, listener, false);
//                }
//            }
//        }
//    }
}



