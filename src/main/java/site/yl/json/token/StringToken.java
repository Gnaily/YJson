package site.yl.json.token;

public class StringToken extends Token {

    public StringToken(String tokenValue, Coordinate coordinate) {
        super(tokenValue,coordinate);
    }

    @Override
    public TokenType getType() {
        return TokenType.STRING;
    }


}
