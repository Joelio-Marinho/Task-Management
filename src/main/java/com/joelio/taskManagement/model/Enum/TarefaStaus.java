package com.joelio.taskManagement.model.Enum;

import com.joelio.taskManagement.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum TarefaStaus {

    PENDING(0, "false"),
    FINISHED(1, "true"),;

    private Integer cod;
    private String description;

    TarefaStaus(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static TarefaStaus toEnum(Integer cod) throws BusinessException {
        if (cod == null){
            return null;
        }
        for (TarefaStaus x : TarefaStaus.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new BusinessException("invalid.id", new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
