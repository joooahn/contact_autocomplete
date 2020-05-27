package com.example.miniproject.model.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 여러가지 정렬 기준을 가지고 있는 클래스
 */
public class MultiOrderBy implements OrderByable {

	private final List<OrderByable> orderByables;

	public MultiOrderBy(List<OrderByable> orderByables) {
		this.orderByables = new ArrayList<>(orderByables);
	}

	public MultiOrderBy(OrderByable... orderByables) {
		this(Arrays.asList(orderByables));
	}

	public static MultiOrderBy combine(OrderByable... orderByables) {
		return new MultiOrderBy(orderByables);
	}

	@Override
	public String column() {
		return this.orderByables.stream()
			.map(OrderByable::column)
			.collect(Collectors.joining(", "));
	}
}
