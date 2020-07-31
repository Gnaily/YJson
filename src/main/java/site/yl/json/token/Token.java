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

    public  Token (Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

   public abstract TokenType getType();

   public boolean match(TokenType tokenType){
       return getType() == tokenType;
   }
}
