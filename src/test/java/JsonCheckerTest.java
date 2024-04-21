import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonCheckerTest {
    JsonChecker jsonChecker = new JsonChecker();
    @Test
    public void noPolicyNameKeyTest() {
        jsonChecker.setJson("src/test/resources/noPolicyNameKey.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void noPolicyDocumentKeyTest() {
        jsonChecker.setJson("src/test/resources/noPolicyDocument.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void noStatementKeyTest() {
        jsonChecker.setJson("src/test/resources/noStatementKey.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void noVersionKeyTest() {
        jsonChecker.setJson("src/test/resources/noVersionKey.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void noResourceKeyTest() {
        jsonChecker.setJson("src/test/resources/noResourceKey.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void resourcesContainsAsteriskTest() {
        jsonChecker.setJson("src/test/resources/resourceContainsAsterisk.json");
        assertFalse(jsonChecker.noAsteriskInResources());
    }
    @Test
    public void resourcesContainsNoAsteriskTest() {
        jsonChecker.setJson("src/test/resources/resourceContainsNoAsterisk.json");
        assertTrue(jsonChecker.noAsteriskInResources());
    }
    @Test
    public void twoAsterisksInResourceTest() {
        jsonChecker.setJson("src/test/resources/twoAsterisksInResource.json");
        assertTrue(jsonChecker.noAsteriskInResources());
    }
    @Test
    public void additionalInvalidKeyInStatementTest() {
        jsonChecker.setJson("src/test/resources/additionalInvalidKeyInStatement.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void doubleStatementKeyTest() {
        jsonChecker.setJson("src/test/resources/doubleStatement.json");
        assertFalse(jsonChecker.noAsteriskInResources());
    }

}
