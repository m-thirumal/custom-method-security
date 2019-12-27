/**
 * 
 */
package in.co.thirumal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping(value = "/{name}")
	public String sayHello(@PathVariable("name") String name) {
		return methodSecurityService.sayHello(name);
	}
	
}
