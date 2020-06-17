package parser.literal;

public class IntegerLiteral extends Literal<Integer> {
    private Integer value;

    public IntegerLiteral(Integer value) {
        this.value = value;
    }

    @Override
    public LiteralType getLiteralType() {
        return LiteralType.INTEGER_LITERAL;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
