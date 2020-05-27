package com.example.miniproject.model.support;

import java.util.function.Function;

/**
 * 검색 조건에 따라 다른 LIKE 패턴을 만드는 enum
 */
public enum SearchType implements LikeableMaker {
	STARTS_WITH(s -> s + "%"),
	CONTAINS(s -> "%" + s + "%"),
	ENDS_WITH(s -> "%" + s);

	private final Function<String, String> makePattern;

	SearchType(Function<String, String> makePattern) {
		this.makePattern = makePattern;
	}

	@Override
	public String pattern(String searchText) {
		return makePattern.apply(searchText);
	}
}
