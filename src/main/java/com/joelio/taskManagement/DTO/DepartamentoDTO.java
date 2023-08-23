package com.joelio.taskManagement.DTO;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class DepartamentoDTO {

    private Integer id;

    @NotEmpty
    @Size(min = 3, max = 255)
    private String titulo;
}
