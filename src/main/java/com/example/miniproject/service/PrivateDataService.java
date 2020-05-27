package com.example.miniproject.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.miniproject.mapper.PrivateDataMapper;
import com.example.miniproject.mapper.PublicDataMapper;
import com.example.miniproject.model.PublicData;
import com.example.miniproject.model.exception.InvalidParameterException;
import com.example.miniproject.model.exception.NotFoundException;

@Service
public class PrivateDataService {

	private final PrivateDataMapper privateDataMapper;

	private final PublicDataMapper publicDataMapper;

	public PrivateDataService(PrivateDataMapper privateDataMapper,
		PublicDataMapper publicDataMapper) {
		this.privateDataMapper = privateDataMapper;
		this.publicDataMapper = publicDataMapper;
	}

	/**
	 * from 인 사람이 to 인 사람을 vip 로 설정하는 메소드
	 *
	 * @param to 대상의 공용 데이터 id
	 * @param from 설정하는 사람의 공용 데이터 id
	 * @param isVip vip 로 설정할지 여부
	 * @throws NotFoundException 대상 혹은 설정하는 사람의 공용 데이터 id가 없을 경우 발생
	 * @throws InvalidParameterException 자기 자신을 vip 로 설정할 경우 발생
	 */
	public void setVip(Long to, Long from, boolean isVip) {
		if (Objects.equals(to, from)) {
			throw new InvalidParameterException("자기 자신을 vip로 설정할 수 없습니다.");
		}

		List<PublicData> publicDataList = publicDataMapper.findAllById(Arrays.asList(to, from));

		if (publicDataList.size() != 2) {
			throw new NotFoundException("id가 " + to + "혹은 " + from + "에 해당하는 public data가 없습니다.");
		}

		privateDataMapper.setVip(to, from, isVip);
	}

	public void insertTransmission(String to, String from) {
		Long toData = publicDataMapper.findIdByEmail(to);
		Long fromData = publicDataMapper.findIdByEmail(from);

		if (toData == null || fromData == null) {
			throw new NotFoundException("email이 " + to + "혹은 " + from + "에 해당하는 public data id가 없습니다.");
		}

		privateDataMapper.insertTransmission(toData, fromData);
	}
}
