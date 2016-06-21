package tui.impl;

import action.request.YesOrNoRequest;

import java.util.Scanner;

/**
 * Created by Ethan on 16/4/30.
 */
public class YesOrNoImpl extends YesOrNoRequest {

    @Override
    public void action() {
        while (true) {
            System.out.println(getQuestionStr());
            System.out.println("输入Y或N");
            System.out.print(">> ");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine().trim();
            if (s.equals("Y")) {
                setAnswer(true);
                break;
            } else if (s.equals("N")) {
                setAnswer(false);
                break;
            } else {
                System.out.println("输入错误！请重新输入：");
            }
        }
    }
}
