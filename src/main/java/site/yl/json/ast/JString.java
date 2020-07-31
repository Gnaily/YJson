package site.yl.json.ast;

import java.util.Objects;

public final  class JString  implements JValue{

  private String value;

  public JString(String value){
    this.value = value;
  }

  public String getValue(){
    return value;
  }

  @Override
  public JValueType getType() {
    return JValueType.JSTRING;
  }

  @Override
  public boolean is(JValueType jValueType) {
    return getType()==jValueType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JString jString = (JString) o;
    return Objects.equals(value, jString.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
