package site.yl.json.ast;

public class JNumber implements JValue {

    private Number number;
    public JNumber(Number number){
      this.number = number;
    }
}
