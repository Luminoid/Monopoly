package action.request;

import javafx.application.Platform;

import java.util.concurrent.Semaphore;

/**
 * Created by Ethan on 16/4/30.
 */
public abstract class IntRangeRequestWithLock implements Request<Integer> {
    private String questionStr;
    private int answer;
    protected int floor;
    protected int ceiling;
    private Semaphore lock;

    @Override
    public Integer getAnswer() {
        try {
            lock.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer retValue = answer;
        lock.release();
        return retValue;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
        lock.release();
    }

    public String getQuestionStr() {
        return questionStr;
    }

    public void setQuestionStr(String questionStr) {
        this.questionStr = questionStr;
        lock = new Semaphore(0);
        try {
            lock.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> action());
    }

    public IntRangeRequestWithLock setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public IntRangeRequestWithLock setCeiling(int ceiling) {
        this.ceiling = ceiling;
        return this;
    }
}
