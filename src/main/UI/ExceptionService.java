package main.UI;

public class ExceptionService extends Exception{

    public ExceptionService() {
    }

    public ExceptionService(String message) {
        super(message);
    }

    public ExceptionService(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionService(Throwable cause) {
        super(cause);
    }

}
