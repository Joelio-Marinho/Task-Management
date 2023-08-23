package com.joelio.taskManagement.Repository;

import com.joelio.taskManagement.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Integer> {


}
