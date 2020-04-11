/**
 * 
 */
package in.co.thirumal.service;

import org.springframework.security.core.Authentication;

/**
 * @author thirumal
 *
 */
public interface IAuthenticationFacade {
	
	Authentication getAuthentication();
	
}
