package gui.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Kernel;
import model.map.MapConstructor;
import util.PlayerUtil;

/**
 * Created by Ethan on 16/6/20.
 */
public class MapViewController {
    public void drawMap(GraphicsContext gc) {
        String[][] mapShape = MapConstructor.constructDetailedMap();
        // Map and Construction
        for (int i = 0; i < mapShape.length; i++) {
            for (int j = 0; j < mapShape[0].length; j++) {
                switch (mapShape[i][j]) {
                    case "　":
                        break;
                    case "银":
                        gc.drawImage(new Image("res/Bank.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "卡":
                        gc.drawImage(new Image("res/BonusCard.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "券":
                        gc.drawImage(new Image("res/BonusTicket.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "道":
                        gc.drawImage(new Image("res/CardShop.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "空":
                        break;
                    case "彩":
                        gc.drawImage(new Image("res/Lottery.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "新":
                        gc.drawImage(new Image("res/News.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "医":
                        gc.drawImage(new Image("res/Hospital.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "地":
                        gc.drawImage(new Image("res/HouseLv0.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "1":
                        gc.drawImage(new Image("res/HouseLv1.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "2":
                        gc.drawImage(new Image("res/HouseLv2.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "3":
                        gc.drawImage(new Image("res/HouseLv3.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "4":
                        gc.drawImage(new Image("res/HouseLv4.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "5":
                        gc.drawImage(new Image("res/HouseLv5.png"), j * 50, i * 50, 50, 50);
                        break;
                    case "6":
                        gc.drawImage(new Image("res/HouseLv6.png"), j * 50, i * 50, 50, 50);
                        break;
                }
                if (!mapShape[i][j].equals("　")) {
                    gc.strokeRect(j * 50, i * 50, 50, 50);
                }
            }
        }

        // Player
        Kernel.getInstance().getPlayers().stream().forEach(e ->
                gc.fillOval(PlayerUtil.getPlayerLoc(e).getX() * 50 + 4, PlayerUtil.getPlayerLoc(e).getY() * 50 + 4, 6, 6));
    }
}
