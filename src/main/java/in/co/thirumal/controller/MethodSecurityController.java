/**
 * 
 */
package in.co.thirumal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.co.thirumal.service.IAuthenticationFacade;
import in.co.thirumal.service.MethodSecurityService;

/**
 * @author thirumal
 *
 */
@RestController
@RequestMapping("method")
public class MethodSecurityController {

	@Autowired
	MethodSecurityService methodSecurityService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	@PreAuthorize("hasRole('ROLE_LEAD')" + " or "+ "hasRole('ROLE_ADMIN')")
	@GetMapping(value = "")
	public String sayHello(@RequestParam(value = "name", defaultValue = "thirumal") String name) {
		System.out.println("hi is accessed by " + authenticationFacade.getAuthentication());
		return methodSecurityService.sayHello(name);
	}
	
	@PreAuthorize("hasRole('ROLE_LEAD')")
	@GetMapping(value = "/lead")
	public String sayHelloToLead(@RequestParam(value = "name", defaultValue = "thirumal") String name) {
		System.out.println("hi is accessed by " + authenticationFacade.getAuthentication());
		return methodSecurityService.sayHello(name);
	}
	
	

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/admin")
	public String sayHelloToAdmin(@RequestParam(value = "name", defaultValue = "thirumal") String name) {
		System.out.println("hi is accessed by " + authenticationFacade.getAuthentication());
		return methodSecurityService.sayHello(name);
	}
	
}
