package utils;

import io.restassured.response.Response;

import static org.testng.Assert.*;

public class ValidationUtils    {

    public static void validateValuePrefix(String value, String expectedPrefix) {
        assertTrue(value.startsWith(expectedPrefix), value + " does not start with " + expectedPrefix);
    }

    public static void validateIdenticalValues(Response response1, Response response2, String jsonPath1, String jsonPath2) {
        String value1 = response1.jsonPath().getString(jsonPath1);
        String value2 = response2.jsonPath().getString(jsonPath2);
        assertEquals(value1, value2, "Values should be identical");
    }

    public static void validateNonIdenticalValues(Response response1, Response response2, String jsonPath1, String jsonPath2) {
        String value1 = response1.jsonPath().getString(jsonPath1);
        String value2 = response2.jsonPath().getString(jsonPath2);
        assertNotEquals(value1, value2, "Values should not be identical");
    }
}
