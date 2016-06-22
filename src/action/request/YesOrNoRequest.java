package action.request;

import javafx.application.Platform;

import java.util.concurrent.Semaphore;

/**
 * Created by Ethan on 16/4/30.
 */
public abstract class YesOrNoRequest implements Request<Boolean> {
    private String questionStr;
    private boolean answer;
    private Semaphore lock;
    public String getQuestionStr() {
        return questionStr;
    }

    public void setQuestionStr(String questionStr) {
        this.questionStr = questionStr;
        try {
            lock = new Semaphore(1);
            lock.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(()->action());
    }

    @Override
    public Boolean getAnswer() {
        try {
            lock.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean retValue =  answer;
        lock.release();
        return retValue;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
        lock.release();
    }
}
