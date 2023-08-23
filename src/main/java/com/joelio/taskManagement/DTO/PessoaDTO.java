package com.joelio.taskManagement.DTO;

import com.joelio.taskManagement.model.Departamento;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class PessoaDTO {


    private Integer id;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String nome;
    @NotNull
    private Departamento departamento;

}
