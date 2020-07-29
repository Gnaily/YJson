package site.yl.json.token;

public class ColonToken extends Token {

    public ColonToken(Coordinate coordinate){
        super(coordinate);
    }


    @Override
    protected TokenType getType() {
        return TokenType.COLON;
    }

    @Override
    public String toString() {
        return "ColonToken{" +
                "tokenValue=" + ":" +
                '}';
    }
}
