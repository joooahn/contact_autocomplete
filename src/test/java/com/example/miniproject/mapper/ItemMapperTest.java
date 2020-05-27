package com.example.miniproject.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemMapperTest {

	@Autowired
	private ItemMapper itemMapper;

	@Test
	public void getRecentItem() {
		itemMapper.getRecentItem(1, "test");
	}
}
