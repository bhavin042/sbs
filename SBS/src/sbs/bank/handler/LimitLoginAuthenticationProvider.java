package sbs.bank.handler;

import java.util.Date;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import sbs.bank.dao.UserDetailsDao;
import sbs.bank.users.model.UserAttempts;

public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	UserDetailsDao userDetailsDao;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {

			Authentication auth = super.authenticate(authentication);

			// if reach here, means login success, else exception will be thrown
			// reset the user_attempts
			userDetailsDao.resetFailAttempts(authentication.getName());

			return auth;

		} catch (BadCredentialsException e) {

			userDetailsDao.updateFailAttempts(authentication.getName());
			throw e;

		} catch (LockedException e) {

			String error = "";
			UserAttempts userAttempts = userDetailsDao.getUserAttempts(authentication.getName());
			if (userAttempts != null) {
				Date lastAttempts = userAttempts.getLastModified();
				error = "User Account is locked! Please email us at helpus@sbs.com for further instructions!<br><br> Username : " + authentication.getName()
						+ "<br>Last Attempts : " + lastAttempts;
			} else {
				error = e.getMessage();
			}

			throw new LockedException(error);
		}

	}

	public UserDetailsDao getUserDetailsDao() {
		return userDetailsDao;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

}