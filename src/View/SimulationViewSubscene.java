package View;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SimulationViewSubscene extends SubScene{

    private final static int SUBSCENE_WIDTH = 600;
    private final static int SUBSCENE_HEIGHT = 400;
    private final static String SUBSCENE_BACKGROUND_IMAGE = "Resources/blue_background_for_popup.png";

    public SimulationViewSubscene(int width, int height) {
        super(new AnchorPane(), width, height);
        prefWidth(width);
        prefHeight(height);
        setBackground();
        subsceneLayout();
    }

    private void subsceneLayout(){
        setLayoutX(1024);
        setLayoutY(180);
    }

    private void setBackground(){
        BackgroundImage subsceneBackgroundImage = new BackgroundImage(new Image(SUBSCENE_BACKGROUND_IMAGE, 600, 400, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane subroot = (AnchorPane) this.getRoot();
        subroot.setBackground(new Background(subsceneBackgroundImage));
    }

    // get pane of view
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }



}
