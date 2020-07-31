package site.yl.json.ast;

import java.util.Objects;

public final class JBool implements JValue{

	private boolean bool;

	public JBool(boolean bool){
		this.bool = bool;
	}

	public boolean getValue(){
		return bool;
	}

	@Override
	public JValueType getType() {
		return JValueType.JBOOL;
	}

	@Override
	public boolean is(JValueType jValueType) {
		return getType()==jValueType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		JBool jBool = (JBool) o;
		return bool == jBool.bool;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bool);
	}
}
