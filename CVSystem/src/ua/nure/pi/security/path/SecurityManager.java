package ua.nure.pi.security.path;

import java.util.List;

//import org.apache.log4j.Logger;

import ua.nure.pi.entity.Right;

/**
 * Security manager.
 * 
 * @author Volodymyr_Semrkov
 * 
 */
public class SecurityManager {
	List<Constraint> constraints;
	//private static final Logger log = Logger.getLogger(SecurityManager.class);

	public SecurityManager(List<Constraint> constraints) {
		for (Constraint constraint : constraints) {
			String urlPattern = constraint.getURLPattern();
			urlPattern = urlPattern.replaceAll("\\*", ".*");
			constraint.setURLPattern(urlPattern);
		}
		this.constraints = constraints;
	}

	public boolean accept(String pagePath, List<Right> rights) {
		//log.trace("pagePath --> " + pagePath);
		//log.trace("rights --> " + rights);
		for (Constraint constraint : constraints) {
			if (constraint.getRights().containsAll(rights)
					&& pagePath.matches(constraint.getURLPattern())) {
				return false;
			}
		}
		return true;
	}
}
