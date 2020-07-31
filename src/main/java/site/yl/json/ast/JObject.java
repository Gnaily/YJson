package site.yl.json.ast;

import java.util.LinkedHashMap;

public class JObject  extends LinkedHashMap<String,JValue> implements JValue {

	public JObject(){
		super();
	}


	@Override
	public JValueType getType() {
		return JValueType.JObject;
	}


	@Override
	public boolean is(JValueType jValueType) {
		return getType()==jValueType;
	}


	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}


	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
