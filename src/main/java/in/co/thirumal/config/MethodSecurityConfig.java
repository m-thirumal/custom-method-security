package in.co.thirumal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import in.co.thirumal.security.CustomMethodSecurityExpressionHandler;
import in.co.thirumal.security.CustomPermissionEvaluator;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration{

	 @Override
	 protected MethodSecurityExpressionHandler createExpressionHandler() {
	         //final DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
	       final CustomMethodSecurityExpressionHandler expressionHandler = new CustomMethodSecurityExpressionHandler();
	         System.out.println("gi");
	        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
	        return expressionHandler;
	    }
	 
}
