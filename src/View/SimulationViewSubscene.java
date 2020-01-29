package View;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SimulationViewSubscene extends SubScene{

    private final static int SUBSCENE_WIDTH = 700;
    private final static int SUBSCENE_HEIGHT = 700;


    private final static String SUBSCENE_BACKGROUND_IMAGE = "model/resources/blue_background_for_popup.png";
    //private boolean isSubscrenePresent = true;

    public SimulationViewSubscene(){
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

        BackgroundImage subsceneBackgroundImage = new BackgroundImage(new Image(SUBSCENE_BACKGROUND_IMAGE, 600, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane subroot = (AnchorPane) this.getRoot();
        subroot.setBackground(new Background(subsceneBackgroundImage));

        setLayoutX(1024);
        setLayoutY(180);
    }

    private void upSubsceneDateLayout(int x_layout, int y_layout){
        setLayoutX(1024);
        setLayoutY(180);
    }

//    public void popSubScene(){
//        TranslateTransition popAnimation = new TranslateTransition();
//        popAnimation.setNode(this);
//        popAnimation.setDuration(Duration.seconds(0.25));
//        if (isSubscrenePresent){
//            popAnimation.setToX(-676);
//            isSubscrenePresent = false;
//        }
//        else{
//            popAnimation.setToX(0);
//            isSubscrenePresent = true;
//        }
//        popAnimation.play();
//    }

    // get pane of view
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }

}
