package parser;

import java.util.*;

public class ParseException extends Exception {

    private boolean isList = false;

    private Set<String> errorMessages;

    public ParseException(String errorMessage) {
        super(errorMessage);
    }

    public ParseException(Collection<String> errorMessages) {
        isList = true;
        this.errorMessages = new HashSet<>(10);
        for(String error : errorMessages) {
            this.errorMessages.add(error);
        }
    }

    public Set<String> getErrorMessages() {
        return errorMessages;
    }

    @Override
    public String getMessage() {
        if(isList) {
            StringBuilder stringBuilder = new StringBuilder();
            for(String error : errorMessages) {
                if(error != null) {
                    stringBuilder.append(error);
                    stringBuilder.append(";\n");
                }
            }
            return stringBuilder.toString();
        } else {
            return super.getMessage();
        }
    }

    @Override
    public String getLocalizedMessage() {
        return this.getMessage();
    }

    public boolean multipleErrors() {
        return this.isList;
    }
}
