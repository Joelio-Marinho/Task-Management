package com.joelio.taskManagement.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class BusinessException extends Exception{

    /**
     * Exception message error
     */
    private String message;

    /**
     *
     * @param message
     */
    public BusinessException(String message) {
        this(message, LocaleContextHolder.getLocale(), null);
    }

    /**
     *
     * @param message
     * @param locale
     * @param e
     */
    public BusinessException(String message, Locale locale, Exception e) {
        super(MessageSourceUtil.findMessage(message, locale), e);
    }

    /**
     *
     * @param message
     * @param e
     */
    public BusinessException(String message, Exception e) {
        this(message, LocaleContextHolder.getLocale(), e);
    }
}
