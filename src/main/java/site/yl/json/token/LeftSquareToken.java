package site.yl.json.token;

public class LeftSquareToken extends Token{

    public LeftSquareToken(Coordinate coordinate){
        super(coordinate);
    }


    @Override
    public TokenType getType() {
        return TokenType.LEFT_SQUARE;
    }


    @Override
    public String toString() {
        return "LeftSquareToken{" +
                "tokenValue=" + "[" +
                '}';
    }
}
