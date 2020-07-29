package site.yl.json.token;

public class LeftSquareToken extends Token{

    public LeftSquareToken(Coordinate coordinate){
        this("[", coordinate);
    }

    private LeftSquareToken(String tokenValue,Coordinate coordinate) {
        super(tokenValue,coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.LEFT_SQUARE;
    }
}
