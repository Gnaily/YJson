package site.yl.json.ast;

import site.yl.json.util.TypeUtil;

import java.math.BigDecimal;
import java.util.Objects;

public final  class JNumber extends Number implements JValue {

    private Number number;


    public JNumber(byte number){
      this.number = number;
    }

    public JNumber(Byte number){
        this.number = number;
    }

    public JNumber(short number){
        this.number = number;
    }

    public JNumber(Short number){
        this.number = number;
    }

    public JNumber(int number){
        this.number = number;
    }

    public JNumber(Integer number){
        this.number = number;
    }

    public JNumber(float number){
        this.number = number;
    }

    public JNumber(Float number){
        this.number = number;
    }

    public JNumber(double number){
        this.number = number;
    }

    public JNumber(Double number){
        this.number = number;
    }

    public JNumber(BigDecimal number){
        this.number = number;
    }

    @Override
    public int intValue() {
        return number.intValue();
    }

    @Override
    public long longValue() {
        return number.longValue();
    }

    @Override
    public float floatValue() {
        return number.floatValue();
    }

    @Override
    public double doubleValue() {
        return number.doubleValue();
    }

    public BigDecimal decimalValue() {
        if(number instanceof BigDecimal){
            return TypeUtil.down(number);
        }else if(number instanceof Integer) {
           return BigDecimal.valueOf(intValue());
        }else if(number instanceof Float) {
            return BigDecimal.valueOf(floatValue());
        }else if(number instanceof Double) {
            return BigDecimal.valueOf(doubleValue());
        }else if(number instanceof Long) {
            return BigDecimal.valueOf(longValue());
        }else if(number instanceof Short) {
            return BigDecimal.valueOf(shortValue());
        }else if(number instanceof Byte) {
            return BigDecimal.valueOf(byteValue());
        }else {
            throw  new RuntimeException("error number" + number);
        }
    }

    @Override
    public JValueType getType() {
        return JValueType.JNUMBER;
    }

    @Override
    public boolean is(JValueType jValueType) {
        return getType()==jValueType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JNumber jNumber = (JNumber) o;
        return Objects.equals(number, jNumber.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
