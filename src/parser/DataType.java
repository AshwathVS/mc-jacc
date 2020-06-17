package parser;

import core.Type;

public enum DataType {
    INTEGER,
    DOUBLE,
    CHARACTER,
    STRING,
    BOOLEAN,;

    public static DataType getDataType(Type type) {
        if(null != type) {
            switch (type) {
                case INTEGER:
                    return INTEGER;
                case DOUBLE:
                    return DOUBLE;
                case CHARACTER:
                    return CHARACTER;
                case STRING:
                    return STRING;
                case BOOLEAN:
                    return BOOLEAN;
            }
        }
        return null;
    }
}
