package site.yl.json.token;

public class CommaToken extends Token {

    public CommaToken(Coordinate coordinate){
        super(coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.COMMA;
    }

    @Override
    public String toString() {
        return "CommaToken{" +
                "tokenValue=" + ":" +
                '}';
    }
}
