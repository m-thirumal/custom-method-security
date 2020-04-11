package in.co.thirumal.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

//@Component
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

	 private Object filterObject;
	 private Object returnObject;
	    
	public CustomMethodSecurityExpressionRoot(Authentication authentication) {
		super(authentication);
	}
	
	 public boolean isMember(Long id) {
		 if (id.compareTo(100L) < 1) {
			 return true;
		 }
		 return false;
	 }

	@Override
	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;		
	}

	@Override
	public Object getFilterObject() {
		return this.filterObject;
	}

	@Override
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
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
