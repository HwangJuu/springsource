package com.study.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskTest {
	
	//스프링에서 스케쥴러 사용할 때
	//cron : cron은 유닉스 계열 컴퓨터 운영 체제의 시간 기반 잡 스케줄러
	//@Scheduled(cron="") : CronTab에서의 설정 방식과 같게 사용
	// " * * * * * " : 초, 분, 시, 일, 월, 요일, 연도 순서로 늘어남. 
	// " 0 0 * * * * " : 매일 매시 정각
	//@Scheduled(fixedDelay="") : 이전에 실행된 Task의 종료 시간으로부터 정의된 시간만큼 지난 후 task 실행
	//@Scheduled(fixedRate="") : 이전에 실행된 Task의 시작 시간으로부터 정의된 시간만큼 지난 후 task 실행
	//시간 단위는 밀리세컨드
	
//	@Scheduled(cron="0 * * * * *") // 매분 0초마다 실행
//	public void schedulerTest() {
//		System.out.println("매 분 1초마다 스케쥴링...");
//	}
//	@Scheduled(fixedDelay = 10000) // 10000밀리세컨즈 == 10초
//	public void schedulerTest2() {
//		System.out.println("10초마다 스케쥴링...");
//	}

}
