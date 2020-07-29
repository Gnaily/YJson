package site.yl.json.token;

public class BoolToken extends Token {
    public BoolToken(String tokenValue, Coordinate coordinate) {
        super(tokenValue, coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.BOOL;
    }
}
