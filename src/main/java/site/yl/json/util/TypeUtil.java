package site.yl.json.util;

public class TypeUtil {
	public static  <Super,Sub extends Super> Sub down(Super superType){
		return (Sub)superType;
	};
}
