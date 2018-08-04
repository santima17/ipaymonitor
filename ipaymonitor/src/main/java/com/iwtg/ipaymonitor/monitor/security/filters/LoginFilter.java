package com.iwtg.ipaymonitor.monitor.security.filters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iwtg.ipaymonitor.monitor.security.datatypes.User;
import com.iwtg.ipaymonitor.monitor.security.utils.JwtUtil;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	public LoginFilter(final String url, final AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(final HttpServletRequest req, final HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {

		//InputStream body = req.getInputStream();

		//User user = new ObjectMapper().readValue(body, User.class);

		UsernamePasswordAuthenticationToken userToAuth = new UsernamePasswordAuthenticationToken(
				req.getParameter("username"), req.getParameter("password"));

		return getAuthenticationManager().authenticate(userToAuth);
	}

	@Override
	protected void successfulAuthentication(final HttpServletRequest req, final HttpServletResponse res,
			final FilterChain chain, final Authentication auth) throws IOException, ServletException {
		JwtUtil.addAuthentication(res, auth.getName());
	}

}