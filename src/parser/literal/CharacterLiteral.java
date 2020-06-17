package parser.literal;

public class CharacterLiteral extends Literal<Character> {
    private Character value;

    public CharacterLiteral(Character value) {
        this.value = value;
    }

    @Override
    public LiteralType getLiteralType() {
        return LiteralType.CHARACTER_LITERAL;
    }

    @Override
    public Character getValue() {
        return this.value;
    }
}
