package parser.common;

public class IdNode {

    private String id;

    private Integer symbolTableReference;

    public IdNode(String id) {
        if(null == id) throw new NullPointerException("Id needs to have a value.");
        this.id = id;
        this.symbolTableReference = symbolTableReference;
    }

    public String getId() {
        return this.id;
    }

    public Integer getSymbolTableReference() {
        return symbolTableReference;
    }
}
