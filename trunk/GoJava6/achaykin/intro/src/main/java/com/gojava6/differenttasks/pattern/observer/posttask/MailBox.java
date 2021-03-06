package com.gojava6.differenttasks.pattern.observer.posttask;

/**
 * @Autor Andrey Chaykin
 * @Since 26.09.2015
 */
public abstract class MailBox implements Observer {

    public MailBox(PostOffice post) {
        this.post = post;
    }

    PostOffice post;
    String message = "";

    public void printMessage() {
        System.out.println(message);
    }

    public void saveMessage() {
        message = post.getText();
    }

    public void update() {
        saveMessage();
    }
}
