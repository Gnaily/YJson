package site.yl.json.ast;

public final class JNull implements JValue {

   public  JNull(){
   }


   @Override
   public JValueType getType() {
      return JValueType.JNULL;
   }

   @Override
   public boolean is(JValueType jValueType) {
      return getType() == jValueType;
   }

}
