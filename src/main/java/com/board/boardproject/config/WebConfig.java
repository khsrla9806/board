package com.board.boardproject.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.board.boardproject.common.resolver.LoginMemberArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	/**
	 * 커스텀하게 생성한 ArgumentResolver를 등록
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginMemberArgumentResolver());
	}
}
