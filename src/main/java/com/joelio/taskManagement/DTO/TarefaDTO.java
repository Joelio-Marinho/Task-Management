package com.joelio.taskManagement.DTO;

import com.joelio.taskManagement.exception.BusinessException;
import com.joelio.taskManagement.model.Departamento;
import com.joelio.taskManagement.model.Enum.TarefaStaus;
import com.joelio.taskManagement.model.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class TarefaDTO {


    private Integer id;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String titulo;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String descricao;

    @NotEmpty
    private LocalDate prazo;

    @NotNull
    private Departamento departamento;

    @NotNull
    private Integer duracao;

    private Pessoa pessoa;

    private TarefaStaus finalizado;

    public TarefaStaus getStatus() throws BusinessException {
        return TarefaStaus.toEnum(finalizado.getCod());
    }

}
