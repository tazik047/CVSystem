/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package ua.nure.pi.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.pi.Path;
import ua.nure.pi.client.AdminPanelService;
import ua.nure.pi.dao.FacultyGroupDAO;
import ua.nure.pi.dao.LanguageDAO;
import ua.nure.pi.dao.PassDAO;
import ua.nure.pi.dao.ProgramLanguageDAO;
import ua.nure.pi.dao.PurposeDAO;
import ua.nure.pi.dao.StudentDAO;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.entity.Student;
import ua.nure.pi.parameter.AppConstants;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class AdminPanelServiceImpl extends RemoteServiceServlet 
	implements AdminPanelService {

	private FacultyGroupDAO facultyGroupDAO;
	private StudentDAO studentDAO;
	private ProgramLanguageDAO programLanguageDAO;
	private LanguageDAO languageDAO;
	private PurposeDAO purposeDAO;
	private PassDAO passDAO;
	
	@Override
	public void init() {
		ServletContext servletContext = getServletContext();
		facultyGroupDAO = (FacultyGroupDAO) servletContext.getAttribute(AppConstants.FACULTYGROUP_DAO);
		studentDAO = (StudentDAO) servletContext.getAttribute(AppConstants.STUDENT_DAO);
		programLanguageDAO = (ProgramLanguageDAO) servletContext.getAttribute(AppConstants.PROGRAM_LANGUAGE_DAO);
		languageDAO = (LanguageDAO) servletContext.getAttribute(AppConstants.LANGUAGE_DAO);
		purposeDAO = (PurposeDAO) servletContext.getAttribute(AppConstants.PURPOSE_DAO);
		passDAO = (PassDAO) servletContext.getAttribute(AppConstants.PASS_DAO);
		
		if (facultyGroupDAO == null) {
			throw new IllegalStateException("FacultyGroupDAO attribute is not exists.");
		}
		
		if (studentDAO == null) {
			throw new IllegalStateException("StudentDAO attribute is not exists.");
		}
		if (programLanguageDAO == null) {
			throw new IllegalStateException("ProgramLanguageDAO attribute is not exists.");
		}
		
		if (languageDAO == null) {
			throw new IllegalStateException("LanguageDAO attribute is not exists.");
		}
		
		if (purposeDAO == null) {
			throw new IllegalStateException("PurposeDAO attribute is not exists.");
		}
		
		if (passDAO == null) {
			throw new IllegalStateException("PassDAO attribute is not exists.");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*if (log.isDebugEnabled()) {
			log.debug("GET method starts");
		}*/
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(Path.PAGE__ADMIN_PANEL);
		dispatcher.forward(request, response);
		/*if (log.isDebugEnabled()) {
			log.debug("Response was sent");
		}*/
	}
	
	@Override
	public Collection<Faculty> getFaculties() throws IllegalArgumentException {
		Collection<Faculty> faculties = facultyGroupDAO.getFaculties();
		if(faculties == null)
			return new ArrayList<Faculty>();
		else
			return faculties;
	}

	@Override
	public void setFaculties(Faculty faculty) throws IllegalArgumentException {
		if(-1 == facultyGroupDAO.insertFaculty(faculty))
			throw new IllegalArgumentException("Невозможно добавить этот факультет");
	}

	@Override
	public void setGroup(Group group) throws IllegalArgumentException {
		if(-1 == facultyGroupDAO.insertGroup(group))
			throw new IllegalArgumentException("Невозможно добавить эту групу");		
	}

	@Override
	public Collection<Student> getStudents(String pass)
			throws IllegalArgumentException {
		if(passDAO.checkPass(pass)){
			return studentDAO.getStudents();
		}
		throw new IllegalArgumentException("Неправильно введен ключ доступа");
	}
}
