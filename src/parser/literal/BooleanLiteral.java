package parser.literal;

public class BooleanLiteral extends Literal<Boolean> {

    private boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    @Override
    public LiteralType getLiteralType() {
        return LiteralType.BOOLEAN_LITERAL;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }
}
