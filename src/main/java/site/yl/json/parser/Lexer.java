package site.yl.json.parser;


import site.yl.json.JsonParseException;
import site.yl.json.token.*;

public class Lexer {

    private int line;
    private int col;
    private  int cursor;
    private String text;
    private final  int textLen;


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
        // delimiter
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
        }
        //null
        else if(ch == 'n') {
            if (cur+3 < textLen){
                String nullStr = text.substring(cur+1, cur+4);
                if("ull".equals(nullStr) ){
                    forward(3);
                    return new NullToken(new Coordinate(line, col));
                }
            }
            throw  new JsonParseException(parseFailMessage(line, col));
        }
        // bool
        else if('t' == ch ){
            if (cur+3 < textLen){
                String trueStr = text.substring(cur+1, cur+4);
                if("rue".equals(trueStr) ){
                    forward(3);
                    return new BoolToken(true, new Coordinate(line, col));
                }
            }
            throw  new JsonParseException(parseFailMessage(line, col));
        }else if( ch == 'f'){
            if (cur+3 < textLen){
                String falseStr = text.substring(cur+1, cur+4);
                if("alse".equals(falseStr) ){
                    forward(3);
                    return new BoolToken(false, new Coordinate(line, col));
                }
            }
            throw  new JsonParseException(parseFailMessage(line, col));
        }
        // number
        else if(ch == '-') {
            if(cur+1 >= textLen){
                throw  new JsonParseException(parseFailMessage(line, col));
            }

            if(cur+2< textLen && text.substring(cur+1, cur+3).equals("00")){
                throw  new JsonParseException(parseFailMessage(line, col));
            }

            return parseNumberToken(true, cur, line, col);
        }else if(isNumber(ch)){
            if(cur+1< textLen && text.substring(cur, cur+2).equals("00")){
                throw  new JsonParseException(parseFailMessage(line, col));
            }
            return parseNumberToken(false, cur, line, col);
        }
        //string
        else if(ch  == '"'){
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
        }else{
            throw  new JsonParseException(parseFailMessage(line, col));
        }

    }


    public int getLine() {
        return line;
    }

    public int getColumn() {
        return col;
    }

    private NumberToken parseNumberToken(boolean neg, int cur, int line, int col) throws JsonParseException {
        char newCh;
        int newCur;
        boolean haveDot = false;
        int intLen = neg ? 0:1;
        int floatLen = 0;
        while ( (newCur = forward()) < textLen ){
            newCh = text.charAt(newCur);

            if( isNumber(newCh) ){
                if(haveDot){
                    floatLen = floatLen + 1;
                }else {
                    intLen = intLen + 1;
                }
            }else if( '.' == newCh ) {
                if(!haveDot){
                    haveDot = true;
                }else {
                    throw  new JsonParseException(parseFailMessage(line, col));
                }
            }else if( ' ' == newCh || '\r' == newCh || '\n' == newCh || '\t' == newCh){
                return new NumberToken(text.substring(cur, newCur+1), intLen, floatLen, new Coordinate(line, col));
            }else {
                throw  new JsonParseException(parseFailMessage(line, col));
            }
        }
        return new NumberToken(text.substring(cur, newCur), intLen, floatLen, new Coordinate(line, col));
    }


    private String parseFailMessage(int line, int col){
        int to = cursor + 5;
        return "invalid json value ("
                + text.substring(col-1, to<textLen ? to : textLen)
                + ") at line "+line+",column "+col;
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
            while ( (ch = text.charAt(forward())) == ' '){
                ;
            }

            if(ch == '\n'){
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
