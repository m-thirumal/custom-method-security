/**
 * 
 */
package in.co.thirumal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Thirumal
 *
 */
@RestController
@RequestMapping("/post-filter")
public class PostFilterController {

	/**
	 * 
	 * @return list of name except login user name
	 */
	@GetMapping(value = "")
	@org.springframework.security.access.prepost.PostFilter("filterObject != authentication.principal.username")
	public List<String> getAllUsernamesExceptCurrent() {
	    return new ArrayList<> (
	    	List.of("thirumal", "Jessica", "Kate"));
	}
}
