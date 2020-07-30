package site.yl.json.parser;


import site.yl.json.JsonParseException;
import site.yl.json.ast.*;
import site.yl.json.token.*;
import site.yl.json.util.TypeUtil;

import java.math.BigDecimal;

public abstract class JsonParser {

  public static JValue parse(String text) throws JsonParseException{
    Lexer lexer = new Lexer(text);
    return  parse(lexer);
  }

  private static JValue parse(Lexer lexer) throws JsonParseException{

    if (lexer.hasNext()){
      Token token = lexer.nextToken();

      if(token.match(TokenType.NULL)) {

        return new JNull();

      } else if(token.match(TokenType.BOOL)) {

        return parseBool(TypeUtil.down(token));

      } else if(token.match(TokenType.NUMBER)) {

        return parseNumber(TypeUtil.down(token));

      } else if(token.match(TokenType.STRING)) {

        return parseString(TypeUtil.down(token));

      } else if(token.match(TokenType.LEFT_BRACE)) {

        return parseObject(lexer);

      } else if( token.match(TokenType.LEFT_SQUARE) ){

        return parseArray(lexer);

      } else {
        throw  new IllegalStateException("lexer contains unexpected token");
      }
    }
    return new  JString("");
  }


  private static JArray parseArray(Lexer lexer) throws JsonParseException{
    JArray jArray = new JArray();
    while (lexer.hasNext()){
      Token token = lexer.nextToken();
      if(token.match(TokenType.RIGHT_SQUARE)){
        return jArray;
      }else {

        if(!jArray.isEmpty()){
          eat(TokenType.COMMA, lexer,"missing symbol ,");
        }
        JValue  value = parse(lexer);

        jArray.add(value);
      }
    }
    throw  new JsonParseException("parse fail at line " + lexer.getLine()+ " column " + lexer.getColumn()+","+"missing symbol ]");
  }


  private static JObject parseObject(Lexer lexer) throws JsonParseException{
    JObject jObject = new JObject();//{a:{},b:{}}
    while (lexer.hasNext()){
      Token token = lexer.nextToken();
      if(token.match(TokenType.RIGHT_BRACE)){
        return jObject;
      }else {
        String key = matchKey(token);
        eat(TokenType.COLON, lexer,"missing symbol :");
        JValue  value = parse(lexer);// null , number ,string, bool,  object ,array
        jObject.put(key, value);
      }
    }
    throw  new JsonParseException("parse fail at line " + lexer.getLine()+ " column " + lexer.getColumn()+","+"missing symbol }");
  }


  private static JString parseString(StringToken token) {
    return new JString(token.getValue());
  }


  private static JNumber parseNumber(NumberToken token) {

    return new JNumber(new BigDecimal(token.getTokenValue()));
  }


  private static JBool parseBool(BoolToken token) {
    return new JBool(Boolean.valueOf(token.getValue()));
  }

  private static void eat(TokenType tokenType, Lexer lexer,String failMessage) throws JsonParseException{
    if (lexer.hasNext()) {
      Token token = lexer.nextToken();
      if(token.match(tokenType)){
        return;
      }
    }
    throw  new JsonParseException("parse fail at line " + lexer.getLine()+ " column " + lexer.getColumn()+","+failMessage);
  }


  private static String matchKey(Token token) throws JsonParseException{
    if(token.match(TokenType.STRING)){
      return ((StringToken)token).getValue();
    }else {
      Coordinate coo = token.getCoordinate();
      throw  new JsonParseException("parse fail at line " + coo.getLine()+ " column " + coo.getColumn() + "," + token.toString());
    }
  }
}
