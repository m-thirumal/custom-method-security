package in.co.thirumal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.co.thirumal.service.IAuthenticationFacade;
import in.co.thirumal.service.MethodSecurityService;

/**
 * 
 * @author Thirumal
 * 
 * The @PostAuthorize annotation provides the ability to access the method result:
 *
 */
@RestController
@RequestMapping("/post-auth")
public class PostAuthorizeController {

	@Autowired
	MethodSecurityService methodSecurityService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	String hi = "hi is accessed by ";
	
	/** Works good**/
	@PostAuthorize("#name == authentication.principal.username")
	@GetMapping(value = "")
	public String sayHellotoUser(@RequestParam(value = "name", defaultValue = "thirumal") String name) {
		System.out.println(hi + authenticationFacade.getAuthentication());
		return methodSecurityService.sayHello(name);
	}
	
	@PostAuthorize("returnObject.name == authentication.principal.username")
	@GetMapping(value = "/custom-object")
	public User loadUserDetail(String username) {
	    return new User(username);
	}
	
}


class User {
	
	String name;

	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
