package lambda;


/* 함수형 프로그래밍
    화살표 함수같은 
    람다식(8버전부터 추가됨)
	메소드를 하나의 식으로 표현한 개념
	메소드를 람다식으로 표현하면 메소드의 이름과 값이 없어지므로,
	람다식을 익명함수라고도 함.
 */

public class Lambda1 {
	
//	int max(int a, int b) {
//		return a > b? a:b;
//	}
	
	//람다식
//	(int a, int b) -> {return a > b? a:b};
//	(int a, int b) -> a > b? a:b;
//	(a,b) -> a > b? a:b;
	
	void print(String name, int i) {
		System.out.println(name + "=" +i);
	}
	
	//람다식
//	(String name, int i) -> {System.out.println(name + "=" +i);}
//	(String name, int i) -> System.out.println(name + "=" +i);
//	(name, i) -> System.out.println(name + "=" +i);
	
	//인자가 없는
	int roll() {
		return (int)(Math.random()*6);
	}
	
	//람다식
//	() -> {return (int)(Math.random()*6);}
//	() -> (int)(Math.random()*6);
	
	//인자 하나받는거
	int square(int x) {
		return x*x;
	}
	
	//람다식
//	(int x) -> {return x*x;} ==> 하나라서 중괄호 생략가능
//	(x) -> x*x;
//	x -> x*x; ==> 변수가 하나면 괄호 생략 가능
	

}
