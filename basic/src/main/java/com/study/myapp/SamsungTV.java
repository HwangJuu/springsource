package com.study.myapp;

public class SamsungTV implements TV {
	
	//private SonySpeaker speaker;  //null 값.
								  // 사용하려면 초기화 작업이 필요
								  //초기화(생성자, setter 메소드) => DI 와 연결
	//스피커 교체
	//private AppleSpeaker speaker;
	private Speaker speaker; 
	
	
	public SamsungTV() { //samsung TV deualt 생성자
		
	}
	
	//초기화 = 생성자
	public SamsungTV(Speaker speaker) { //default 생성자가 없어짐.
		super();
		this.speaker = speaker;
	}
	
	//초기화 = setter
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV 전원 On");
	}


	@Override
	public void powerOff() {
		System.out.println("SamsungTV 전원 Off");
	}

	@Override
	public void volumeUp() {
		//System.out.println("SamsungTV Volume Up");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		//System.out.println("SamsungTV Volume Down");
		speaker.volumeDown();
	}

}
