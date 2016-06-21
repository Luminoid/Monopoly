package model.map;

import model.spot.EstateSpot;
import model.spot.SimpleSpotFactory;
import model.spot.Spot;
import model.spot.SpotType;

import java.util.*;

/**
 * Created by Ethan on 16/4/28.
 */
public class MapGenerator {
    private static RegMap map = new RegMap();
    private static ArrayList<Integer> xCoordinate = new ArrayList<>();
    private static ArrayList<Integer> yCoordinate = new ArrayList<>();

    public static void increase(ArrayList<Integer> arrayList) {
        arrayList.add(arrayList.get(arrayList.size() - 1) + 1);
        arrayList.add(arrayList.get(arrayList.size() - 1) + 1);
    }

    public static void decrease(ArrayList<Integer> arrayList) {
        arrayList.add(arrayList.get(arrayList.size() - 1) - 1);
        arrayList.add(arrayList.get(arrayList.size() - 1) - 1);
    }

    public static void maintain(ArrayList<Integer> arrayList) {
        arrayList.add(arrayList.get(arrayList.size() - 1));
        arrayList.add(arrayList.get(arrayList.size() - 1));
    }

    public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public static void generateLocation() {
        int upwardCapacity = 0;
        int downwardCapacity = 2;
        xCoordinate.add(0);
        xCoordinate.add(1);
        xCoordinate.add(2);
        yCoordinate.add(0);
        yCoordinate.add(0);
        yCoordinate.add(0);

        int orientation = 0; // 1: horizontal 2: upward 3: downward
        while (xCoordinate.get(xCoordinate.size() - 1) < 9) {
            if (downwardCapacity > 0 && upwardCapacity > 0) {
                int randomNum = getRandomNumber();
                if (randomNum >= 0 && randomNum < 40 && orientation != 2) {
                    maintain(xCoordinate);
                    increase(yCoordinate);
                    downwardCapacity -= 1;
                    upwardCapacity += 1;
                    orientation = 3;
                } else if (randomNum >= 40 && randomNum < 60 && orientation != 3) {
                    maintain(xCoordinate);
                    decrease(yCoordinate);
                    upwardCapacity -= 1;
                    orientation = 2;
                } else {
                    increase(xCoordinate);
                    maintain(yCoordinate);
                    orientation = 1;
                }
            } else if (downwardCapacity > 0 && upwardCapacity == 0) {
                int randomNum = getRandomNumber();
                if (randomNum >= 0 && randomNum < 50 && orientation != 2) {
                    maintain(xCoordinate);
                    increase(yCoordinate);
                    downwardCapacity -= 1;
                    upwardCapacity += 1;
                    orientation = 3;
                } else {
                    increase(xCoordinate);
                    maintain(yCoordinate);
                    orientation = 1;
                }
            } else if (downwardCapacity == 0 && upwardCapacity > 0) {
                int randomNum = getRandomNumber();
                if (randomNum >= 0 && randomNum < 50 && orientation != 3) {
                    maintain(xCoordinate);
                    decrease(yCoordinate);
                    upwardCapacity -= 1;
                    orientation = 2;
                } else {
                    increase(xCoordinate);
                    maintain(yCoordinate);
                    orientation = 1;
                }
            } else {
                increase(xCoordinate);
                maintain(yCoordinate);
                orientation = 1;
            }
        }

        for (int i = yCoordinate.get(yCoordinate.size() - 1); i < 9; i++) {
            xCoordinate.add(xCoordinate.get(xCoordinate.size() - 1));
            yCoordinate.add(yCoordinate.get(yCoordinate.size() - 1) + 1);
        }

        orientation = 3;
        upwardCapacity = 2;
        downwardCapacity = 0;
        while (xCoordinate.get(xCoordinate.size() - 1) > 0) {
            if (upwardCapacity > 0 && downwardCapacity > 0) {
                int randomNum = getRandomNumber();
                if (randomNum >= 0 && randomNum < 50 && orientation != 3) {
                    maintain(xCoordinate);
                    decrease(yCoordinate);
                    upwardCapacity -= 1;
                    downwardCapacity += 1;
                    orientation = 2;
                } else if (randomNum >= 50 && randomNum < 75 && orientation != 2) {
                    maintain(xCoordinate);
                    increase(yCoordinate);
                    downwardCapacity -= 1;
                    orientation = 3;
                } else {
                    decrease(xCoordinate);
                    maintain(yCoordinate);
                    orientation = 1;
                }
            } else if (upwardCapacity > 0 && downwardCapacity == 0) {
                int randomNum = getRandomNumber();
                if (randomNum >= 0 && randomNum < 30 && orientation != 3) {
                    maintain(xCoordinate);
                    decrease(yCoordinate);
                    upwardCapacity -= 1;
                    downwardCapacity += 1;
                    orientation = 2;
                } else {
                    decrease(xCoordinate);
                    maintain(yCoordinate);
                    orientation = 1;
                }
            } else if (upwardCapacity == 0 && downwardCapacity > 0 && orientation != 2) {
                int randomNum = getRandomNumber();
                if (randomNum >= 0 && randomNum < 30) {
                    maintain(xCoordinate);
                    increase(yCoordinate);
                    downwardCapacity -= 1;
                    orientation = 3;
                } else {
                    decrease(xCoordinate);
                    maintain(yCoordinate);
                    orientation = 1;
                }
            } else {
                decrease(xCoordinate);
                maintain(yCoordinate);
                orientation = 1;
            }
        }

        for (int i = yCoordinate.get(yCoordinate.size() - 1) - 1; i > 0; i--) {
            xCoordinate.add(xCoordinate.get(xCoordinate.size() - 1));
            yCoordinate.add(yCoordinate.get(yCoordinate.size() - 1) - 1);
        }
    }

    public static void generateMap() {
        // Spot type and location
        int size = xCoordinate.size();
        List<SpotType> spotTypeList = new ArrayList<>();
        for (SpotType spotType : SpotType.values()) {
            if (spotType != SpotType.EstateSpot) {
                spotTypeList.add(spotType);
            }
        }
        Collections.shuffle(spotTypeList);
        for (int i = 0; i < size; i++) {
            Spot spot;
            if (i % 5 == 1 && !spotTypeList.isEmpty()) {
                spot = SimpleSpotFactory.createSpot(spotTypeList.get(0));
                spotTypeList.remove(0);
            } else {
                spot = SimpleSpotFactory.createSpot(SpotType.EstateSpot);
            }
            map.spotLoc.put(spot, new SpotLoc(xCoordinate.get(i), yCoordinate.get(i)));
            map.addSpot(spot);
        }
        // Spot name
        String[] spotLocNameArr = {"阿拉巴马", "阿拉斯加", "阿利桑那", "阿肯色", "加利福尼亚", "科罗拉多", "康涅狄格", "特拉华",
                "佛罗里达", "乔治亚", "夏威夷", "爱达荷", "伊利诺斯", "印第安纳", "爱荷华", "堪萨斯", "肯塔基", "路易斯安那",
                "缅因", "马里兰", "马萨诸塞", "密歇根", "明尼苏达", "密西西比", "密苏里", "蒙大拿", "内布拉斯加", "内华达",
                "新罕布什尔", "新泽西", "新墨西哥", "纽约", "北卡罗来纳", "北达科他", "俄亥俄", "俄克拉荷马", "俄勒冈", "宾夕法尼亚",
                "罗得岛", "南卡罗来纳", "南达科他", "田纳西", "得克萨斯", "犹他", "佛蒙特", "弗吉尼亚", "华盛顿", "西弗吉尼亚",
                "威斯康辛", "怀俄明",
                "阿尔伯塔省", "不列颠哥伦比亚省", "马尼托巴省", "纽芬兰与拉布拉多省", "新不伦瑞克省", "新斯科舍省",
                "安大略省", "爱德华王子岛省", "萨斯喀彻温省", "努纳武特地区", "西北地区", "育空地区",
                "新南威尔士州", "昆士兰州", "南澳大利亚州", "塔斯马尼亚", "维多利亚州", "西澳大利亚州", "澳大利亚首都领地", "北领地",
                "巴登—符腾堡州", "巴伐利亚州", "柏林市", "勃兰登堡州", "不来梅市", "汉堡市", "黑森州", "梅克伦堡—前波莫瑞州",
                "下萨克森州", "北莱茵—威斯特法伦州", "莱茵兰—普法尔茨州", "萨尔州", "萨克森州", "萨克森—安哈特州",
                "石勒苏益格—荷尔斯泰因州", "图林根州",
                "伦敦", "罗马", "巴黎", "马德里", "柏林", "伯尔尼", "莫斯科", "斯底哥尔摩", "布鲁塞尔", "哥本哈根", "索非亚",
                "明斯克", "瓦杜兹", "维也纳", "布拉格", "华沙", "萨拉热窝"};
        List<String> spotNameList = new ArrayList<>(Arrays.asList(spotLocNameArr));
        map.getSpotList().stream().forEach(e -> {
            e.setLocName(spotNameList.get(0));
            spotNameList.remove(0);
        });
        // Spot street
        String[] spotStreetNameArr = {"亚松森", "罗索", "渥太华", "巴西利亚", "布宜诺斯艾利斯", "利马", "特古西加尔巴",
                "加拉加斯", "太子港", "基多", "圣菲波哥大", "圣萨尔瓦多", "金斯敦", "圣约翰", "圣地亚哥", "蒙得维的亚",
                "帕拉马里博", "哈瓦那", "布里奇顿", "贝尔莫潘", "马那瓜", "卡斯特里", "圣何塞", "拿骚", "乔治敦", "西班牙港",
                "巴拿马城", "危地马拉城", "拉巴斯", "圣多明各", "圣乔治", "墨西哥城"};
        for (int i = 2; i < map.getSpotList().size() - 5; i += 5) {
            ((EstateSpot) (map.getSpotList().get(i))).setStreetName(spotStreetNameArr[(i - 2) / 5]);
            ((EstateSpot) (map.getSpotList().get(i + 1))).setStreetName(spotStreetNameArr[(i - 2) / 5]);
            ((EstateSpot) (map.getSpotList().get(i + 2))).setStreetName(spotStreetNameArr[(i - 2) / 5]);
            ((EstateSpot) (map.getSpotList().get(i + 3))).setStreetName(spotStreetNameArr[(i - 2) / 5]);
        }
    }

    public static RegMap generate() {
        generateLocation();
        generateMap();
        map.setWidth(xCoordinate.stream().max((e1, e2) -> (e1 - e2)).get() + 1);
        map.setHeight(yCoordinate.stream().max((e1, e2) -> (e1 - e2)).get() + 1);
        return map;
    }
}
