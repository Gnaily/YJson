package site.yl.json.parser;


import org.junit.Assert;
import org.junit.Test;
import site.yl.json.JsonParseException;
import site.yl.json.token.BoolToken;
import site.yl.json.token.NumberToken;
import site.yl.json.token.Token;
import site.yl.json.token.TokenType;
import site.yl.json.util.TypeUtil;

public class LexerTest {

  @Test
  public void t1() throws JsonParseException {
    String  text = "null";
    Token token = new Lexer(text).nextToken();
    Assert.assertTrue(token.match(TokenType.NULL));
  }

  @Test
  public void t2() throws JsonParseException {
    String  text = "true";
    Token token = new Lexer(text).nextToken();

    Assert.assertTrue(token.toString(),token.match(TokenType.BOOL));
    BoolToken boolToken = TypeUtil.down(token);
    Assert.assertTrue(boolToken.getValue() == true);
  }

  @Test
  public void t3() throws JsonParseException {
    String  text = "false";
    Token token = new Lexer(text).nextToken();
    Assert.assertTrue(token.match(TokenType.BOOL));
    BoolToken boolToken = TypeUtil.down(token);
    Assert.assertTrue(boolToken.getValue() == false);
  }

  @Test
  public void t4() throws JsonParseException {
    String  text = "-123333";
    Token token = new Lexer(text).nextToken();
    Assert.assertTrue(token.match(TokenType.NUMBER));
    NumberToken numberToken = TypeUtil.down(token);
    Assert.assertTrue(text.equals(numberToken.getTokenValue()));
  }

  @Test
  public void t5() throws JsonParseException {
    String  text = "-123.333";
    Token token = new Lexer(text).nextToken();
    Assert.assertTrue(token.match(TokenType.NUMBER));
    NumberToken numberToken = TypeUtil.down(token);
    Assert.assertTrue(text.equals(numberToken.getTokenValue()));
  }

  @Test
  public void t6() throws JsonParseException {
    String  text = "-123..333";
    Token token = new Lexer(text).nextToken();
    Assert.assertTrue(token.match(TokenType.NUMBER));
    NumberToken numberToken = TypeUtil.down(token);
    Assert.assertTrue(text.equals(numberToken.getTokenValue()));
  }

  @Test
  public void t7() throws JsonParseException {
    String  text = "123.333";
    Token token = new Lexer(text).nextToken();
    Assert.assertTrue(token.match(TokenType.NUMBER));
    NumberToken numberToken = TypeUtil.down(token);
    Assert.assertTrue(text.equals(numberToken.getTokenValue()));
  }

  @Test
  public void t8() throws JsonParseException {
    String  text = "00123.333";
    Token token = new Lexer(text).nextToken();
    Assert.assertTrue(token.match(TokenType.NUMBER));
    NumberToken numberToken = TypeUtil.down(token);
    Assert.assertTrue(text.equals(numberToken.getTokenValue()));
  }

  @Test
  public void t9() throws JsonParseException {
    String  text = "-00123.333";
    Token token = new Lexer(text).nextToken();
    Assert.assertTrue(token.match(TokenType.NUMBER));
    NumberToken numberToken = TypeUtil.down(token);
    Assert.assertTrue(text.equals(numberToken.getTokenValue()));
  }


  @Test
  public void t10() throws JsonParseException {
    String  text = "x778true";
    Token token = new Lexer(text).nextToken();
    Assert.assertTrue(token.match(TokenType.STRING));
    NumberToken numberToken = TypeUtil.down(token);
    Assert.assertTrue(text.equals(numberToken.getTokenValue()));
  }


  @Test
  public void t11() throws JsonParseException {
    String  text = "\r\n null -1234 xyz true false True";
    Lexer lexer = new Lexer(text);

    while (lexer.hasNext()){
      Token token = lexer.nextToken();
      System.out.println(token.toString());
    }

  }


}