import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonCheckerTest {
    JsonChecker jsonChecker;
    @Test
    public void noPolicyNameKeyTest() {
        jsonChecker = new JsonChecker("src/test/resources/noPolicyNameKey.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void noPolicyDocumentKeyTest() {
        jsonChecker = new JsonChecker("src/test/resources/noPolicyDocument.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void noStatementKeyTest() {
        jsonChecker = new JsonChecker("src/test/resources/noStatementKey.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void noVersionKeyTest() {
        jsonChecker = new JsonChecker("src/test/resources/noVersionKey.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void noResourceKeyTest() {
        jsonChecker = new JsonChecker("src/test/resources/noResourceKey.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
    @Test
    public void resourcesContainsAsteriskTest() {
        jsonChecker = new JsonChecker("src/test/resources/resourceContainsAsterisk.json");
        assertFalse(jsonChecker.resourcesContainsAsterisk());
    }
    @Test
    public void resourcesContainsNoAsteriskTest() {
        jsonChecker = new JsonChecker("src/test/resources/resourceContainsNoAsterisk.json");
        assertTrue(jsonChecker.resourcesContainsAsterisk());
    }
    @Test
    public void twoAsterisksInResourceTest() {
        jsonChecker = new JsonChecker("src/test/resources/twoAsterisksInResource.json");
        assertTrue(jsonChecker.resourcesContainsAsterisk());
    }
    @Test
    public void additionalInvalidKeyInStatementTest() {
        jsonChecker = new JsonChecker("src/test/resources/additionalInvalidKeyInStatement.json");
        assertThrows(IllegalArgumentException.class, () -> jsonChecker.isJsonValid());
    }
}
