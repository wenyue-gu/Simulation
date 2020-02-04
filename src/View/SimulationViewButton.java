package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

public class SimulationViewButton extends Button {

    private static final int BUTTON_HEIGHT = 45, BUTTON_WIDTH = 190;
    private static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    private ResourceBundle myResources;
    private String font;

   // private final String BUTTON_SEMIURL = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/blue_button03.png');";
    //private final String FONT_SEMIURL = "src/model/resources/kenvector_future.ttf";
   // private final String BUTTON_PRESSED_SEMIURL = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/blue_button02.png');";

    public SimulationViewButton (String words, String language){
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
        font = myResources.getString("FontStylePath");
        setText(words);
        setButtonTextFont();
        setPrefHeight(BUTTON_HEIGHT);//45
        setPrefWidth(BUTTON_WIDTH);//190
        setStyle(font);
        mouseUpdateListener();
    }

    private void setButtonTextFont() {
        try {
            setFont(javafx.scene.text.Font.loadFont(new FileInputStream(font), Double.parseDouble(myResources.getString("FSize"))));
        }
        catch (FileNotFoundException e){
            setFont(Font.font("TimesRoman", 23));
        }
    }

    private void didReleaseButton(){
        setStyle(myResources.getString("ButtonUnpressed"));
        setPrefHeight(45);
        setLayoutY(getLayoutY() - 3);
    }

    private void didPressedButton(){
        //setBackground(new Image(myResources.getString("ButtonPressed")));
        setStyle(myResources.getString("ButtonPressed"));
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 3);

    }

    private void mouseUpdateListener(){

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    //didReleaseButton();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    //didPressedButton();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }

}
