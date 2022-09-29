/**
 * 
 */
package in.co.thirumal.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import in.co.thirumal.repository.RoleRepository;

/**
 * @author Thirumal
 *
 */
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RoleRepository roleRepository;
	private Object filterObject;
	private Object returnObject;
	
	    
	public CustomMethodSecurityExpressionRoot(Authentication authentication) {
		super(authentication);
	}
	
	
	public boolean hasWorkerRole(int comapnyId) {
		String user = this.authentication.getName();
		boolean hasRole = roleRepository.hasRoleWorkerRole(comapnyId, user);
		logger.debug("The user {} has worker role ? ---> {} ", user, hasRole);
        return hasRole;
	}
	
	//We need this setter method to set the `roleRepository` from another class because this one dosen't have access to Application Context.
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

	@Override
	public Object getFilterObject() {
		return this.filterObject;
	}

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }
    
	@Override
	public Object getReturnObject() {
		return this.returnObject;
	}

	@Override
	public Object getThis() {
		return this;
	}

}
