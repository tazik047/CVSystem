package ua.nure.pi.security.path;

import java.util.ArrayList;
import java.util.List;

import ua.nure.pi.entity.Right;

/**
 * The <code>Constraint</code> class contains forbidden url-patterns for rights.
 * 
 * @author Volodymyr_Semerkov
 * 
 */
public class Constraint {
	private String URLPattern;
	private List<Right> rights;

	public String getURLPattern() {
		return URLPattern;
	}

	public void setURLPattern(String urlPattern) {
		URLPattern = urlPattern;
	}

	public List<Right> getRights() {
		if (rights == null) {
			rights = new ArrayList<Right>();
		}
		return rights;
	}
}
