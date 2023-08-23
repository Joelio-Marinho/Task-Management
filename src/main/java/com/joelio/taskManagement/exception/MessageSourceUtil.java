package com.joelio.taskManagement.exception;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageSourceUtil {
    public static String findMessage(String message, Locale local) {
        ResourceBundle messageSource = ResourceBundle.getBundle("messages", local);
        return messageSource.getString(message);
    }
}
