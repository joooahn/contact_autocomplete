package com.example.miniproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.miniproject.model.PublicData;

@Mapper
@Repository
public interface ItemMapper {
	List<PublicData> getRecentItem(@Param("senderID") int senderID, @Param("searchText") String searchText);
}
