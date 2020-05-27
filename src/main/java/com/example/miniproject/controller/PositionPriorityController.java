package com.example.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniproject.model.PositionPriority;
import com.example.miniproject.model.PublicData;
import com.example.miniproject.model.exception.InvalidParameterException;
import com.example.miniproject.model.request.SetPositionPriority;
import com.example.miniproject.service.PositionPriorityService;

@CrossOrigin("*")
@RestController
public class PositionPriorityController {

	//service
	private final PositionPriorityService positionPriorityService;

	//service constructor injection
	@Autowired
	public PositionPriorityController(PositionPriorityService positionPriorityService) {
		this.positionPriorityService = positionPriorityService;
	}

	@RequestMapping(value = "/positions", method = RequestMethod.POST)
	public void createPosition(@RequestBody @Validated SetPositionPriority setPositionPriority) {

		List<PositionPriority> positionPriorityList = positionPriorityService.retrievePosition(setPositionPriority.getName(), setPositionPriority.getPriority());

		// DB에 같은 이름 또는 우선순위가 있는지 중복 검사
//		for (int i = 0; i < positionPriorityList.size(); i++) {
//			if (targetName.equals(positionPriorityList.get(i).getName())) {
//				throw new InvalidParameterException("duplicated name");
//			}
//			if (targetPriority == positionPriorityList.get(i).getPriority()) {
//				throw new InvalidParameterException("duplicated priority");
//			}
//		}

		if(positionPriorityList != null){
			throw new InvalidParameterException("duplicated name or priority");
		}

		positionPriorityService.createPosition(setPositionPriority);
	}

	@RequestMapping(value = "/retrievePosition", method=RequestMethod.GET)
	public List<PositionPriority> retrievePosition(){
		return positionPriorityService.retrievePosition();
	}

	@RequestMapping(value = "/positions/{id}/priority/{priority}", method = RequestMethod.PUT)
	public void updatePosition(@PathVariable("id") int id, @PathVariable("priority") int priority) {
		positionPriorityService.updatePosition(id, priority);
	}

	@RequestMapping(value = "/positions/{id}", method = RequestMethod.DELETE)
	public void deletePosition(@PathVariable("id") int id) {
		positionPriorityService.deletePosition(id);
	}

	@RequestMapping(value = "/searchOrderByPosition", method = RequestMethod.GET)
	public List<PublicData> searchOrderByPosition() {
		return positionPriorityService.searchOrderByPosition();
	}

	@RequestMapping(value = "/contacts/{id}/position", method = RequestMethod.GET)
	public PositionPriority getPosition(@PathVariable("id") int id){
		return positionPriorityService.getPosition(id);
	}
}