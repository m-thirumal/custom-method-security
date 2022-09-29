/**
 * 
 */
package in.co.thirumal.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

import in.co.thirumal.repository.RoleRepository;

/**
 * @author Thirumal
 *
 */
public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
	
	private ApplicationContext applicationContext;
	private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	@Override
	protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
			MethodInvocation invocation) {
		CustomMethodSecurityExpressionRoot root = new CustomMethodSecurityExpressionRoot(authentication);
		root.setPermissionEvaluator(getPermissionEvaluator());
		root.setTrustResolver(this.trustResolver);
		root.setRoleHierarchy(getRoleHierarchy());
		//set the G in the AuctionMethodSecurityExpressionRoot instance to be able to use it
		root.setRoleRepository(this.applicationContext.getBean(RoleRepository.class));
		return root;
	}
	
	//This setter method will be called from the config class
	@Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        this.applicationContext=applicationContext;
    }
}
