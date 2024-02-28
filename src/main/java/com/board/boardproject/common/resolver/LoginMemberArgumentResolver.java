package com.board.boardproject.common.resolver;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.board.boardproject.common.domain.LoginMember;
import com.board.boardproject.common.exception.ErrorMessage;

public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
	
	/**
	 * 파라미터의 타입이 LoginMember 인지 확인
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(LoginMember.class);
	}
	
	/**
	 * LoginMember 파라미터에 값을 바인딩 하는 작업
	 */
	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory
	) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		Objects.requireNonNull(session, ErrorMessage.IS_NOT_LOGIN_MEMBER.getMessage());
		
		LoginMember member = (LoginMember) session.getAttribute("loginMember");
		Objects.requireNonNull(member, ErrorMessage.IS_NOT_LOGIN_MEMBER.getMessage());
		
		return member;
	}
}
