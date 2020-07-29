package site.yl.json.token;

public class NumberToken extends Token {
    private int dotNumber;
    public NumberToken(String tokenValue, int dotNumber, Coordinate coordinate) {
        super(tokenValue,coordinate);
        this.dotNumber = dotNumber;
    }

    @Override
    protected TokenType getType() {
        return TokenType.NUMBER;
    }
}
