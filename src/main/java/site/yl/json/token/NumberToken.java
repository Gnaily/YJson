package site.yl.json.token;

public class NumberToken extends Token {
    private int floatLen;
    private int intLen;
    private String tokenValue;
    public NumberToken(String tokenValue,  int intLen, int floatLen, Coordinate coordinate) {
        super(coordinate);
        this.intLen = intLen;
        this.floatLen = floatLen;
        this.tokenValue = tokenValue;
    }

    @Override
    public TokenType getType() {
        return TokenType.NUMBER;
    }

    public int getIntLen() {
        return intLen;
    }

    public int getFloatLen() {
        return floatLen;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    @Override
    public String toString() {
        return "NumberToken{" +
                "tokenValue=" + tokenValue +
                '}';
    }
}
