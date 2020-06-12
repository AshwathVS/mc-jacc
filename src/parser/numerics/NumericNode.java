package parser.numerics;

public class NumericNode extends NumericExpressionNode {
    private Number value;

    public NumericNode(Number value) {
        super(null, null, null);
        this.value = value;
    }

    @Override
    public Number evaluate() {
        return this.value;
    }
}
