package purcio.purcio.common.exception;

public class EmailIsAlreadyExist extends RuntimeException{

    public EmailIsAlreadyExist() {
        super();
    }

    public EmailIsAlreadyExist(String message) {
        super(message);
    }

    public EmailIsAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailIsAlreadyExist(Throwable cause) {
        super(cause);
    }
}
