/**
 * 
 */
package in.co.thirumal.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Thirumal
 *
 */
@RestController
@RequestMapping("/annotate")
public class AnnotationController {

	@GetMapping("")
	@IsViewer
	public String getUsername4() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return securityContext.getAuthentication().getName();
	}
}
