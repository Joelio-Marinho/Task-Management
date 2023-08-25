package com.joelio.taskManagement.Repository.Implementation;

import com.joelio.taskManagement.Repository.PessoaCustomRepository;
import com.joelio.taskManagement.helper.PessoaTarefaDepartamentoHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Repository
@AllArgsConstructor
public class PessoaImpl implements PessoaCustomRepository {

    EntityManager entityManager;

    @Override
    public PessoaTarefaDepartamentoHelper retornaNomePessoaDepartamentoTotalHorasGastasPorTarefa() {
        try {
            StringBuilder sqlNativo = new StringBuilder();

            sqlNativo.append("SELECT ");
            sqlNativo.append(" p.nome,");
            sqlNativo.append(" d.titulo AS departamento,");
            sqlNativo.append(" SUM(t.duracao) AS total_horas ");
            sqlNativo.append("FROM pessoa p ");
            sqlNativo.append(" JOIN departamento d ON p.id_departamento = d.id ");
            sqlNativo.append(" JOIN tarefa t ON t.pessoa_id  = p.id ");
            sqlNativo.append("GROUP BY p.id, p.nome, d.titulo ");
            sqlNativo.append("ORDER BY p.nome");

            Query query = entityManager.createNativeQuery(sqlNativo.toString()); // cria chamada para consulta no banco;

            List<Object[]> objects = query.getResultList();

            if (!objects.isEmpty()) {
                PessoaTarefaDepartamentoHelper helper = new PessoaTarefaDepartamentoHelper();
                objects.forEach(obj -> {
                    helper.setNomePessoa(obj[0] != null ? obj[0].toString() : null);
                    helper.setTitulo(obj[1] != null ? obj[1].toString() : null);
                    helper.setTotalHoras(obj[2] != null ? Integer.parseInt(obj[2].toString()) : null);
                });
                return helper;
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }
        return null;
    }

    public List<PessoaTarefaDepartamentoHelper> retornaNomeEPeriodo(LocalDate prazoInicial,
                                                                       LocalDate prazoFinal){
        try {
            StringBuilder sqlNativo = new StringBuilder();

            sqlNativo.append("SELECT ");
            sqlNativo.append(" p.nome,");
            sqlNativo.append(" t.prazo AS prazo,");
            sqlNativo.append(" AVG(t.duracao) AS total_horas ");
            sqlNativo.append("FROM pessoa p ");
            sqlNativo.append(" JOIN departamento d ON p.id_departamento = d.id ");
            sqlNativo.append(" JOIN tarefa t ON t.pessoa_id  = p.id ");
            sqlNativo.append("WHERE t.prazo BETWEEN :prazoInicial and :prazoFinal ");
            sqlNativo.append("GROUP BY p.id, p.nome, t.prazo ");
            sqlNativo.append("ORDER BY p.nome");

            Query query = entityManager.createNativeQuery(sqlNativo.toString()); // cria chamada para consulta no banco;
            query.setParameter("prazoInicial", prazoInicial);
            query.setParameter("prazoFinal", prazoFinal);

            List<Object[]> objects = query.getResultList();

            if (!objects.isEmpty()) {
                List<PessoaTarefaDepartamentoHelper> helpers = new ArrayList<>();

                objects.forEach(obj -> {
                    PessoaTarefaDepartamentoHelper helper = new PessoaTarefaDepartamentoHelper();
                    helper.setNomePessoa(obj[0] != null ? obj[0].toString() : null);
                    helper.setPrazo(obj[1] != null ? LocalDate.parse(obj[1].toString()) : null);
                    helper.setDuracaotarefa(obj[2] != null ? (BigDecimal) obj[2] : null);

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
