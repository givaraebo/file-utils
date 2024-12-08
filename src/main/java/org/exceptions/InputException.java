package org.exceptions;

import org.console.utils.Color;

public class InputException extends OperationException {
    public InputException(String message, String recommendation) {
        super("\n"+message + "\n" + Color.CYAN.getCode()+"[Recommendation]" + recommendation +"" + Color.CYAN.getCode()+"\n"+Color.RESET.getCode());
    }

    public InputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputException(Throwable cause) {
        super(cause);
    }

}
