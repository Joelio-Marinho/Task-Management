package com.joelio.taskManagement.model;

import com.joelio.taskManagement.exception.BusinessException;
import com.joelio.taskManagement.model.Enum.TarefaStaus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Tarefa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String titulo;
    @Column
    private String descricao;
    @Column
    private String prazo;
    @ManyToOne
    @JoinColumn(name = "IdDepartamento")
    private Departamento departamento;
    @Column
    private Integer duracao;
    @ManyToOne
    private Pessoa pessoa;
    @Column
    private TarefaStaus finalizado;

    public TarefaStaus getStatus() throws BusinessException {
        return TarefaStaus.toEnum(finalizado.getCod());
    }
}
