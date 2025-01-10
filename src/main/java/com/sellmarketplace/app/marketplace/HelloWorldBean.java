package com.sellmarketplace.app.marketplace;

import lombok.Getter;
import lombok.Setter;


public class HelloWorldBean {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public HelloWorldBean(String message) {
        this.message = message;
    }

    @Override public String toString() {
        return "HelloWorldBean [message=" + message + "]";
    }
}
