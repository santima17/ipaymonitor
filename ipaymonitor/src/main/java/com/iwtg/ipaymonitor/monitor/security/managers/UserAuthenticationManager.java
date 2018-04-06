package com.iwtg.ipaymonitor.monitor.security.managers;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.iwtg.ipaymonitor.facades.users.interfaces.IPayMonitorUserFacades;

public class UserAuthenticationManager implements AuthenticationManager {
	
	@Resource(name = "userFacades")
	IPayMonitorUserFacades userFacades;
	
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		String passEncrptyed = authentication.getCredentials().toString();
		String userName = authentication.getName();
		boolean user = false;
		user = userFacades.login(userName, passEncrptyed);
		if (!user) {
			throw new BadCredentialsException("1000");
		}
		return new UsernamePasswordAuthenticationToken(userName, passEncrptyed);
}

}
