package com.joelio.taskManagement.Repository;

import com.joelio.taskManagement.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Integer> {

    boolean existsByTitulo(String titulo);
}
