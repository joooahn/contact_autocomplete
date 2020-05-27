package com.example.miniproject.model.support;

/**
 * SQL 에서 LIKE 절을 사용할 때 패턴을 만드는
 * 인터페이스
 */
public interface LikeableMaker {

	String pattern(String searchText);
}
