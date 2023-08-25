package com.joelio.taskManagement.Repository;

import com.joelio.taskManagement.helper.DepartamentoHelper;

import java.util.List;

public interface DepartamentoCustomRepository {

    List<DepartamentoHelper> retornaDepartamentoQPessoasQTarefas();
}
