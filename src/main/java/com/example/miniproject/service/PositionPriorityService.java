package com.example.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.miniproject.mapper.PositionPriorityMapper;
import com.example.miniproject.model.PositionPriority;
import com.example.miniproject.model.PublicData;
import com.example.miniproject.model.request.SetPositionPriority;

@Service
public class PositionPriorityService {
	private PositionPriorityMapper positionPriorityMapper;

	@Autowired
	public PositionPriorityService(PositionPriorityMapper positionPriorityMapper) {
		this.positionPriorityMapper = positionPriorityMapper;
	}

	public void createPosition(SetPositionPriority setPositionPriority) {
		positionPriorityMapper.createPosition(setPositionPriority);
	}

	public List<PositionPriority> retrievePosition() {
		return positionPriorityMapper.retrievePosition();
	}

	public List<PositionPriority> retrievePosition(String targetName, int priority) {
		return positionPriorityMapper.retrievePositionByTargetNameOrPriority(targetName, priority);
	}

	public void updatePosition(int id, int priority) {
		positionPriorityMapper.updatePosition(id, priority);
	}

	public void deletePosition(int id) {
		positionPriorityMapper.deletePosition(id);
	}

	public List<PublicData> searchOrderByPosition() {
		return positionPriorityMapper.searchOrderByPosition();
	}

	public PositionPriority getPosition(int id){
		return positionPriorityMapper.getPosition(id);
	}
}
