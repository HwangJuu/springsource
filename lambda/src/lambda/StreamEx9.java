package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* 스트림 최종 연산
 	forEach()
 	collect() : 요소를 그룹화하거나 분할한 결과를 컬렉션에 담아 변환하는데 사용
 	count()
 	sum()
 	average()
 	max()
 	min()
 	findFirst()
 */
public class StreamEx9 {
	public static void main(String[] args) {
		
		List<Student> stuList = new ArrayList<Student>();
		
		stuList.add(new Student("홍길동", 70));
		stuList.add(new Student("송혜교", 75));
		stuList.add(new Student("김지원", 88));
		stuList.add(new Student("정우성", 92));
		stuList.add(new Student("송중기", 93));
		
		//학생들의 점수만 모아서 새로운 리스트 생성
		
		//원래 방식
		List<Integer> stuNum = new ArrayList<Integer>();
		for(Student stu:stuList) {
			stuNum.add(stu.getJumsu());
		}
		
		//stream 방식
		//리스트였던게 스트림 객체로 변환
//		Stream<Student> stream = stuList.stream();
		
		//점수만 모으고 싶음
		//n.getJumsu() : int형태
		//collect(Collectors.toList() ==> 리스트 구조로 바뀜
//		List<Integer> jumsu = stream.map(n -> n.getJumsu()).collect(Collectors.toList());
		
		//한번에 정리
		List<Integer> jumsu =stuList.stream().map(n -> n.getJumsu()).collect(Collectors.toList());

	}
}
