package com.example.miniproject.model.support;

/**
 * 어떻게 정렬할 건지 컬럼 이름과 정렬 방향을 가지고 있는 클래스
 */
public class OrderBy implements OrderByable {

	private final String columnName;

	private final Order order;

	public OrderBy(String columnName, Order order) {
		this.columnName = columnName;
		this.order = order;
	}

	public static OrderBy of(String columnName) {
		return OrderBy.of(columnName, Order.ASC);
	}

	public static OrderBy of(String columnName, Order order) {
		return new OrderBy(columnName, order);
	}

	@Override
	public String column() {
		return columnName + " " + order.name();
	}
}
