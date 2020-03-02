package data;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.awt.*;

public class Action {
    private Robot robot;

    private int actionType;
    private MouseButton mouseButton;
    private KeyCode keyCode;
    private String type;
    private int firstUse;
    private Point pointStart;
    private Point pointEnd;
    private String periodAndBuffer;
    private int period;
    private int buffer;
    private int repetitions;
    private int timeToNextAction;

    public Action(int actionType, MouseButton mouseButton, int firstUse, Point pointStart, Point pointEnd, int period, int buffer, int repetitions) {
        try{
            robot = new Robot();
        } catch(Exception e){
            e.printStackTrace();
        }

        this.actionType = actionType;
        this.mouseButton = mouseButton;
        this.type = "Mysz: " + mouseButton.name();
        this.firstUse = firstUse;
        this.pointStart = pointStart;
        this.pointEnd = pointEnd;
        this.period = period;
        this.buffer = buffer;
        this.periodAndBuffer = period + " + " + buffer;
        this.repetitions = repetitions;
        timeToNextAction = buffer;

        //createActionBasedOnType();
    }

    public Action(int actionType, KeyCode keyCode, int firstUse, Point pointStart, Point pointEnd, int period, int buffer, int repetitions) {
        try{
            robot = new Robot();
        } catch(Exception e){
            e.printStackTrace();
        }

        this.actionType = actionType;
        this.keyCode = keyCode;
        this.type = "Klawisz: " + keyCode;
        this.firstUse = firstUse;
        this.pointStart = pointStart;
        this.pointEnd = pointEnd;
        this.period = period;
        this.buffer = buffer;
        this.periodAndBuffer = period + " + " + buffer;
        this.repetitions = repetitions;
        timeToNextAction = buffer;
    }

    public void lifeCycle(){
        try {
            long reps = repetitions > 0 ? repetitions : 9000000000000000000L;
            Robot robot = new Robot();

            Thread.sleep(buffer);

            while (reps > 0) {
                reps--;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    public void setMouseButton(MouseButton mouseButton) {
        this.mouseButton = mouseButton;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFirstUse() {
        return firstUse;
    }

    public void setFirstUse(int firstUse) {
        this.firstUse = firstUse;
    }

    public Point getPointStart() {
        return pointStart;
    }

    public void setPointStart(Point pointStart) {
        this.pointStart = pointStart;
    }

    public Point getPointEnd() {
        return pointEnd;
    }

    public void setPointEnd(Point pointEnd) {
        this.pointEnd = pointEnd;
    }

    public String getPeriodAndBuffer() {
        return periodAndBuffer;
    }

    public void setPeriodAndBuffer(String periodAndBuffer) {
        this.periodAndBuffer = periodAndBuffer;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getBuffer() {
        return buffer;
    }

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getTimeToNextAction() {
        return timeToNextAction;
    }

    public void setTimeToNextAction(int timeToNextAction) {
        this.timeToNextAction = timeToNextAction;
    }

    @Override
    public String toString() {
        return "Action{" +
                "mouseButton=" + mouseButton +
                ", keyCode=" + keyCode +
                ", type='" + type + '\'' +
                ", firstUse=" + firstUse +
                ", pointStart=" + pointStart +
                ", pointEnd=" + pointEnd +
                ", periodAndBuffer='" + periodAndBuffer + '\'' +
                ", period=" + period +
                ", buffer=" + buffer +
                ", repetitions=" + repetitions +
                ", timeToNextAction=" + timeToNextAction +
                '}';
    }
}
