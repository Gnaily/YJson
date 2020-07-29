package site.yl.json.token;

public class RightSquareToken extends Token {

    public RightSquareToken(Coordinate coordinate){
        this("]",coordinate);
    }

    private RightSquareToken(String tokenValue,Coordinate coordinate) {
        super(tokenValue,coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.RIGHT_SQUARE;
    }
}
