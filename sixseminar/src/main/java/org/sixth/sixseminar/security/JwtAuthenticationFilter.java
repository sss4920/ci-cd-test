package org.sixth.sixseminar.security;

import static org.sixth.sixseminar.security.JwtValidationType.*;

import java.io.IOException;

import org.sixth.sixseminar.service.ServiceMemberService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;
	private final ServiceMemberService serviceMemberService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain) throws ServletException, IOException {
		try {
			final String token = getJwtFromRequest(request);
			System.out.println(token);
			if (jwtTokenProvider.validateToken(token) == VALID_JWT) {
				Long memberId = jwtTokenProvider.getUserFromJwt(token);
				// authentication 객체 생성 -> principal에 유저정보를 담는다.
				UserAuthentication authentication = new UserAuthentication(memberId.toString(), null, null);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			System.out.println("여기");
		} catch (Exception exception) {
			throw new JwtException("jwt filter과정에서 오류가 발생했습니다.");
		}
		// 다음 필터로 요청 전달
		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		}
		return null;
	}
}
