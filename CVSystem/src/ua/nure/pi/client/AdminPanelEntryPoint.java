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
package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.Student;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Overflow;  
import com.smartgwt.client.types.VisibilityMode;  
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Img;  
import com.smartgwt.client.widgets.events.ClickEvent;  
import com.smartgwt.client.widgets.events.ClickHandler;  
import com.smartgwt.client.widgets.layout.HLayout;  
import com.smartgwt.client.widgets.layout.SectionStack;  
import com.smartgwt.client.widgets.layout.SectionStackSection;  
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdminPanelEntryPoint implements EntryPoint {
	
	private final AdminPanelServiceAsync adminPanelService = GWT
			.create(AdminPanelService.class);
	
	public void onModuleLoad() {  
        HTMLFlow htmlFlow = new HTMLFlow();  
        htmlFlow.setOverflow(Overflow.AUTO);  
        htmlFlow.setPadding(10);  
  
        String contents = "<b>Severity 1</b> - Critical problem<br>System is unavailable in production or " +  
                "is corrupting data, and the error severely impacts the user's operations." +  
                "<br><br><b>Severity 2</b> - Major problem<br>An important function of the system " +  
                "is not available in production, and the user's operations are restricted." +  
                "<br><br><b>Severity 3</b> - Minor problem<br>Inability to use a function of the " +  
                "system occurs, but it does not seriously affect the user's operations.";  
  
        htmlFlow.setContents(contents);  
  
        final RootPanel root = RootPanel.get("content");
        VerticalPanel vp = new VerticalPanel();
        root.add(vp);
        final PasswordTextBox ptb = new PasswordTextBox();
        Button bt = new Button("Получить");
        final SimplePanel cv = new SimplePanel();
        vp.add(ptb);
        vp.add(bt);
        vp.add(cv);
        bt.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
			
			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				adminPanelService.getStudents(ptb.getText(), new AsyncCallback<Collection<Student>>() {
					
					@Override
					public void onSuccess(Collection<Student> result) {
						cv.add(new TablePanel(result));						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						cv.add(new Label(caught.getLocalizedMessage()));
					}
				});
			}
		});
                
        /*final SectionStack sectionStack = new SectionStack();  
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);  
        sectionStack.setWidth("90%");  
        sectionStack.setHeight(350);  
  
        SectionStackSection section1 = new SectionStackSection("Blue Pawn");
        section1.addItem(new AddFacultiesCanvas(adminPanelService));  
        sectionStack.addSection(section1); 

        sectionStack.draw();*/
    }  
}
