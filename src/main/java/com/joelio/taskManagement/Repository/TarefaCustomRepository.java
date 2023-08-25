package com.joelio.taskManagement.Repository;

import com.joelio.taskManagement.helper.PessoaTarefaDepartamentoHelper;
import com.joelio.taskManagement.model.Tarefa;

import java.util.List;

public interface TarefaCustomRepository {
    List<PessoaTarefaDepartamentoHelper> retornaTresTarefasMaisAntigasSemAlocacao();

}
