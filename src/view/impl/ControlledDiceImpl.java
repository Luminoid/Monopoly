package view.impl;

import action.request.ControlledDiceRequest;
import view.util.TuiInput;

import java.util.Scanner;

/**
 * Created by Ethan on 16/4/30.
 */
public class ControlledDiceImpl extends ControlledDiceRequest {
    @Override
    public void action() {
        System.out.println(getQuestionStr());
        Scanner scanner = new Scanner(System.in);
        setAnswer(TuiInput.readInt(scanner, 1, 6));
    }
}
