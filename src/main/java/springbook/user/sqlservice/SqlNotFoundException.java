package springbook.user.sqlservice;

public class SqlNotFoundException extends RuntimeException {
    public SqlNotFoundException() {
        super();
    }

    public SqlNotFoundException(String message) {
        super(message);
    }

    public SqlNotFoundException(Throwable cause) {
        super(cause);
    }

    public SqlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
