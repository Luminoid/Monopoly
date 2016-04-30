package action.request;

/**
 * Created by Ethan on 16/4/30.
 */
public abstract class IntRangeRequest implements Request<Integer> {
    private String questionStr;
    private int answer;
    protected int floor;
    protected int ceiling;

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

    public IntRangeRequest setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public IntRangeRequest setCeiling(int ceiling) {
        this.ceiling = ceiling;
        return this;
    }
}
