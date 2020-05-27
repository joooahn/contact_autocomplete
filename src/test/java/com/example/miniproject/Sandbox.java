package com.example.miniproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class Sandbox {
	@Test
	public void test() {
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= 1_000_000; i++) {
			list.add(RandomStringUtils.random(10));
		}

		long a = System.currentTimeMillis();
		list.stream()
			.forEach(str -> str.equals("aa"));
		System.out.println(System.currentTimeMillis() - a);
	}
}
