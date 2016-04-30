package view.impl;

import action.request.IntRangeRequest;
import view.util.TuiInput;

import java.util.Scanner;

/**
 * Created by Ethan on 16/4/30.
 */
public class IntRangeImpl extends IntRangeRequest {
    @Override
    public void action() {
        System.out.println(getQuestionStr());
        Scanner scanner = new Scanner(System.in);
        setAnswer(TuiInput.readInt(scanner, floor, ceiling));
    }
}
