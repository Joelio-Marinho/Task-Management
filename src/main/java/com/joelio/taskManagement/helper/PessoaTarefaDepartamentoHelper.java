package com.joelio.taskManagement.helper;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PessoaTarefaDepartamentoHelper {
    String nomePessoa;
    String nomeDepartamento;
    Integer totalHoras;
    LocalDate prazo;
    String titulo;
    Integer quantidadePessoas;
    Integer quantidadeTarefas;
    Integer idTarefa;
    String descricaoTarefa;
    BigDecimal duracaotarefa;
    Integer finalizado;
    String tituloTarefa;
    Integer idDepartamento;
    Integer pessoaId;
}
