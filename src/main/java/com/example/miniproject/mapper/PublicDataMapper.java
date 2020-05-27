package com.example.miniproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.miniproject.model.PublicData;
import com.example.miniproject.model.SearchData;

@Mapper
@Repository
public interface PublicDataMapper {

	PublicData findById(Long id);

	Long findIdByEmail(String email);

	List<PublicData> findAll();

	List<PublicData> findAllById(@Param("ids") Iterable<Long> ids);

	List<SearchData> findAllByNameLikeOrderBy(@Param("searcherId") Long searcherId,
		@Param("searchText") String searchText);

	void insert(PublicData publicData);

	void updateContactsPosition(@Param("id") int id, @Param("positionId") int positionId);

	String getEmailById(@Param("id") long id);
}
