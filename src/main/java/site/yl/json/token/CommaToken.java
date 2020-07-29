package site.yl.json.token;

public class CommaToken extends Token {

    public CommaToken(Coordinate coordinate){
        this(",",coordinate);
    }

    private CommaToken(String tokenValue,Coordinate coordinate) {
        super(tokenValue,coordinate);
    }

    @Override
    protected TokenType getType() {
        return TokenType.COMMA;
    }

}
