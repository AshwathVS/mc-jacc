package parser.literal;

import core.Token;
import parser.ExpressionType;
import parser.RhsExpression;

public abstract class Literal<T> implements RhsExpression {

    public abstract LiteralType getLiteralType();

    public abstract T getValue();

    public static Literal parseTokenToLiteral(Token token) {
        switch (token.getType()) {
            case BOOLEAN_LITERAL: {
                return new BooleanLiteral(Boolean.parseBoolean(token.getValue()));
            }
            case CHARACTER_LITERAL: {
                return new CharacterLiteral(token.getValue().charAt(1));
            }
            case DOUBLE_LITERAL: {
                return new DoubleLiteral(Double.parseDouble(token.getValue()));
            }
            case INTEGER_LITERAL: {
                return new IntegerLiteral(Integer.parseInt(token.getValue()));
            }
        }
        return null;
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.LITERAL;
    }

}
