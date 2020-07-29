package site.yl.json.ast;

import java.util.ArrayList;
import java.util.List;

public class JArray  implements JValue{

  private List<JValue> jValueList;

  public JArray(){
    this.jValueList = new ArrayList<>();
  }

  public JArray(List<JValue> jValueList){
    this.jValueList = jValueList;
  }

  public void add(JValue jValue){
    jValueList.add(jValue);
  }

  public boolean isEmpty() {
     return jValueList.isEmpty();
  }
}
