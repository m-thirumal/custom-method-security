/**
 * 
 */
package in.co.thirumal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/method")
public class MethodSecurityController {

	@Autowired
	MethodSecurityService methodSecurityService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	
	@PreAuthorize("hasPermission(returnObject, 'read')")
	@GetMapping(value = "/custom")
	public String customSecurity(@RequestParam(value = "name", defaultValue = "thirumal") String name) {
		System.out.println("hi is accessed by " + authenticationFacade.getAuthentication());
		return methodSecurityService.sayHello(name);
	}
	
	
	@PreAuthorize("hasWorkerRole(#id)")
	@GetMapping(value = "/custom/{id}")
	public String customMethodSecurity(@PathVariable long id, @RequestParam(value = "name", defaultValue = "thirumal") String name) {
		System.out.println(id);
		System.out.println("hi is accessed by " + authenticationFacade.getAuthentication());
		return methodSecurityService.sayHello(name);
	}
	
	//---Start of @secured
	@Secured("ROLE_S")
	@GetMapping("/secured-annotation")
	public String getUsername() {
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    return securityContext.getAuthentication().getName();
	}
	
	@Secured({"ROLE_S", "ROLE_LEAD"})
	@GetMapping("/secured-annotation-with-multiple-role")//The @Secured annotation doesn't support Spring Expression Language (SpEL).
	public String getUsernameForLeadS() {
	    return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	//-End of @secured
	
}
