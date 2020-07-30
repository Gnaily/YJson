package site.yl.json.ast;

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

}
