package site.yl.json.token;

public class StringToken extends Token {

    private String tokenValue;
    public StringToken(String tokenValue, Coordinate coordinate) {
        super(coordinate);
        this.tokenValue = tokenValue;
    }

    @Override
    public TokenType getType() {
        return TokenType.STRING;
    }

    public String getValue() {
        return tokenValue;
    }

    @Override
    public String toString() {
        return "StringToken{" +
                "tokenValue='" + tokenValue + '\'' +
                '}';
    }
}
