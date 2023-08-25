package com.joelio.taskManagement.Repository.Implementation;

import com.joelio.taskManagement.Repository.TarefaCustomRepository;
import com.joelio.taskManagement.helper.TarefaHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class TarefaImpl implements TarefaCustomRepository {

    EntityManager entityManager;

    public List<TarefaHelper> retornaTresTarefasMaisAntigasSemAlocacao(){
        try {
            StringBuilder sqlNativo = new StringBuilder();

            sqlNativo.append("SELECT * ");
            sqlNativo.append("FROM tarefa t ");
            sqlNativo.append("WHERE t.pessoa_id is null ");
            sqlNativo.append("ORDER BY t.prazo desc, t.id asc limit 3");

            Query query = entityManager.createNativeQuery(sqlNativo.toString());

            List<Object[]> objects = query.getResultList();

            if (!objects.isEmpty()) {
                List<TarefaHelper> helpers = new ArrayList<>();

                objects.forEach(obj -> {
                    TarefaHelper helper = new TarefaHelper();
                    helper.setIdTarefa(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
                    helper.setDescricaoTarefa(obj[1] != null ? obj[1].toString() : null);
                    helper.setDuracaotarefa(obj[2] != null ? new BigDecimal(obj[2].toString()) : null);
                    helper.setFinalizado(obj[3] != null ? Integer.parseInt(obj[3].toString()) : null);
                    helper.setPrazo(obj[4] != null ? LocalDate.parse(obj[4].toString()) : null);
                    helper.setTitulo(obj[5] != null ? obj[5].toString() : null);

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
