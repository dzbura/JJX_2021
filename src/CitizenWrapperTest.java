import static org.junit.jupiter.api.Assertions.*;

class CitizenWrapperTest {
    @org.junit.jupiter.api.Test
    void validatePeselWithCorrectValue() {
        CitizenWrapper citizen = new CitizenWrapper("user");
        citizen.pesel = "96061204723";
        assertEquals(true, citizen.validatePesel());
    }

    @org.junit.jupiter.api.Test
    void validatePeselWithInCorrectValue() {
        CitizenWrapper citizen = new CitizenWrapper("user");
        citizen.pesel = "12345678910";
        assertEquals(false, citizen.validatePesel());
    }
}