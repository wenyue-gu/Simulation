package View;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SimulationViewSubscene extends SubScene{


    private final static String SUBSCENE_BACKGROUND_IMAGE = "Resources/blue_background_for_popup.png";

    public SimulationViewSubscene(int width, int height) {
        super(new AnchorPane(), width, height);
        prefWidth(width);
        prefHeight(height);
        setBackground();
        subsceneLayout();
    }

    private void subsceneLayout(){
        setLayoutX(100);
        setLayoutY(124);
    }

    private void setBackground(){
        Image backgroundImage = new Image("blue_background_for_popup.png", false);
        BackgroundImage subsceneViewBackground;
        subsceneViewBackground = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane subroot = (AnchorPane) this.getRoot();
        subroot.setBackground(new Background(subsceneViewBackground));
    }

    /**
     * Public method to enable easy acces to the subcene pane
     * @return the pane of the subscene
     */

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }



}
