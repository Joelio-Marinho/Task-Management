package com.joelio.taskManagement.Repository;

import com.joelio.taskManagement.DTO.TarefaDTO;
import com.joelio.taskManagement.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Integer> {

    @Query(value = "SELECT * FROM Tarefa t WHERE  t.pessoa_id is null", nativeQuery = true)
    List<Tarefa> findAllByPessoaEmptyAndOrderByPrazoAsc();


}
