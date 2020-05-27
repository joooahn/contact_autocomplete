package com.example.miniproject.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PrivateDataMapper {

	/**
	 * 이미 to 와 from 으로 emailSend가 있으면 업데이트하고
	 * 저장된 데이터가 없으면 삽입하는 메소드
	 *
	 * @param to 누구에게
	 * @param from 누가
	 * @param emailSendDate 이메일을 언제 보냈는지
	 */
	void setEmailSendDate(@Param("to") Long to,
		@Param("from") Long from,
		@Param("emailSendDate") LocalDateTime emailSendDate);

	/**
	 * 이미 to 와 from 으로 vip가 있으면 업데이트하고
	 * 저장된 데이터가 없으면 삽입하는 메소드
	 *
	 * @param to 누구에게
	 * @param from 누가
	 * @param vip vip 설정을 했는지 안 했는지
	 */
	void setVip(@Param("to") Long to,
		@Param("from") Long from,
		@Param("vip") boolean vip);

	/**
	 * 이메일 서버에서 전송 시간을 기록하기 위해 호출하는 API 메소드
	 *
	 * @param to    수신자
	 * @param from    발신자
	 */
	void insertTransmission(@Param("to") Long to,
		@Param("from") Long from);
}
