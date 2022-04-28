package com.study.service;

import com.study.dto.PersonDTO;

public interface PersonSerivce {
	//인서트작업
	public boolean insertPerson(String id, String name);
	public boolean updatePerson(String id, String name);
	public boolean deletePerson(String id);
	public PersonDTO selectPerson(String id);

}
