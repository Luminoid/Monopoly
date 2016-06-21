import model.Kernel;
import tui.init.GameBegin;
import tui.init.UserInfoSetting;

/**
 * Created by Ethan on 16/4/28.
 */
public class Main {
    public static void main(String[] args) {
        GameBegin.gameBegin();
        UserInfoSetting.userInfoInput();
        Kernel.getInstance().addCards();
        Kernel.getInstance().circulate();
    }
}
