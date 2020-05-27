package com.example.miniproject.mapper;

import java.util.List;

import javax.swing.text.Position;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.miniproject.model.PositionPriority;
import com.example.miniproject.model.PublicData;
import com.example.miniproject.model.request.SetPositionPriority;

@Mapper
@Repository
public interface PositionPriorityMapper {
	void createPosition(SetPositionPriority setPositionPriority);

	List<PositionPriority> retrievePosition();

	List<PositionPriority> retrievePositionByTargetNameOrPriority(@Param("targetName") String targetName, @Param("priority") int priority);

	void updatePosition(@Param("id") int id, @Param("priority") int priority);

	void deletePosition(@Param("id") int id);

	List<PublicData> searchOrderByPosition();

	PositionPriority getPosition(@Param("id") int id);
}
