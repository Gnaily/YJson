package site.yl.json.token;

public class NullToken extends Token{

    public NullToken( Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.NULL;
    }

    @Override
    public String toString() {
        return "NullToken{" +
                "tokenValue=" + "null" +
                '}';
    }
}
