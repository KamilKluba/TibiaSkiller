package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SelectKeyWindowController {
    @FXML private Pane pane;
    @FXML private Button buttonCancel;

    private MainWindowController mwc;
    private Stage stage;
    private boolean keyboardSupport;

    public void initialize(){
        pane.setOnMouseClicked(e -> actionMouseClicked(e));
        pane.setOnKeyPressed(e -> actionKeyPressed(e));
    }

    public void myInitialize(MainWindowController mwc, Stage stage, boolean keyboardSupport){
        this.mwc = mwc;
        this.stage = stage;
        this.keyboardSupport = keyboardSupport;
    }

    public void actionButtonCancel(){
        stage.close();
    }

    private void actionMouseClicked(MouseEvent e){
        if(!keyboardSupport) {
            mwc.setSelectedMouseButton(true);
            mwc.setMouseButton(e.getButton());
            stage.close();
        }
    }

    private void actionKeyPressed(KeyEvent e){
        if(keyboardSupport) {
            mwc.setSelectedMouseButton(false);
            mwc.setKeyCode(e.getCode());
            stage.close();
        }
    }
}
