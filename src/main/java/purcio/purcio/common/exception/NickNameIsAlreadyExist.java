package purcio.purcio.common.exception;

public class NickNameIsAlreadyExist extends RuntimeException{

    public NickNameIsAlreadyExist() {
    }

    public NickNameIsAlreadyExist(String message) {
        super(message);
    }

    public NickNameIsAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public NickNameIsAlreadyExist(Throwable cause) {
        super(cause);
    }
}
