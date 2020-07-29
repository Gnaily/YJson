package site.yl.json.token;

public class RightBraceToken extends Token {

    public RightBraceToken(Coordinate coordinate){
        this("}",coordinate);
    }

    private RightBraceToken(String tokenValue,Coordinate coordinate) {
        super(tokenValue,coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.RIGHT_BRACE;
    }
}
