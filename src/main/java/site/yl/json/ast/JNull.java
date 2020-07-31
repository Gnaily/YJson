package site.yl.json.ast;

import java.util.Objects;

public final class JNull implements JValue {

   private   JNull(){
   }

   private static class InstanceHolder{
      static final JNull jNull = new JNull();
   }

   public static JNull get(){
      return InstanceHolder.jNull;
   }

   @Override
   public JValueType getType() {
      return JValueType.JNULL;
   }

   @Override
   public boolean is(JValueType jValueType) {
      return getType() == jValueType;
   }

   @Override
   public boolean equals(Object obj) {
      return (obj instanceof JNull) && getType() == ((JNull)obj).getType();
   }

   @Override
   public int hashCode() {
      return Objects.hashCode( JValueType.JNULL);
   }
}
