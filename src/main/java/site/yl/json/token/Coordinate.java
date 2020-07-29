package site.yl.json.token;

public class Coordinate {
    private  int line;
    private  int column;

    public Coordinate(int line,int column){
        this.line=line;
        this.column=column;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }
}
