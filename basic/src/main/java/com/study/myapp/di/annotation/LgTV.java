package com.study.myapp.di.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("lg")
public class LgTV implements TV {
	
	@Autowired
	@Qualifier("sony")
	private Speaker speaker; //speaker 객체 AppleSpeaker, SonySpeaker

	@Override
	public void powerOn() {
		System.out.println("LgTV 전원 On");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV 전원 Off");
	}

	@Override
	public void volumeUp() {
		//System.out.println("LgTV Volume Up");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		//System.out.println("LgTV Volume Down");
		speaker.volumeDown();
	}

}
