package site.yl.json.token;

public class RightBraceToken extends Token {

    public RightBraceToken(Coordinate coordinate){
        super(coordinate);
    }


    @Override
    public TokenType getType() {
        return TokenType.RIGHT_BRACE;
    }

    @Override
    public String toString() {
        return "RightBraceToken{" +
                "tokenValue=" + "}" +
                '}';
    }
}
