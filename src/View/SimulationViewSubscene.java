package View;

import IndividualSimulations.*;
import cellsociety.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ResourceBundle;
import xml.simulationXML;

public class SimulationViewSubscene extends SubScene {

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    private ResourceBundle myResources;

    private Simulation HardCodeSimulation;
    private Timeline animation;
    private AnchorPane mySubscenePane;
    private simulationXML simXMLInfo;
    private int factor = 10;

    public SimulationViewSubscene(int width, int height) {
        super(new AnchorPane(), width, height);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
        prefWidth(width);
        prefHeight(height);
        setBackground();
        subsceneLayout();
        beginAnimation();
    }

    private void subsceneLayout() {
        setLayoutX(25);
        setLayoutY(124);
    }

    private void setBackground() {
        Image backgroundImage = new Image(myResources.getString("SubImage"), false);
        BackgroundImage subsceneViewBackground;
        subsceneViewBackground = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mySubscenePane = (AnchorPane) this.getRoot();
        mySubscenePane.setBackground(new Background(subsceneViewBackground));
    }

    /**
     * Public method to enable easy acces to the subcene pane
     *
     * @return the pane of the subscene
     */

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

    private void step(double secondDelay){
        // TO DO: Add simulations here for specific button
        HardCodeSimulation.update(secondDelay, factor);

    }

    public void stepb(){
        HardCodeSimulation.updateGrid();
    }

    public void factorChange(int i){
        factor = i;
        System.out.println(factor);
    }

    public void start(simulationXML simInfo) {
        this.simXMLInfo = simInfo;
        if(HardCodeSimulation!=null) HardCodeSimulation.getDisplay().removeFromPane(mySubscenePane);
        makeNewSim();
        HardCodeSimulation.getDisplay().addToPane(mySubscenePane);
        animation.play();
    }

    private void makeNewSim(){
        String title = simXMLInfo.getTitle();
        String[] allTitle = {"Game of Life", "Segregation", "Fire", "Percolation", "WaTor"};
        String shape = simXMLInfo.getShape();

        if(title.equals(allTitle[0])) {
            HardCodeSimulation = new GoL2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                    true, shape);
        }
        else if(title.equals(allTitle[1])){
            HardCodeSimulation = new Segregation2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                    true, shape, 0.75);
        }
        else if(title.equals(allTitle[2])){
            HardCodeSimulation = new Fire2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                    true,shape, 0.25);
        }
        else if(title.equals(allTitle[3])){
            HardCodeSimulation = new Percolation2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),true, shape);
        }
        else if(title.equals(allTitle[4])){
            HardCodeSimulation = new WaTor2(simXMLInfo.getHeight(), simXMLInfo.getWidth(),
                    false,shape, 2,10,2);
        }


        if (!simXMLInfo.isRandom()) {
            HardCodeSimulation.setData(simXMLInfo.getInitialConfig());
        }

    }



    private void beginAnimation() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
    }

    public Timeline getAnimation(){
        return animation;
    }
}
