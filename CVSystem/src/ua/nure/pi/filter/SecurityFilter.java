package ua.nure.pi.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;

import ua.nure.pi.Path;
import ua.nure.pi.entity.Right;
import ua.nure.pi.entity.Role;
import ua.nure.pi.entity.User;
import ua.nure.pi.security.path.SecurityManager;
import ua.nure.pi.security.path.StAXParser;
import ua.nure.pi.parameter.AppConstants;

/**
 * Security filter.
 * 
 * @author Volodymyr_Semerkov
 * 
 */
public class SecurityFilter implements Filter {
	private SecurityManager securityManager;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		String xmlFileName = servletContext
				.getInitParameter(AppConstants.SECURITY_XML);
		boolean isValidate;
		try {
			isValidate = StAXParser.validate(xmlFileName);
		} catch (XMLStreamException e) {
			throw new IllegalStateException("XML file validation error");
		}
		if (isValidate) {
			try {
				securityManager = new SecurityManager(
						StAXParser.parse(xmlFileName));
			} catch (XMLStreamException e) {
				throw new IllegalStateException("XML file parsing error");
			}
		} else {
			throw new IllegalStateException("XML file is not valid");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		User user = (User) httpRequest.getSession().getAttribute(
				AppConstants.USER);
		List<Role> roles = user != null ? user.getRoles() : null;
		List<Right> rights = new ArrayList<Right>();
		if (roles != null) {
			for (Role role : roles) {
				rights.add(role.getRight());
			}
		} else {
			rights.add(Right.VISITOR);
		}
		String pagePath = httpRequest.getServletPath();
		if (securityManager.accept(pagePath, rights)) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		} else {
			if (rights.contains(Right.VISITOR)) {
				RequestDispatcher requestDispatcher = httpRequest
						.getRequestDispatcher(Path.PAGE__LOGIN);
				requestDispatcher.forward(httpRequest, httpResponse);
				return;
			} else {
				httpResponse.sendError(403);
				return;
			}
		}
	}

	@Override
	public void destroy() {
	}
}
