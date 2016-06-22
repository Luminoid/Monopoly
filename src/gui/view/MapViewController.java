package gui.view;

import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Kernel;
import model.map.MapConstructor;
import model.spot.EstateSpot;
import model.spot.Spot;
import util.PlayerUtil;

/**
 * Created by Ethan on 16/6/20.
 */
public class MapViewController {
    public void drawMap(Group mapView) {
        mapView.getChildren().clear();
        Spot[][] mapShape = MapConstructor.constructDetailedMap();
        // Map and Construction
        for (int i = 0; i < mapShape.length; i++) {
            for (int j = 0; j < mapShape[0].length; j++) {
                if (mapShape[i][j] != null) {
                    // Cell
                    Rectangle rectangle = new Rectangle();
                    rectangle.setLayoutX(j * 50);
                    rectangle.setLayoutY(i * 50);
                    rectangle.setWidth(50);
                    rectangle.setHeight(50);
                    rectangle.setFill(Color.TRANSPARENT);
                    rectangle.setStroke(Color.GRAY);
                    mapView.getChildren().add(rectangle);

                    // Construction
                    ImageView image = new ImageView();
                    switch (mapShape[i][j].getSpotType()) {
                        case BankSpot:
                            image = new ImageView("res/Bank.png");
                            break;
                        case BonusCardSpot:
                            image = new ImageView("res/BonusCard.png");
                            break;
                        case BonusTicketSpot:
                            image = new ImageView("res/BonusTicket.png");
                            break;
                        case CardShopSpot:
                            image = new ImageView("res/CardShop.png");
                            break;
                        case EmptySpot:
                            break;
                        case LotterySpot:
                            image = new ImageView("res/Lottery.png");
                            break;
                        case NewsSpot:
                            image = new ImageView("res/News.png");
                            break;
                        case HospitalSpot:
                            image = new ImageView("res/Hospital.png");
                            break;
                        case EstateSpot:
                            if (((EstateSpot) mapShape[i][j]).getOwner() == null) {
                                image = new ImageView("res/HouseLv0.png");
                            } else {
                                switch (((EstateSpot) mapShape[i][j]).getLevel()) {
                                    case 1:
                                        image = new ImageView("res/HouseLv1.png");
                                        break;
                                    case 2:
                                        image = new ImageView("res/HouseLv2.png");
                                        break;
                                    case 3:
                                        image = new ImageView("res/HouseLv3.png");
                                        break;
                                    case 4:
                                        image = new ImageView("res/HouseLv4.png");
                                        break;
                                    case 5:
                                        image = new ImageView("res/HouseLv5.png");
                                        break;
                                    case 6:
                                        image = new ImageView("res/HouseLv6.png");
                                        break;
                                }
                            }
                            break;
                    }
                    image.setLayoutX(j * 50);
                    image.setLayoutY(i * 50);
                    image.setFitWidth(50);
                    image.setFitHeight(50);
                    Tooltip t = new Tooltip(mapShape[i][j].getSpotInfoStr());
                    Tooltip.install(image, t);
                    mapView.getChildren().add(image);

                    // Block
                    if(mapShape[i][j].isBlocked()){
                        ImageView blockImage;
                        blockImage = new ImageView("res/Block.png");
                        blockImage.setLayoutX(j * 50);
                        blockImage.setLayoutY(i * 50);
                        blockImage.setFitWidth(50);
                        blockImage.setFitHeight(50);
                        mapView.getChildren().add(blockImage);
                    }
                }
            }
        }

        Kernel.getInstance().getPlayers().stream().forEach(e -> {
            Circle circle = new Circle();
            switch (e.getId()) {
                case 1:
                    circle.setFill(Color.rgb(244, 67, 54));
                    circle.setLayoutX(PlayerUtil.getPlayerLoc(e).getX() * 50 + 8);
                    break;
                case 2:
                    circle.setFill(Color.rgb(33, 150, 243));
                    circle.setLayoutX(PlayerUtil.getPlayerLoc(e).getX() * 50 + 16);
                    break;
                case 3:
                    circle.setFill(Color.rgb(255, 87, 34));
                    circle.setLayoutX(PlayerUtil.getPlayerLoc(e).getX() * 50 + 24);
                    break;
                case 4:
                    circle.setFill(Color.rgb(0, 150, 136));
                    circle.setLayoutX(PlayerUtil.getPlayerLoc(e).getX() * 50 + 32);
                    break;

            }
            circle.setLayoutY(PlayerUtil.getPlayerLoc(e).getY() * 50 + 8);
            circle.setRadius(4);
            mapView.getChildren().add(circle);
        });
    }
}
