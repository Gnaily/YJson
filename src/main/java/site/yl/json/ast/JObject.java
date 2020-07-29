package site.yl.json.ast;

import java.util.ArrayList;
import java.util.List;

public class JObject implements JValue {

  private List<JPair> pairList;

  public JObject() {
    this.pairList = new ArrayList<>();
  }

  public JObject(List<JPair> pairList) {
    this.pairList = pairList;
  }

  public void add(JPair pair){
    pairList.add(pair);
  }


}
