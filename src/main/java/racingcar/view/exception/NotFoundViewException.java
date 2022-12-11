package racingcar.view.exception;

public class NotFoundViewException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "";

    public NotFoundViewException(Throwable throwable) {
        super(DEFAULT_MESSAGE, throwable);
    }
}
