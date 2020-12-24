package com.example.application.views.jridico;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "1", layout = MainView.class)
@PageTitle("Jridi & Co.")
@CssImport("./styles/views/jridico/jridi-co-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class JridiCoView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public JridiCoView() {
        setId("jridi-co-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
    }

}
