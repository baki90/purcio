package purcio.purcio.common.exception;

public class NotFoundUserException extends RuntimeException{

    public NotFoundUserException() {
        super("존재하지 않는 사용자입니다.");
    }

    public NotFoundUserException(String message) {
        super(message);
    }

    public NotFoundUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundUserException(Throwable cause) {
        super(cause);
    }
}
