package site.yl.json.parser;


import org.junit.Assert;
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
		assertEquals(bool.getValue() , true);
	}


	@Test
	public void test2() throws JsonParseException {
		JValue value = JsonParser.parse(" false ");
		assertTrue(value.is(JValueType.JBOOL));
		JBool bool = TypeUtil.down(value);
		assertEquals(bool.getValue() , false);
	}


	@Test
	public void test3() throws JsonParseException {
		JValue value = JsonParser.parse(" 123 ");
		assertTrue(value.is(JValueType.JNUMBER));
		JNumber number = TypeUtil.down(value);
		assertEquals(number.intValue(), 123);
	}


	@Test
	public void test4() throws JsonParseException {
		JValue value = JsonParser.parse(" -123 ");
		assertTrue(value.is(JValueType.JNUMBER));
		JNumber number = TypeUtil.down(value);
		assertEquals(number.intValue() , -123);
	}


	@Test
	public void test5() throws JsonParseException {
		String json = " {\"key1\": null, \"key2\": true,  \"key3\": false, \"key4\": 1234567890123, \"key5\": \"hello world\" " +
				", \"key6\": [null, true, false, 100 ,-100,  \"string\", {\"key01\": 9999}] } ";
		System.out.println(json);
		JValue value = JsonParser.parse(json);
		assertTrue(value.is(JValueType.JObject));
		JObject object = TypeUtil.down(value);

		assertEquals(object.get("key1"),JNull.get() );
		assertEquals(((JBool)object.get("key2")).getValue(), true);
		assertEquals(((JBool)object.get("key3")).getValue(),false);
		assertEquals(((JNumber)object.get("key4")).longValue() , 1234567890123L);
		assertEquals(((JString)object.get("key5")).getValue() ,"hello world");
		JArray jArray = TypeUtil.down(object.get("key6"));
		assertEquals(jArray.get(0) ,JNull.get());
		assertEquals(((JBool)jArray.get(1)).getValue() ,true);
		assertEquals(((JBool)jArray.get(2)).getValue() ,false);
		assertEquals(((JNumber)jArray.get(3)).intValue(),100);
		assertEquals(((JNumber)jArray.get(4)).intValue() ,-100);
		assertEquals(((JString)jArray.get(5)).getValue() ,"string");
		JObject o = TypeUtil.down(jArray.get(6));
		JNumber n = TypeUtil.down(o.get("key01"));
		assertEquals(n.intValue() ,9999);
	}
}