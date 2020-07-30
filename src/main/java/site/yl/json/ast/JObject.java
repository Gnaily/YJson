package site.yl.json.ast;

import java.util.LinkedHashMap;

public class JObject  extends LinkedHashMap<String,JValue> implements JValue {

	@Override
	public JValueType getType() {
		return JValueType.JObject;
	}

	@Override
	public boolean is(JValueType jValueType) {
		return getType()==jValueType;
	}

}
