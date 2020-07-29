package site.yl.json.token;

/**
 * Json Token Representation
 *
 *      Json  Token is one of below:
 *
 *         -- number
 *         -- string
 *         -- bool,bool is one of below
 *            -- true
 *            -- false
 *         -- ,
 *         -- :
 *         -- [
 *         -- ]
 *         -- {
 *         -- }
 */

public abstract class Token {

    private Coordinate coordinate;
    private String content;
    public  Token (String content,Coordinate coordinate){
        this.content=content;
        this.coordinate=coordinate;
    }

    public String content(){
        return content;
    }


   protected abstract TokenType getType();

   public boolean match(TokenType tokenType){
       return getType() == tokenType;
   }
}
