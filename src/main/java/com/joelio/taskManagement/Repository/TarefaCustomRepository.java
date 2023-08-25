package com.joelio.taskManagement.Repository;


import com.joelio.taskManagement.helper.TarefaHelper;

import java.util.List;

public interface TarefaCustomRepository {
    List<TarefaHelper> retornaTresTarefasMaisAntigasSemAlocacao();

}
