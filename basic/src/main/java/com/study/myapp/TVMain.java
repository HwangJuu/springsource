package com.study.myapp;

public class TVMain {
	public static void main(String[] args) {
		//TV tv = new SamsungTV(); 생성자 만든 후엔 사용 불가
		//TV tv = new SamsungTV(new SonySpeaker());  //=> 주입 완료
		
		SamsungTV tv = new SamsungTV();
				
		//tv.setSpeaker(new SonySpeaker()); // 초기화 후 새로 생성
		
		tv.setSpeaker(new AppleSpeaker());
		
		tv.powerOn();
		tv.volumeUp();
		tv.powerOff();
		
//		String str = null;
//		System.out.println(str);
//		System.out.println(str.toString());
		
	}
}
