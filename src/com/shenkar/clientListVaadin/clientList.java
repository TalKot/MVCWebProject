package com.shenkar.clientListVaadin;

import com.shenkar.model.HibernateToDoListDAO;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Iterator;

@SuppressWarnings("serial")
@Theme("testingClass")

public class clientList extends UI{

		@Override
		protected void init(VaadinRequest request) {
			final VerticalLayout layout = new VerticalLayout();
			layout.setMargin(true);
			setContent(layout);
			java.util.List users =HibernateToDoListDAO.Instance().getUsers();
			int amount=1;
			Iterator i = users.iterator();
			while(i.hasNext()) 
			{
				layout.addComponent(new Label(amount+"."+i.next().toString()));
				amount++;
			}
		
	}
		
}
