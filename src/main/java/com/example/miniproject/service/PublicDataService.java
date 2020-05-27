package com.example.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.miniproject.mapper.PublicDataMapper;
import com.example.miniproject.model.PublicData;
import com.example.miniproject.model.SearchData;
import com.example.miniproject.model.support.MultiOrderBy;
import com.example.miniproject.model.support.Order;
import com.example.miniproject.model.support.OrderBy;

@Service
public class PublicDataService {

	private final PublicDataMapper publicDataMapper;

	public PublicDataService(PublicDataMapper publicDataMapper) {
		this.publicDataMapper = publicDataMapper;
	}

	public void insert(PublicData publicData) {
		publicDataMapper.insert(publicData);
	}

	/**
	 * 검색자와 검색어를 통해 가중치가 부여된 공용 데이터를 조회하는 메소드
	 *
	 * @param searcherId 검색자 id
	 * @param searchText 검색어
	 * @return 가중치가 부여된 검색 결과
	 */
	public List<SearchData> search(Long searcherId, String searchText) {
		return publicDataMapper.findAllByNameLikeOrderBy(searcherId, searchText);
	}

	public String getEmail(long id) {
		return publicDataMapper.getEmailById(id);
	}

	public void updateContactsPosition(int id, int positionId) {
		publicDataMapper.updateContactsPosition(id, positionId);
	}
}
