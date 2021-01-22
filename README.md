# custom-method-security

Spring boot method level security

### Dependency ###

It requires `spring-security-config` but it's part of `spring-boot-starter-security` in spring boot

### Enable Global Method Security ###
	
	@Configuration
	@EnableGlobalMethodSecurity(
	  prePostEnabled = true, 
	  securedEnabled = true, 
	  jsr250Enabled = true)
	public class MethodSecurityConfig 
	  extends GlobalMethodSecurityConfiguration {
	}
    
* The `prePostEnabled` property enables Spring Security `pre/post annotations`
* The `securedEnabled` property determines if the `@Secured` annotation should be enabled
* The `jsr250Enabled` property allows us to use the `@RoleAllowed` annotation

