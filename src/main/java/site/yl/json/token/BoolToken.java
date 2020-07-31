package site.yl.json.token;

public class BoolToken extends Token {
    private boolean tokenValue;
    public BoolToken(boolean tokenValue, Coordinate coordinate) {
        super(coordinate);
        this.tokenValue = tokenValue;
    }

    @Override
    public TokenType getType() {
        return TokenType.BOOL;
    }

    public boolean getValue(){
        return tokenValue;
    }

    @Override
    public String toString() {
        return "BoolToken{" +
                "tokenValue=" + tokenValue +
                '}';
    }
}
