package site.yl.json.token;

public class RightSquareToken extends Token {

    public RightSquareToken(Coordinate coordinate){
        super(coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.RIGHT_SQUARE;
    }

    @Override
    public String toString() {
        return "RightSquareToken{" +
                "tokenValue=" + "]" +
                '}';
    }
}
