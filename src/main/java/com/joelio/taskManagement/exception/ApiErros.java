package com.joelio.taskManagement.exception;

import java.util.List;

public class ApiErros {
    private Integer status;
    private String title;
    private Long timestamp;
    private List<String> errors;

    private ApiErros() {

    }

    public static ApiErros newBuilder(){
        return new ApiErros();
    }

    public ApiErros status(Integer status){
        this.status = status;
        return this;
    }

    public ApiErros title(String title){
        this.title = title;
        return this;
    }

    public ApiErros timestamp(Long timestamp){
        this.timestamp = timestamp;
        return this;
    }

    public ApiErros errors(List<String> errors){
        this.errors = errors;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }
}
