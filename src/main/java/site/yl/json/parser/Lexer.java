package site.yl.json.parser;


import site.yl.json.JsonParseException;
import site.yl.json.token.*;

public class Lexer {

    private int line;
    private int col;
    private  int cursor;
    private String text;
    private final  int textLen;

    private static final char BLANK = ' ';

    private static final char NEW_LINE = '\n';


    public  Lexer(String text){
        this.text = text;
        this.textLen = text.length();

        this.line = 1;
        this.col = 1;
        this.cursor = 0;
    }


    public boolean hasNext(){
        skip();
        return cursor() < textLen;
    }


    public Token nextToken() throws JsonParseException {

        int cur =  cursor();
        char ch = text.charAt(cur);
        int line = this.line;
        int col = this.col;
        if(ch == '{'){
            return  new LeftBraceToken(new Coordinate(line, col));
        } else if(ch == '}') {
            return  new RightBraceToken(new Coordinate(line, col));
        } else if(ch == '[') {
            return  new LeftSquareToken(new Coordinate(line, col));
        } else if(ch == ']') {
            return  new RightSquareToken(new Coordinate(line, col));
        } else if(ch == ',') {
            return  new CommaToken(new Coordinate(line, col));
        } else if(ch == ':') {
            return  new ColonToken(new Coordinate(line, col));
        } else if(ch == 'n') {
            if (cur+3 < textLen){
                String nullStr = text.substring(cur, cur+4);
                if("null".equals(nullStr) ){
                    return new NullToken(new Coordinate(line, col));
                }
            }
            throw  new JsonParseException(parseFailMessage(line, col));
        } else if(ch == '-' || isNumber(ch)){
            if(cur+2< textLen && text.substring(cur,cur+3).equals("-00")){
                throw  new JsonParseException(parseFailMessage(line, col));
            }
            if(cur+1< textLen && text.substring(cur,cur+2).equals("00")){
                throw  new JsonParseException(parseFailMessage(line, col));
            }
            char newCh;
            int newCur;
            boolean haveDot = false;
            int dotNumberLen = 0;
            while ( (newCur = forward()) < textLen ){
                newCh = text.charAt(newCur);

                if( isNumber(newCh) ){
                    //skip
                    if(haveDot){
                        dotNumberLen = dotNumberLen + 1;
                    }
                }else if( '.' == newCh ) {
                    if(!haveDot){
                        haveDot = true;
                    }else {
                        throw  new JsonParseException(parseFailMessage(line, col));
                    }
                }else if( ' ' == newCh || '\r' == newCh || '\n' == newCh ){
                    return new NumberToken(text.substring(cur, newCur+1), dotNumberLen, new Coordinate(line, col));
                }else {
                    throw  new RuntimeException("xx");
                }
            }
            if(cur + 1 == newCur){
                throw  new JsonParseException(parseFailMessage(line, col));
            }else {
                return new NumberToken(text.substring(cur, newCur), dotNumberLen, new Coordinate(line, col));
            }
        }else if(ch  == '"'){
            int newCur;
            while ( (newCur = forward()) < textLen ){
                char newCh = text.charAt(newCur);
                if(newCh == '"' ){
                    if('\\' == text.charAt(newCur-1)){
                        return new StringToken(text.substring(cur, newCur+1), new Coordinate(line, col));
                    }
                }
            }
            throw  new JsonParseException(parseFailMessage(line, col));
        }else if('t' == ch ){
            if (cur+3 < textLen){
                String trueStr = text.substring(cur, cur+4);
                if("true".equals(trueStr) ){
                    return new BoolToken("true", new Coordinate(line, col));
                }
            }
            throw  new JsonParseException(parseFailMessage(line, col));
        }else if( ch == 'f'){
            if (cur+3 < textLen){
                String falseStr = text.substring(cur, cur+4);
                if("false".equals(falseStr) ){
                    return new BoolToken("false", new Coordinate(line, col));
                }
            }
            throw  new JsonParseException(parseFailMessage(line, col));
        }else{
            throw  new JsonParseException(parseFailMessage(line, col));
        }

    }


    private String parseFailMessage(int line, int col){
        int to = cursor + 5;
        return "invalid json value ("
                +text.substring(col-1, to<textLen ? to:textLen)
                +") at line "+line+",column "+col;
    }


    private boolean isNumber(char ch){
        if( ch>=48 && ch<=58 ){
            return true;
        }else {
            return false;
        }
    }


    private void skip() {
        int last = textLen - 1 ;
        while (cursor() < last){
            char ch;
            while ( (ch = text.charAt(forward())) == BLANK){
                ;
            }

            if(ch == NEW_LINE){
                newLineStart();
            }else {
                return;
            }
        }
    }


    private void newLineStart() {
        line = line + 1;
        col = 1;
    }


    private int forward() {
        return forward(1);
    }


    private int forward(int k) {
        cursor = cursor + k;
        col = col + k;
        return cursor;
    }


    private int cursor(){
        return cursor;
    }

}
