package View;

import Cells.RectCell;
import IndividualSimulations.GoL;
import cellsociety.Cell;
import cellsociety.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SimulationViewSubscene extends SubScene{

    private final static String SUBSCENE_BACKGROUND_IMAGE = "Resources/blue_background_for_popup.png";
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Simulation HardCodeSimulation;
    private Timeline animation;
    private AnchorPane mySubscenePane;

    public SimulationViewSubscene(int width, int height) {
        super(new AnchorPane(), width, height);
        prefWidth(width);
        prefHeight(height);
        setBackground();
        subsceneLayout();
        beginAnimation();
    }

    private void subsceneLayout(){
        setLayoutX(100);
        setLayoutY(124);
    }

    private void setBackground(){
        Image backgroundImage = new Image("blue_background_for_popup.png", false);
        BackgroundImage subsceneViewBackground;
        subsceneViewBackground = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        //AnchorPane subroot = (AnchorPane) this.getRoot();
        mySubscenePane = (AnchorPane) this.getRoot();
        //subroot.setBackground(new Background(subsceneViewBackground));
        mySubscenePane.setBackground(new Background(subsceneViewBackground));
    }

    /**
     * Public method to enable easy acces to the subcene pane
     * @return the pane of the subscene
     */

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

    private void step(double secondDelay){
        // TO DO: Add simulations here for specific button
        HardCodeSimulation.update(secondDelay,10);
    }

    public void start () {
        createHardCodedSimulation();
        animation.play();
    }


    public void createHardCodedSimulation(){
        List<List<Cell>> myListOfList = new ArrayList<>();
        int row = 10;
        int col = 10;
        int cellWidth = 800/row;
        int cellHeight = 600/col;
        for (int i = 0; i < row; i++){
            myListOfList.add(new ArrayList<Cell>());
            for (int j=0; j< col;j++){
                int status = (Math.random() <=0.5) ?0:1;
                Cell cell = new RectCell(i, j, cellWidth, cellHeight, status);
                myListOfList.get(i).add(cell);
                mySubscenePane.getChildren().add(cell.getCellImage());
            }
        }
        HardCodeSimulation = new GoL(myListOfList);
    }

    private void beginAnimation(){
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
    }
}
