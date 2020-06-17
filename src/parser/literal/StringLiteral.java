package parser.literal;

public class StringLiteral extends Literal<String> {
    private String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public LiteralType getLiteralType() {
        return LiteralType.STRING_LITERAL;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
