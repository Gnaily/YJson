package site.yl.json.parser;


import junit.framework.Assert;
import org.junit.Test;
import site.yl.json.JsonParseException;
import site.yl.json.ast.*;
import site.yl.json.util.TypeUtil;

public class JsonParserTest  extends Assert {

	@Test
	public void test0() throws JsonParseException {
		JValue value = JsonParser.parse("null");
		assertTrue(value.is(JValueType.JNULL));
	}

	@Test
	public void test1() throws JsonParseException {
		JValue value = JsonParser.parse("true");
		assertTrue(value.is(JValueType.JBOOL));
		JBool bool = TypeUtil.down(value);
		assertTrue(bool.getValue() == true);
	}

	@Test
	public void test2() throws JsonParseException {
		JValue value = JsonParser.parse(" false ");
		assertTrue(value.is(JValueType.JBOOL));
		JBool bool = TypeUtil.down(value);
		assertTrue(bool.getValue() == false);
	}

	@Test
	public void test3() throws JsonParseException {
		JValue value = JsonParser.parse(" 123 ");
		assertTrue(value.is(JValueType.JNUMBER));
		JNumber number = TypeUtil.down(value);
		assertTrue(number.intValue() == 123);
	}


	@Test
	public void test4() throws JsonParseException {
		JValue value = JsonParser.parse(" -123 ");
		assertTrue(value.is(JValueType.JNUMBER));
		JNumber number = TypeUtil.down(value);
		assertTrue(number.intValue() == -123);
	}

	@Test
	public void test5() throws JsonParseException {
		JValue value = JsonParser.parse(" {\"key1\": null, \"key2\": true,  \"key3\": false, \"key4\": 1234567890123, \"key5\": \"hello world\" } ");
		assertTrue(value.is(JValueType.JObject));
		JObject object = TypeUtil.down(value);
		assertTrue(object.get("key1") == null);
		assertTrue(((JBool)object.get("key2")).getValue() == true);
		assertTrue(((JBool)object.get("key3")).getValue() == false);
		assertTrue(((JNumber)object.get("key4")).longValue() == 1234567890123L);
		assertTrue(((JString)object.get("key5")).getValue() .equals("hello world"));
	}
}