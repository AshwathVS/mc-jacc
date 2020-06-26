package parser;

import core.Type;

public enum DataType {

    INTEGER(4),
    DOUBLE(8),
    CHARACTER(1),
    STRING(20),
    BOOLEAN(1),
    UNDEFINED(null),;

    private Integer byteCount;

    DataType(Integer byteCount) {
        this.byteCount = byteCount;
    }

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

    public static Type[] getDataTypeOperators() {
        return new Type[] { Type.INTEGER, Type.DOUBLE, Type.CHARACTER, Type.STRING, Type.BOOLEAN};
    }

    public Integer getByteCount() {
        return this.byteCount;
    }
}
