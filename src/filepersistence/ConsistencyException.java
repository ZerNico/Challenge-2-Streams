package filepersistence;

public class ConsistencyException extends Exception {
    public ConsistencyException() {
        super();
    }

    public ConsistencyException(String message) {
        super(message);
    }
    public ConsistencyException(String message, Exception e) {
        super(message, e);
    }
}