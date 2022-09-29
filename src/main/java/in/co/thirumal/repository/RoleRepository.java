package in.co.thirumal.repository;

import org.springframework.stereotype.Repository;

/**
 * 
 */

/**
 * @author Thirumal
 *
 */
@Repository
public class RoleRepository {

	public boolean hasRoleWorkerRole(int companyId, String user) {
		if (companyId == 1) {
			System.out.println("Has permission to access");
		} else {
			System.out.println("No permission.... to access.......");
		}
		return companyId == 1;
	}

	
}
