package ua.nure.pi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class MainStaticPanel extends StaticPanel {

	private static String text = "<h1 align=\"center\">\"Центр - Карьера\"</h1><h3 align=\"center\">Харьковского национального университета радиоэлектроники</h3><h3> Цель:</h3>  Помочь студентам и выпускникам вузов создать и эффективно выполнить личный план развития профессиональной  карьеры.<h3>Задачи:</h3>  Анализ тенденций развития рынка труда и предоставление образовательных услуг студентам и выпускникам вузов в виде консультаций, тестирования, тренингов по технологии поиска работы в рыночных условиях, а также специализированных курсов по профессиональным вопросам. Создание и поддержка информационных ресурсов профессиональной карьеры и трудоустройства, в т. ч. компьютерных баз данных, печатных изданий, источников в Интернет. Организация прямых контактов с работодателями, поддержка конкурсных отборов кандидатов, и другие формы содействия трудоустройству.<h3> Услуги:</h3>Карьера-центр предлагает свои услуги:<ul><li>Студентам и выпускникам вузов;</li> <li>Предприятиям, фирмам, организациям;</li></ul>"; 
	
	public MainStaticPanel(MainServiceAsync main) {
		main.getPage(PathClient.STATIC_PAGE_MAIN,new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				setText(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				setText(caught.getMessage());
			}
		});
	}

}
