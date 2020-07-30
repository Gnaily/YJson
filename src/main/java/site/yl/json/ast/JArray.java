package site.yl.json.ast;

import java.util.ArrayList;

public class JArray extends ArrayList<JValue> implements JValue{

  @Override
  public JValueType getType() {
    return JValueType.JArray;
  }

  @Override
  public boolean is(JValueType jValueType) {
    return getType()==jValueType;
  }

}
