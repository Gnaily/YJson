package site.yl.json.token;

public class LeftBraceToken extends Token {

    public LeftBraceToken(Coordinate coordinate){
        super(coordinate);
    }


    @Override
    protected TokenType getType() {
        return TokenType.LEFT_BRACE;
    }


    @Override
    public String toString() {
        return "LeftBraceToken{" +
                "tokenValue=" + "{" +
                '}';
    }
}
