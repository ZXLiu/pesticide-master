package pesticide.server.exception;

public class AuthException extends RuntimeException {

    private int code;

    public AuthException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
