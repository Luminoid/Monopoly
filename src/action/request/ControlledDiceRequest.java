package action.request;

/**
 * Created by Ethan on 16/4/30.
 */
public abstract class ControlledDiceRequest implements Request<Integer> {
    private String questionStr;
    private int answer;

    @Override
    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getQuestionStr() {
        return questionStr;
    }

    public void setQuestionStr(String questionStr) {
        this.questionStr = questionStr;
        action();
    }
}
