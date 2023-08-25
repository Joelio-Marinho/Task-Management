package com.joelio.taskManagement.Repository;

import com.joelio.taskManagement.helper.PessoaTarefaDepartamentoHelper;

import java.time.LocalDate;
import java.util.List;

public interface PessoaCustomRepository {

    PessoaTarefaDepartamentoHelper retornaNomePessoaDepartamentoTotalHorasGastasPorTarefa();

    List<PessoaTarefaDepartamentoHelper> retornaNomeEPeriodo(LocalDate prazoInicial, LocalDate prazoFinal);
}
