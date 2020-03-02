package controllers;

import data.Action;
import data.Point;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;


public class MainWindowController {
    @FXML private TableView<Action> tableViewActions;
    @FXML private TableColumn<Action, String> tableColumnActionType;
    @FXML private TableColumn<Action, Integer> tableColumnFirstUse;
    @FXML private TableColumn<Action, Point> tableColumnPointStart;
    @FXML private TableColumn<Action, Point> tableColumnPointEnd;
    @FXML private TableColumn<Action, Integer> tableColumnPeriodAndBuffer;
    @FXML private TableColumn<Action, Integer> tableColumnRepetitions;
    @FXML private TableColumn<Action, Integer> tableColumnTimeToNextAction;
    @FXML private Button buttonSelectKey;
    @FXML private ComboBox<String> comboBoxActionType;
    @FXML private TextField textFieldSelectedKey;
    @FXML private Button buttonPoint1;
    @FXML private Button buttonPoint2;
    @FXML private TextField textFieldFirstUse;
    @FXML private TextField textFieldPeriod;
    @FXML private TextField textFieldBuffer;
    @FXML private TextField textFieldRepetitions;
    @FXML private Label labelError;
    @FXML Button buttonAddAction;
    @FXML Button buttonDeleteAction;
    @FXML Button buttonStart;
    @FXML Button buttonStop;

    private int selectedIndex = 0;
    private boolean selectedMouseButton;
    private MouseButton mouseButton;
    private KeyCode keyCode;
    private Point point1 = new Point();
    private Point point2 = new Point();


    public void initialize(){
        tableViewActions.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> actionSelectionChanged());
        tableColumnActionType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableColumnFirstUse.setCellValueFactory(new PropertyValueFactory<>("firstUse"));
        tableColumnPointStart.setCellValueFactory(new PropertyValueFactory<>("pointStart"));
        tableColumnPointEnd.setCellValueFactory(new PropertyValueFactory<>("pointEnd"));
        tableColumnPeriodAndBuffer.setCellValueFactory(new PropertyValueFactory<>("periodAndBuffer"));
        tableColumnRepetitions.setCellValueFactory(new PropertyValueFactory<>("repetitions"));
        tableColumnTimeToNextAction.setCellValueFactory(new PropertyValueFactory<>("timeToNextAction"));

        comboBoxActionType.getItems().add("Kliknięcie klawisza");
        comboBoxActionType.getItems().add("Wciśnięcie klawisza");
        comboBoxActionType.getItems().add("Zwolnienie klawisza");
        comboBoxActionType.getItems().add("Kliknięcie myszy");
        comboBoxActionType.getItems().add("Wciśnięcie myszy");
        comboBoxActionType.getItems().add("Zwolnienie myszy");
        comboBoxActionType.getItems().add("Przesunięcie myszy");
        comboBoxActionType.getItems().add("Przeciągnięcie myszy");
        comboBoxActionType.getSelectionModel().select(0);
    }

    private void actionSelectionChanged(){

    }

    public void actionComboBoxActionType(){
        selectedIndex = comboBoxActionType.getSelectionModel().getSelectedIndex();

        switch(selectedIndex){
            case 0:
            case 1:
            case 2:
                buttonSelectKey.setText("Podaj klawisz");
                buttonPoint1.setVisible(false);
                buttonPoint2.setVisible(false);
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                buttonSelectKey.setText("Podaj przycisk myszy");
                buttonPoint1.setVisible(true);
                buttonPoint2.setVisible(false);
                break;
            case 7:
                buttonSelectKey.setText("Podaj przycisk myszy");
                buttonPoint1.setVisible(true);
                buttonPoint2.setVisible(true);
        }

        textFieldSelectedKey.setText("");
    }

    public void actionButtonPoint1(){
        openTransparentWindow(true);
    }

    public void actionButtonPoint2(){
        openTransparentWindow(false);
    }

    private void openTransparentWindow(boolean isPoint1){
        VBox vBox = new VBox();

        Stage stage = new Stage();
        stage.setScene(new Scene(vBox));
        //stage.setOpacity(0.5f); //Uncomment if you want to test and see the stage
        stage.setOpacity(0.1);
        stage.setMaximized(true);

        vBox.setOnMouseClicked(event ->{
            int x = (int)event.getScreenX();
            int y = (int)event.getScreenY();
            if(isPoint1) {
                point1 = new Point(event.getScreenX(), event.getScreenY());
                buttonPoint1.setText(x + "," + y);
            }
            else{
                point2 = new Point(event.getScreenX(), event.getScreenY());
                buttonPoint2.setText(x + "," + y);
            }
            stage.close();
        });

        stage.showAndWait();
    }

    public void actionButtonAddAction(){
        try{
            int firstUse = !textFieldFirstUse.equals("") ? Integer.parseInt(textFieldFirstUse.getText()) : 0;
            int period = !textFieldPeriod.getText().equals("") ? Integer.parseInt(textFieldPeriod.getText()) : 0;
            int buffer = !textFieldBuffer.getText().equals("") ? Integer.parseInt(textFieldBuffer.getText()) : 0;
            int repetitions = !textFieldRepetitions.getText().equals("") ? Integer.parseInt(textFieldRepetitions.getText()) : 0;;

            if(mouseButton == null){
                tableViewActions.getItems().add(new Action(selectedIndex, keyCode, firstUse, point1,
                        point2, period, buffer, repetitions));
            }
            else{
                tableViewActions.getItems().add(new Action(selectedIndex, mouseButton, firstUse, point1,
                        point2, period, buffer, repetitions));
            }
            buttonDeleteAction.setDisable(false);
            buttonStart.setDisable(false);
        } catch (Exception e){
            labelError.setText("Coś poszło nie tak!");
            e.printStackTrace();
        }
    }

    public void actionButtonDeleteAction(){
        tableViewActions.getItems().remove(tableViewActions.getSelectionModel().getSelectedItem());
    }

    public void actionButtonSelectKey(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SelectKeyWindow.fxml"));
            Pane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            SelectKeyWindowController selectKeyWindowController = loader.getController();
            selectKeyWindowController.myInitialize(this, stage, selectedIndex < 3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSelectedMouseButton(boolean selectedMouseButton) {
        this.selectedMouseButton = selectedMouseButton;
    }

    public void setMouseButton(MouseButton mouseButton) {
        this.mouseButton = mouseButton;
        keyCode = null;
        textFieldSelectedKey.setText(mouseButton.name());
    }

    public void setKeyCode(KeyCode keyCode) {
        this.keyCode = keyCode;
        mouseButton = null;
        textFieldSelectedKey.setText(keyCode.getName());
    }

    public void actionButtonStart(){
        comboBoxActionType.setDisable(true);
        buttonPoint1.setDisable(true);
        buttonPoint2.setDisable(true);
        buttonSelectKey.setDisable(true);
        buttonAddAction.setDisable(true);
        buttonDeleteAction.setDisable(true);
        buttonStart.setDisable(true);
        buttonStop.setDisable(false);
    }

    public void actionButtonStop(){
        comboBoxActionType.setDisable(false);
        buttonPoint1.setDisable(false);
        buttonPoint2.setDisable(false);
        buttonSelectKey.setDisable(false);
        buttonAddAction.setDisable(false);
        buttonDeleteAction.setDisable(false);
        buttonStart.setDisable(false);
        buttonStop.setDisable(true);
    }
}
