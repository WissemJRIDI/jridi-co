package com.example.application.views.jridico;

import com.example.application.components.MessageList;
import com.example.application.views.main.ChatMessage;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.time.LocalDateTime;

@Route(value = "2", layout = MainView.class)
@PageTitle("Chat")
@CssImport("./styles/views/jridico/jridi-co-chat.css")
public class JridiCoChat extends VerticalLayout {
    private String username;
    private UnicastProcessor<ChatMessage> publisher;
    private Flux<ChatMessage> messages;

    public JridiCoChat(UnicastProcessor<ChatMessage> publisher, Flux<ChatMessage> messages){
        this.publisher = publisher;
        this.messages = messages;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();

        //addClassName("chat-view");

        //H1 header = new H1("Jridi & Co. Chat");
        //header.getElement().getThemeList().add("dark");

        //add(header);

        askUserName();
    }

    private void askUserName(){
        HorizontalLayout usernameLayout = new HorizontalLayout();

        TextField usernameField = new TextField();
        Button startButton = new Button("Start Chatting");

        startButton.addClickListener(click ->{
            username = usernameField.getValue();
            remove(usernameLayout);
            showChat();
        });

        usernameLayout.add(usernameField, startButton);

        add(usernameLayout);
    }

    private void showChat(){
        MessageList messageList = new MessageList();

        add(messageList, createInputLayout());

        expand(messageList);
    }

    private Component createInputLayout(){
        HorizontalLayout inputLayout = new HorizontalLayout();
        inputLayout.setWidth("100%");

        TextField messageField = new TextField();
        Button sendButton = new Button("Send");
        sendButton.getElement().getThemeList().add("primary");

        inputLayout.add(messageField, sendButton);
        inputLayout.expand(messageField);

        sendButton.addClickListener(click -> {
            publisher.onNext(new ChatMessage(username, LocalDateTime.now(), messageField.getValue()));
            messageField.clear();
            messageField.focus();
        });

        messageField.focus();

        return inputLayout;
    }
}

