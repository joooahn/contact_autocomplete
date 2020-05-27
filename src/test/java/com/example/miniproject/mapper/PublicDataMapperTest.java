package com.example.miniproject.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.miniproject.model.PublicData;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublicDataMapperTest {

	@Autowired
	private PublicDataMapper publicDataMapper;

	private PublicData testPublicData() {
		return PublicData.builder()
			.name("서종현")
			.email("sjonghyon@gmail.com")
			.build();
	}

	@Test
	public void insertAndFindAll() {
		Stream.generate(this::testPublicData).limit(10)
			.forEach(publicDataMapper::insert);

		List<PublicData> publicDataList = publicDataMapper.findAll();

		Assertions.assertEquals(publicDataList.size(), 10);
	}

	@Test
	public void insertAndFindById() {
		PublicData publicData = testPublicData();
		publicDataMapper.insert(publicData);

		PublicData findPublicData = publicDataMapper.findById(publicData.getId());

		Assertions.assertEquals(publicData.getName(), findPublicData.getName());
		Assertions.assertEquals(publicData.getEmail(), findPublicData.getEmail());
	}
}
