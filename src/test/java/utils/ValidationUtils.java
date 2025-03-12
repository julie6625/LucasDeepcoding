package utils;

import static org.testng.Assert.*;

public class ValidationUtils    {

    public static void validateValuePrefix(String value, String expectedPrefix) {
        assertTrue(value.startsWith(expectedPrefix), value + " does not start with " + expectedPrefix);
    }
}
