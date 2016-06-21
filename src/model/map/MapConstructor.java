package model.map;

import model.Kernel;
import model.spot.EstateSpot;
import model.spot.Spot;
import model.spot.SpotType;

/**
 * Created by Ethan on 16/6/20.
 */
public class MapConstructor {
    private static final String BLANK = "　";
    private static final String BANK_SPOT = "银";
    private static final String BONUS_CARD_SPOT = "卡";
    private static final String BONUS_TICKET_SPOT = "券";
    private static final String CARD_SHOP_SPOT = "道";
    private static final String EMPTY_SPOT = "空";
    private static final String LOTTERY_SPOT = "彩";
    private static final String NEWS_SPOT = "新";
    private static final String HOSPITAL_SPOT = "医";
    private static final String ESTATE_SPOT = "地①②③④";

    public static String getEstateOwner(EstateSpot spot) {
        String ret = "";
        if (spot.getOwner() == null) {
            ret += ESTATE_SPOT.charAt(0);
        } else {
            ret += ESTATE_SPOT.charAt(spot.getOwner().getId());
        }
        return ret;
    }

    public static String getSpotTag(Spot spot) {
        switch (spot.getSpotType()) {
            case BankSpot:
                return BANK_SPOT;
            case BonusCardSpot:
                return BONUS_CARD_SPOT;
            case BonusTicketSpot:
                return BONUS_TICKET_SPOT;
            case CardShopSpot:
                return CARD_SHOP_SPOT;
            case EmptySpot:
                return EMPTY_SPOT;
            case LotterySpot:
                return LOTTERY_SPOT;
            case NewsSpot:
                return NEWS_SPOT;
            case HospitalSpot:
                return HOSPITAL_SPOT;
            case EstateSpot:
                return getEstateOwner((EstateSpot) spot);
            default:
                return BLANK;
        }
    }

    public static String[][] constructMap() {
        RegMap map = (RegMap) Kernel.getInstance().getMap();
        String[][] mapShape = new String[map.getHeight()][map.getWidth()];
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++)
                mapShape[i][j] = BLANK;
        map.getSpotList().stream().forEach(e -> mapShape[map.spotLoc.get(e).getY()][map.spotLoc.get(e).getX()] = getSpotTag(e));
        return mapShape;
    }

    public static String[][] constructDetailedMap() {
        RegMap map = (RegMap) Kernel.getInstance().getMap();
        String[][] mapShape = new String[map.getHeight()][map.getWidth()];
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++)
                mapShape[i][j] = BLANK;
        map.getSpotList().stream().forEach(e -> {
            if (e.getSpotType() == SpotType.EstateSpot) {
                mapShape[map.spotLoc.get(e).getY()][map.spotLoc.get(e).getX()] = ((EstateSpot) e).getLevel() + "";
            } else {
                mapShape[map.spotLoc.get(e).getY()][map.spotLoc.get(e).getX()] = getSpotTag(e);
            }
        });
        return mapShape;
    }
}
