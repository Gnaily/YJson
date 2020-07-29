package site.yl.json.token;

public class NullToken extends Token{

    public NullToken( Coordinate coordinate) {
        this("null", coordinate);
    }

    private NullToken(String tokenValue, Coordinate coordinate) {
        super(tokenValue, coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.NULL;
    }
}
