package site.yl.json.token;

public class ColonToken extends Token {

    public ColonToken(Coordinate coordinate){
        this(":",coordinate);
    }

    private ColonToken(String tokenValue,Coordinate coordinate) {
        super(tokenValue,coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.COLON;
    }

}
