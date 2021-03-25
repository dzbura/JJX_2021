import javax.swing.plaf.MenuBarUI;
import java.util.Collection;
import java.util.Map;
import java.util.*;
import java.util.Set;

public class Util {
    public static enum status {MENU, EXIT, INPUT_NAME, INPUT_CITY, INPUT_PESEL, VIEW_ALL, SWITCH_CITIES}
    public static final Map<String, Util.status> inputToStatusMap;
    static {
        Map<String, Util.status> aMap = new HashMap<String, Util.status>();
        aMap.put("m", Util.status.MENU);
        aMap.put("q", status.EXIT);
        aMap.put("n", status.INPUT_NAME);
        aMap.put("c", status.INPUT_CITY);
        aMap.put("v", status.VIEW_ALL);
        aMap.put("s", status.SWITCH_CITIES);

        inputToStatusMap = aMap;
    }
    public static final Map<Integer, Integer> indexToWagaPeselMap;
    static {
        Map<Integer, Integer> aMap = new HashMap<Integer, Integer>();
        aMap.put(1, 1);
        aMap.put(2, 3);
        aMap.put(3, 7);
        aMap.put(4, 9);
        aMap.put(5, 1);
        aMap.put(6, 3);
        aMap.put(7, 7);
        aMap.put(8, 9);
        aMap.put(9, 1);
        aMap.put(10, 3);
        indexToWagaPeselMap = aMap;
    }
    public static String getPrettySessionData(Map<String, List<CitizenWrapper>> sessionData) {
        String resultString = "\n";
        for (String city : sessionData.keySet()) {
            resultString += "\nMiasto " + city + ":\n";
            for (CitizenWrapper cw : sessionData.get(city)) {
                resultString += cw.name + " (" + cw.pesel + ")";
            }
        }
        return resultString;
    }
}
