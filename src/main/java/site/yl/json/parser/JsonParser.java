package site.yl.json.parser;


import site.yl.json.JsonParseException;
import site.yl.json.ast.*;
import site.yl.json.token.*;
import site.yl.json.util.TypeUtil;

import java.math.BigDecimal;

public abstract class JsonParser {

  public static JValue parse(String text) throws JsonParseException{
    Lexer lexer = new Lexer(text);
    JValue value = parse(lexer);
    if (lexer.hasNext()) {
      Token next = lexer.nextToken();
      throw new JsonParseException(msg(ParseFail.INVALID_PART, next.getCoordinate()));
    }else {
      return  value;
    }
  }

  private static JValue parse(Lexer lexer) throws JsonParseException{

    if (lexer.hasNext()){
      Token token = lexer.nextToken();

      if(token.match(TokenType.NULL)) {

        return JNull.get();

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
    JArray jArray = new JArray();//[JValue,JValue,...,JValue]
    while (lexer.hasNext()){

      Token token = lexer.nextToken();
      JValue value = null;
      switch (token.getType()){
        case RIGHT_SQUARE:
          return jArray;

        case LEFT_BRACE:
          value = parseArray(lexer);
          break;

        case LEFT_SQUARE:
          value = parseObject(lexer);
          break;

        case NULL:
          value = JNull.get();
          break;

        case BOOL:
          value = parseBool(TypeUtil.down(token));
          break;

        case NUMBER:
          value = parseNumber(TypeUtil.down(token));
          break;

        case STRING:
          value = parseString(TypeUtil.down(token));
          break;

      }

      if(jArray.isEmpty()){
        if (value != null){
          jArray.add(value);
        }else {
          throw  new JsonParseException(msg(ParseFail.MISSING_VALUE, token.getCoordinate()));
        }
      }else {
        matchAndEat(TokenType.COMMA, token, ParseFail.MISSING_COMMA);
        JValue  jvalue = parse(lexer);
        jArray.add(jvalue);
      }
    }
    throw  new JsonParseException(msg(ParseFail.MISSING_RIGHT_SQUARE, lexer.getCoordinate()));
  }


  private static JObject parseObject(Lexer lexer) throws JsonParseException{
    JObject jObject = new JObject();//{"key1":JValue,"key2":JValue,...,"keyn":JValue}
    while (lexer.hasNext()){
      Token token = lexer.nextToken();

      if(token.match(TokenType.RIGHT_BRACE)){
        return jObject;
      }else {
        if(jObject.isEmpty()){
          String key = matchKey(token);
          matchAndEat(TokenType.COLON, lexer, ParseFail.MISSING_COLON);
          JValue  value = parse(lexer);// null , number ,string, bool,  object ,array
          jObject.put(key, value);
        }else {
          matchAndEat(TokenType.COMMA,  token, ParseFail.MISSING_COMMA);
          String key = matchKey(lexer, ParseFail.MISSING_KEY);
          matchAndEat(TokenType.COLON,  lexer, ParseFail.MISSING_COLON);
          JValue  value = parse(lexer);
          jObject.put(key, value);
        }
      }
    }
    throw  new JsonParseException(msg(ParseFail.MISSING_RIGHT_BRACE, lexer.getCoordinate()));
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

  private static void matchAndEat(TokenType tokenType, Lexer lexer, ParseFail fail) throws JsonParseException {
    if(lexer.hasNext()){
      matchAndEat(tokenType, lexer.nextToken(), fail);
    }else {
      Coordinate coo = lexer.getCoordinate();
      throw  new JsonParseException(msg(fail, coo));
    }
  }

  private static void matchAndEat(TokenType tokenType, Token token, ParseFail fail) throws JsonParseException{
      if(token.match(tokenType)){
        return;
      }else {
        Coordinate coo = token.getCoordinate();
        throw  new JsonParseException(msg(fail, coo));
      }
  }


  private static String matchKey(Token token) throws JsonParseException{
    if(token.match(TokenType.STRING)){
      return ((StringToken)token).getValue();
    }else {
      Coordinate coo = token.getCoordinate();
      throw  new JsonParseException(msg(ParseFail.KEY_MUST_STRING, coo));
    }
  }


  private static String matchKey(Lexer lexer, ParseFail fail) throws JsonParseException{

      if(lexer.hasNext()){
        return matchKey(lexer.nextToken());
      }else {
        Coordinate coo = lexer.getCoordinate();
        throw  new JsonParseException(msg(fail, coo));
      }
  }


  private static String msg(ParseFail parseFail, Coordinate coordinate){
    return new StringBuilder("parse fail at line ")
        .append(coordinate.getLine())
        .append(" column ")
        .append(coordinate.getColumn())
        .append(",")
        .append(parseFail.description)
        .toString();
  }


  enum ParseFail {
     MISSING_VALUE("missing value(null、bool、string、object or array)"),
     MISSING_KEY("missing object key"),
     KEY_MUST_STRING("key must be string"),
     MISSING_COMMA("missing symbol ,"),
     MISSING_COLON("missing symbol :"),
     MISSING_RIGHT_BRACE("missing symbol }"),
     MISSING_RIGHT_SQUARE("missing symbol ]"),
     INVALID_PART("invalid part")
    ;
     String description;
     ParseFail(String description){
        this.description = description;
     }
  }
}
