package com.example.miniproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniproject.model.PositionPriority;
import com.example.miniproject.model.SearchData;
import com.example.miniproject.model.exception.InvalidParameterException;
import com.example.miniproject.service.PositionPriorityService;
import com.example.miniproject.service.PublicDataService;

@RestController
@CrossOrigin("*")
@RequestMapping("contacts")
public class PublicDataController {

	private final PublicDataService publicDataService;
	private final PositionPriorityService positionPriorityService;

	public PublicDataController(PublicDataService publicDataService, PositionPriorityService positionPriorityService) {
		this.publicDataService = publicDataService;
		this.positionPriorityService = positionPriorityService;
	}

	@GetMapping("search")
	public List<SearchData> search(@RequestParam("searcherId") Long searcherId,
		@RequestParam("searchText") String searchText) {
		return publicDataService.search(searcherId, searchText);
	}

	@PutMapping("{id}/positionId/{positionId}")
	public void updateContactsPosition(@PathVariable("id") int id, @PathVariable("positionId") int positionId) {

		// DB에 positionID가 있는지 검사
		List<PositionPriority> positionPriorityList = positionPriorityService.retrievePosition();
		boolean isExists = false;

		for (int i = 0; i < positionPriorityList.size(); i++) {
			if (positionPriorityList.get(i).getId() == positionId) {
				isExists = true;
				break;
			}
		}

		if (isExists) {
			publicDataService.updateContactsPosition(id, positionId);
		} else {
			throw new InvalidParameterException("positionId not exists");
		}
	}

	@GetMapping("{id}/email")
	public String getEmailById(@PathVariable long id) {
		return publicDataService.getEmail(id);
	}
}
