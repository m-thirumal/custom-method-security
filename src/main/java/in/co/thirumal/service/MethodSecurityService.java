/**
 * 
 */
package in.co.thirumal.service;

import org.springframework.stereotype.Service;

/**
 * @author thirumal
 *
 */
@Service
public class MethodSecurityService {

	public String sayHello(String name) {
		return "Hello " + name;
	}
}
