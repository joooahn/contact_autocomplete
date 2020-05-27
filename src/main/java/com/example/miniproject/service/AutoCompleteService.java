package com.example.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.miniproject.mapper.ItemMapper;
import com.example.miniproject.model.PublicData;

@Service
public class AutoCompleteService {
	private ItemMapper itemMapper;

	@Autowired
	public AutoCompleteService(ItemMapper itemMapper) {
		this.itemMapper = itemMapper;
	}

	public List<PublicData> getRecentItemService(int senderID, String searchText) {
		return itemMapper.getRecentItem(senderID, searchText);
	}
}
