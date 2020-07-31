package site.yl.json.token;

public class ColonToken extends Token {

    public ColonToken(Coordinate coordinate){
        super(coordinate);
    }


    @Override
    public TokenType getType() {
        return TokenType.COLON;
    }

    @Override
    public String toString() {
        return "ColonToken{" +
                "tokenValue=" + ":" +
                '}';
    }
}
