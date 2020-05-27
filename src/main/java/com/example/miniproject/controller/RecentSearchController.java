package com.example.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniproject.model.PublicData;
import com.example.miniproject.service.AutoCompleteService;

@CrossOrigin("*")
@RestController
public class RecentSearchController {

	private final AutoCompleteService autoCompleteService;

	@Autowired
	public RecentSearchController(AutoCompleteService autoCompleteService) {
		this.autoCompleteService = autoCompleteService;
	}

	@RequestMapping(value = "/getRecentItem")
	public List<PublicData> getRecentItem(@RequestParam(name = "senderID") int senderID, @RequestParam(name = "searchText") String searchText) {
		return autoCompleteService.getRecentItemService(senderID, searchText);
	}
}
