package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.control.Button;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SimulationViewButton extends Button {
    private final String BUTTON_SEMIURL = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/blue_button03.png');";
    private final String FONT_SEMIURL = "src/model/resources/kenvector_future.ttf";
    private final String BUTTON_PRESSED_SEMIURL = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/blue_button02.png');";

    public SimulationViewButton (String words){
        setText(words);
        setButtonTextFont();
        setPrefHeight(45);
        setPrefWidth(190);
        setStyle(BUTTON_SEMIURL);
        mouseUpdateListener();
    }

    private void setButtonTextFont() {
        try {
            setFont(javafx.scene.text.Font.loadFont(new FileInputStream(FONT_SEMIURL), 23));
        }
        catch (FileNotFoundException e){
            setFont(Font.font("TimesRoman", 23));
        }
    }

    private void didReleaseButton(){
        setStyle(BUTTON_SEMIURL);
        setPrefHeight(45);
        setLayoutY(getLayoutY() - 3);
    }

    private void didPressedButton(){
        setStyle(BUTTON_PRESSED_SEMIURL);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 3);

    }

    private void mouseUpdateListener(){

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    didPressedButton();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    didReleaseButton();
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
