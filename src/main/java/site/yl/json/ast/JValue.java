package site.yl.json.ast;

public interface JValue {

	JValueType getType();

	boolean is(JValueType jValueType);

}
