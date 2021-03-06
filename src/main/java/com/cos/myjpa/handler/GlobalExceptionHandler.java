package com.cos.myjpa.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.handler.ex.MyAuthenticationException;
import com.cos.myjpa.web.dto.CommonRespDto;

@RestController		// 데이터를 리턴할 수 있음.
@ControllerAdvice	// 모든 익셉션을 낚아채준다.
public class GlobalExceptionHandler {

	// 그 중 DataIntegrityViolationException 이 발생하면 해당 함수 실행됨.
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public CommonRespDto<?> dataIntegrityViolation(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
	
	// 그 중 IllegalArgumentException 이 발생하면 해당 함수 실행됨.
	@ExceptionHandler(value = IllegalArgumentException.class)
	public CommonRespDto<?> illegalArgument(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
	
	// 그 중 EmptyResultDataAccessException 이 발생하면 해당 함수 실행됨.
	@ExceptionHandler(value = EmptyResultDataAccessException.class)
	public CommonRespDto<?> emptyResultDataAccessException(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
	
	// 그 중 EmptyResultDataAccessException 이 발생하면 해당 함수 실행됨.
	@ExceptionHandler(value = MyAuthenticationException.class)
	public CommonRespDto<?> myAuthenticationException(Exception e) {
		return new CommonRespDto<>(-1, "로그인 후 사용해주세요.", null);
	}
			
}
