package site.yl.json.token;

public class LeftBraceToken extends Token {

    public LeftBraceToken(Coordinate coordinate){
        this("{",coordinate);
    }

    private LeftBraceToken(String tokenValue,Coordinate coordinate) {
        super(tokenValue,coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.LEFT_BRACE;
    }
}
