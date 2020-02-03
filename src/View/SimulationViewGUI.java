package View;

import Model.SimulationViewGUIModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulationViewGUI {

    private static final double WIDTH = 1000;
    private static final double HEIGHT = 1024;
    private final static int SUBSCENE_WIDTH = 800;
    private final static int SUBSCENE_HEIGHT = 600;
    //private static final String LABEL_FONT = "resources/kenvector_future.ttf";

    private Stage simulationViewStage;
    private Scene simulationViewScene;
    private AnchorPane simulationViewPane;
    private SimulationViewSubscene mySubscene;

    private static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
    //public static final String STYLESHEET = "default.css";
    public static final String BLANK = " ";

    private  String font ;
    private WebView myPage;
    private SimulationViewInfoLabel simulationViewLabel;
    private SimulationViewButton mySimulationStartButton;
    private SimulationViewButton mySimulationStopButton;
    private SimulationViewButton mySimulationStepButton;
    private SimulationViewButton mySimulationLoadNewFileButton;
    private ResourceBundle myResources;
    private SimulationViewGUIModel mySimulationViewModel;


    /**
     * Create a view of the given model of a web browser with prompts in the given language.
     */
    public SimulationViewGUI(String language) throws FileNotFoundException {

        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        font = myResources.getString("FontStylePath");
        setGameScene();
        createBackgroundImage();
        //makeScene((int)WIDTH, (int)HEIGHT);
        createSubScene();
        createSimulationPane();
    }

    private void makeTopButtons() {
        HBox boxWIthButtons = new HBox(8);
        boxWIthButtons.setPrefWidth(WIDTH);
        boxWIthButtons.setPrefHeight(50);
        mySimulationStartButton = makeButton("StartCommand", event -> startSimulation());
        boxWIthButtons.getChildren().add(mySimulationStartButton);

        mySimulationStopButton = makeButton("StopCommand", event -> stopSimulation());
        boxWIthButtons.getChildren().add(mySimulationStopButton);

        mySimulationStepButton = makeButton("StepCommand", event -> stepThroughSimulation());
        boxWIthButtons.getChildren().add(mySimulationStepButton);


        simulationViewPane.getChildren().add(boxWIthButtons);
    }

    private void makeBottomLabelScene() throws FileNotFoundException {
        HBox labelAtBottom = new HBox();
        labelAtBottom.setPrefHeight(50);
        labelAtBottom.setPrefWidth(WIDTH);
        labelAtBottom.setLayoutY(HEIGHT - 268);
        labelAtBottom.setLayoutX(0);
        simulationViewLabel = new SimulationViewInfoLabel(myResources.getString("WelcomeMessage"), (int) WIDTH, 50);
        simulationViewLabel.setFont((Font.loadFont(new FileInputStream(new File(font)), 23)));
        labelAtBottom.setAlignment(Pos.CENTER);
        labelAtBottom.getChildren().addAll(simulationViewLabel);
        simulationViewPane.getChildren().add(labelAtBottom);
    }


    /**
     * Returns scene for the browser so it can be added to stage.
     */
    public void makeScene(int width, int height) throws FileNotFoundException {

        BorderPane root = new BorderPane();
        //simulationViewPane
        // must be first since other panels may refer to page
        VBox vbox = new VBox(8);
        //root.setCenter(makePageDisplay());
        //vbox.getChildren().addAll(makeInputPanel(), makeVerticalButtons());
        vbox.setPrefHeight(40);


        //root.setTop(makeBottomButtonScene());
        //root.setBottom(makeBottomLabelScene());

        //root.setBottom(makeInformationPanel());
        // control the navigation
        //enableButtons();
        // create scene to hold UI
        //Scene scene = new Scene(root, width, height);
        // activate CSS styling
        //scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        //return scene;
        simulationViewPane.getChildren().add(root);
    }


    /**
     * Start Simulation.
     */
    public void startSimulation() {
        return;
    }


    private void stopSimulation() {
        return;
    }

    private void stepThroughSimulation() {
        return;
    }

    // Display given message as an error in the GUI
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }

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
    private SimulationViewButton makeButton(String property, EventHandler<ActionEvent> handler) {
        // represent all supported image suffixes
        final String IMAGEFILE_SUFFIXES = String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
        SimulationViewButton result = new SimulationViewButton(myResources.getString(property));
        String label = myResources.getString(property);
        if (label.matches(IMAGEFILE_SUFFIXES)) {
            result.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(DEFAULT_RESOURCE_FOLDER + label))));
        } else {
            result.setText(label);
        }
        result.setOnAction(handler);
        return result;
    }


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



    /**
     * method to obtain the stage of the view
     * @return baseStage
     */
    public Stage getSimulationViewStage() {
        return simulationViewStage;
    }

    private void setGameScene() throws FileNotFoundException {
        simulationViewPane = new AnchorPane();
        simulationViewScene = new Scene(simulationViewPane, WIDTH, HEIGHT);
        simulationViewStage = new Stage();
        simulationViewStage.setScene(simulationViewScene);
        simulationViewStage.setResizable(false);
    }

    private void createBackgroundImage(){
        Image backgroundImage = new Image(myResources.getString("SimulationBackground"), false);
        BackgroundImage simulationViewBackground;
        simulationViewBackground = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        simulationViewPane.setBackground(new Background(simulationViewBackground));
    }

    private void createSimulationPane() throws FileNotFoundException {
        makeBottomLabelScene();
        makeTopButtons();
    }

    private void createSubScene(){
        mySubscene = new SimulationViewSubscene(SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
        simulationViewPane.getChildren().add(mySubscene);
    }

//    private void createStartButton(){
//         = new StartButton("START");
//        myStartButton.setLayoutX(0);
//        myStartButton.setLayoutY(0);
//        simulationViewPane.getChildren().add(myStartButton);
//        myStartButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                mySubscene.start();
//            }
//        });
//    }


}



