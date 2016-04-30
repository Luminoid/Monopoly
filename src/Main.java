import model.Kernal;
import view.init.GameBegin;
import view.init.UserInfoSetting;

/**
 * Created by Ethan on 16/4/28.
 */
public class Main {
    public static void main(String[] args) {
        GameBegin.gameBegin();
        UserInfoSetting.userInfoInput();
        Kernal.getInstance().addCards();
        Kernal.getInstance().circulate();
    }
}