import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Main extends Application {
    public static void main(String[] args) throws InterruptedException {
        Robot robot1 = null;
        Robot robot2 = null;
        Robot robot3 = null;
        Robot robot4 = null;
        final Random random = new Random();
        try {
            robot1 = new Robot();
            robot2 = new Robot();
            robot3 = new Robot();
            robot4 = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        final Robot finalRobot1 = robot1;
        final Robot finalRobot2 = robot2;
        final Robot finalRobot3 = robot3;
        final Robot finalRobot4 = robot4;

        Thread.sleep(3000);

        new Thread(() -> {
            while(true) {
                //finalRobot1.mouseMove(2410, 775);
                finalRobot1.mouseMove(1770, 630);
                finalRobot1.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                finalRobot1.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                try {
                    Thread.sleep(120000 + random.nextInt(120000));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            //while(true) {
            while(true){
                //finalRobot2.mouseMove(1075, 1225);
                finalRobot2.mouseMove(1085, 870);
                finalRobot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                finalRobot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                try {
                    Thread.sleep(75000 + random.nextInt(75000));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
//
//        new Thread(() -> {
//            while(true) {
//                finalRobot3.keyPress(17);
//                finalRobot3.delay(100);
//                finalRobot3.keyPress(38);
//                finalRobot3.keyRelease(38);
//                finalRobot3.keyRelease(17);
//                try {
//                    Thread.sleep(20000 + random.nextInt(20000));
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//                finalRobot3.keyPress(17);
//                finalRobot3.delay(100);
//                finalRobot3.keyPress(40);
//                finalRobot3.keyRelease(40);
//                finalRobot3.keyRelease(17);
//                try {
//                    Thread.sleep(20000 + random.nextInt(20000));
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }).start();

//        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindowController.fxml"));
        Pane pane = loader.load();

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}