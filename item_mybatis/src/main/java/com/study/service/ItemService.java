package com.study.service;

import java.util.List;

import com.study.dto.ItemDTO;

public interface ItemService {
	//CRUD 호출
	public boolean insertItem(ItemDTO insertDto);
	public ItemDTO selectItem(int num);
	public boolean deleteItem(int num);
	public boolean updateItem(int num, String psize, int price);
	public List<ItemDTO> selectAllItem();
	

}
