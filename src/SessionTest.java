import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void getDuplicateCitizen() {
        CitizenWrapper citizen = new CitizenWrapper("user");
        citizen.pesel = "96061204723";
        Session currSes = new Session(Util.status.MENU);
        ArrayList<CitizenWrapper> testList = new ArrayList<CitizenWrapper>();
        testList.add(citizen);
        currSes.sessionData.put("poznan", testList);
        currSes.currentCity = "poznan";
        assertEquals(citizen, currSes.getDuplicateCitizen(citizen));
    }
}