package site.yl.json.ast;

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

}
