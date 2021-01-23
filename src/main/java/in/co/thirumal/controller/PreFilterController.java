/**
 * 
 */
package in.co.thirumal.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Thirumal
 *
 */
@RestController
@RequestMapping("/pre-filter")
public class PreFilterController {

	/**
	 * 
	 * @return list of name except login user name
	 */
	@GetMapping(value = "")
	public String preFilter() {
		return joinUsernames(List.of("thirumal", "Jessica", "Kate"));
	}
	
	@PreFilter("filterObject != authentication.principal.username")
	public String joinUsernames(List<String> usernames) {
	    return usernames.stream().collect(Collectors.joining(";"));
	}
	
	@PreFilter(value = "filterObject != authentication.principal.username", filterTarget = "usernames")
	public String joinUsernamesAndRoles(List<String> usernames, List<String> roles) {	 
	    return usernames.stream().collect(Collectors.joining(";")) 
	      + ":" + roles.stream().collect(Collectors.joining(";"));
	}
	
}
