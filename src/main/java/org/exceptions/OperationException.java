package org.exceptions;

import org.console.utils.Color;

public class OperationException extends RuntimeException {
    public OperationException(String message, String recommendation) {
        super("\n"+message + "\n" + Color.CYAN.getCode()+"[Recommendation]" + recommendation +"" + Color.CYAN.getCode()+"\n"+Color.RESET.getCode());
    }

    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationException(Throwable cause) {
        super(cause);
    }

    public OperationException(String message) {
        super(message);
    }
}
