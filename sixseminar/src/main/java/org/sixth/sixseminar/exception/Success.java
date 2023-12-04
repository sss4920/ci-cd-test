package org.sixth.sixseminar.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Success {

	/**
	 * 201 CREATED
	 */
	CREATE_MEMBER_SUCCESS(HttpStatus.CREATED, "유저 생성 완-벽"),

	/**
	 * 200 OK
	 */
	GET_MEMBER_SUCCESS(HttpStatus.OK, "유저 조회 성공~"),
	GET_MEMBERS_SUCCESS(HttpStatus.OK, "유저 전체조회 성공~"),

	DELETE_MEMBER_SUCCESS(HttpStatus.OK, "유저 삭제 성공~"),

	/**
	 * 204 NO_CONTENT
	 */
	UPDATE_MEMBER_SUCCESS(HttpStatus.NO_CONTENT, "유저 수정 성공~"),



	;

	private final HttpStatus httpStatus;
	private final String message;

	public int getHttpStatusCode(){
		return httpStatus.value();
	}

}
