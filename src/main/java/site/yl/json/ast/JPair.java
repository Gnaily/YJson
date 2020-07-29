package site.yl.json.ast;

public class JPair implements JValue {

	private String key;

	private JValue value;

	public JPair(String key, JValue value){
		this.key = key;
		this.value = value;
	}
}
