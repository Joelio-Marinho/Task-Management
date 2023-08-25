package com.joelio.taskManagement.helper;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class TarefaHelper {

    Integer idTarefa;
    String descricaoTarefa;
    BigDecimal duracaotarefa;
    Integer finalizado;
    LocalDate prazo;
    String titulo;
}
