/**
 * 
 */
package in.co.thirumal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value = "")
	public String sayHello(@RequestParam(value = "name", defaultValue = "thirumal") String name) {
		return methodSecurityService.sayHello(name);
	}
	
}
