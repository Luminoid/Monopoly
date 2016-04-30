package action.request;

/**
 * Created by Ethan on 16/4/30.
 */
public abstract class YesOrNoRequest implements Request<Boolean> {
    private String questionStr;
    private boolean answer;

    public String getQuestionStr() {
        return questionStr;
    }

    public void setQuestionStr(String questionStr) {
        this.questionStr = questionStr;
        action();
    }

    @Override
    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
