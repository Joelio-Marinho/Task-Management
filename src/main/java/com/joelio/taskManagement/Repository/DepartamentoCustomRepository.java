package com.joelio.taskManagement.Repository;

import com.joelio.taskManagement.helper.PessoaTarefaDepartamentoHelper;

import java.util.List;

public interface DepartamentoCustomRepository {

    List<PessoaTarefaDepartamentoHelper> retornaDepartamentoQPessoasQTarefas();
}
