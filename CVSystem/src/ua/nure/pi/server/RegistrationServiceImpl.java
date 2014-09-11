package ua.nure.pi.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.pi.Path;
import ua.nure.pi.client.GreetingService;
import ua.nure.pi.client.RegistrationService;
import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.dao.mssql.MSSqlDAOFactory;
import ua.nure.pi.dao.mssql.MSSqlUserDAO;
import ua.nure.pi.parameter.AppConstants;
import ua.nure.pi.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RegistrationServiceImpl extends RemoteServiceServlet implements
	RegistrationService {
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*if (log.isDebugEnabled()) {
			log.debug("GET method starts");
		}*/
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(Path.PAGE__REGISTRATION);
		dispatcher.forward(request, response);
		/*if (log.isDebugEnabled()) {
			log.debug("Response was sent");
		}*/
	}
}
