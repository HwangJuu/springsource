package lambda;

public class StringConcatEx {

	public static void main(String[] args) {
//		StringConcat sc = new StringConcatImpl();
//		sc.makeString("Hello", "Interface");

		//람다식 구현
		StringConcat sc = (s1,s2) -> System.out.println(s1+", "+s2);
		sc.makeString("Hello", "world");
	}

}
