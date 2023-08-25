package com.joelio.taskManagement.Repository.Implementation;

import com.joelio.taskManagement.Repository.DepartamentoCustomRepository;
import com.joelio.taskManagement.helper.DepartamentoHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class DepartementoImp implements DepartamentoCustomRepository {

    EntityManager entityManager;

    public List<DepartamentoHelper> retornaDepartamentoQPessoasQTarefas(){
        try {
            StringBuilder sqlNativo = new StringBuilder();

            sqlNativo.append("SELECT  ");
            sqlNativo.append(" d.titulo , ");
            sqlNativo.append("sum(p.id) as quantidade_pessoas, ");
            sqlNativo.append("sum(t.id) as quantidade_tarefas ");
            sqlNativo.append("from departamento d  ");
            sqlNativo.append("join tarefa t on t.id_departamento = d.id ");
            sqlNativo.append("join pessoa p on p.id = t.pessoa_id ");
            sqlNativo.append(" group by 1; ");

            Query query = entityManager.createNativeQuery(sqlNativo.toString());

            List<Object[]> objects = query.getResultList();

            if (!objects.isEmpty()) {
                List<DepartamentoHelper> helpers = new ArrayList<>();

                objects.forEach(obj -> {
                    DepartamentoHelper helper = new DepartamentoHelper();
                    helper.setTitulo(obj[0] != null ? obj[0].toString() : null);
                    helper.setQuantidadePessoas(obj[1] != null ? Integer.parseInt(obj[1].toString()) : null);
                    helper.setQuantidadeTarefas(obj[2] != null ? Integer.parseInt(obj[2].toString()) : null);


                    helpers.add(helper);
                });

                return helpers;
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }

        return null;
    }

}
