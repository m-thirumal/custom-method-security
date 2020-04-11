package in.co.thirumal.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		System.out.println("hi permission " );
		if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)) {
			System.out.println(authentication);
			System.out.println(targetDomainObject);
			System.out.println(permission);
            return false;
        }
        final String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
        return hasPrivilege(authentication, targetType, permission.toString().toUpperCase());
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		System.out.println("hi permission " );
		if ((authentication == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(authentication, targetType.toUpperCase(), permission.toString().toUpperCase());
	}

	private boolean hasPrivilege(Authentication authentication, String targetType, String permission) {
		System.out.println("per");
		   for (final GrantedAuthority grantedAuth : authentication.getAuthorities()) {
	            System.out.println("here " + grantedAuth);
	            if (grantedAuth.getAuthority().startsWith(targetType)) {
	                if (grantedAuth.getAuthority().contains(permission)) {
	                    return true;
	                }
	            }
	        }
	        return false;
	}

}
