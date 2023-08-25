package com.joelio.taskManagement.Repository;

import com.joelio.taskManagement.helper.PessoaHelper;
import com.joelio.taskManagement.helper.PessoaSimplHelper;

import java.time.LocalDate;
import java.util.List;

public interface PessoaCustomRepository {

    List<PessoaHelper> retornaNomePessoaDepartamentoTotalHorasGastasPorTarefa();

    PessoaSimplHelper retornaNomeEPeriodo(String namePessoa ,LocalDate prazoInicial, LocalDate prazoFinal);
}
