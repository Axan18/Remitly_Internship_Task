/**
 * Exception thrown when there is an error parsing JSON.
 */
public class JsonParsingException extends RuntimeException {
    public JsonParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}