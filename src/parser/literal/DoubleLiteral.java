package parser.literal;

public class DoubleLiteral extends Literal<Double> {
    private Double value;

    public DoubleLiteral(Double value) {
        this.value = value;
    }

    @Override
    public LiteralType getLiteralType() {
        return LiteralType.DOUBLE_LITERAL;
    }

    @Override
    public Double getValue() {
        return this.value;
    }

}
