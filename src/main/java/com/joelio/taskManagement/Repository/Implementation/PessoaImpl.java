package com.joelio.taskManagement.Repository.Implementation;

import com.joelio.taskManagement.Repository.PessoaCustomRepository;
import com.joelio.taskManagement.helper.PessoaHelper;
import com.joelio.taskManagement.helper.PessoaSimplHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Repository
@AllArgsConstructor
public class PessoaImpl implements PessoaCustomRepository {

    EntityManager entityManager;

    @Override
    public List<PessoaHelper> retornaNomePessoaDepartamentoTotalHorasGastasPorTarefa() {
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

            Query query = entityManager.createNativeQuery(sqlNativo.toString());

            List<Object[]> objects = query.getResultList();

            if (!objects.isEmpty()) {

                List<PessoaHelper> helpers = new ArrayList<>();
                objects.forEach(obj -> {
                    PessoaHelper helper = new PessoaHelper();
                    helper.setNomePessoa(obj[0] != null ? obj[0].toString() : null);
                    helper.setTitulo(obj[1] != null ? obj[1].toString() : null);
                    helper.setTotalHoras(obj[2] != null ? Integer.parseInt(obj[2].toString()) : null);
                    helpers.add(helper);
                });
                return helpers;
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public PessoaSimplHelper retornaNomeEPeriodo(String nomePessoa,
                                                        LocalDate prazoInicial,
                                                       LocalDate prazoFinal){
        try {
            StringBuilder sqlNativo = new StringBuilder();

            sqlNativo.append("SELECT ");
            sqlNativo.append(" p.nome,");
            sqlNativo.append(" AVG(t.duracao) AS total_horas ");
            sqlNativo.append("FROM pessoa p ");
            sqlNativo.append(" JOIN departamento d ON p.id_departamento = d.id ");
            sqlNativo.append(" JOIN tarefa t ON t.pessoa_id  = p.id ");
            sqlNativo.append("WHERE p.nome =:nomePessoa AND t.prazo BETWEEN :prazoInicial and :prazoFinal ");
            sqlNativo.append("GROUP BY p.id, p.nome, t.prazo ");

            Query query = entityManager.createNativeQuery(sqlNativo.toString());
            query.setParameter("nomePessoa", nomePessoa);
            query.setParameter("prazoInicial", prazoInicial);
            query.setParameter("prazoFinal", prazoFinal);

            List<Object[]> objects = query.getResultList();

            if (!objects.isEmpty()) {
                PessoaSimplHelper helper = new PessoaSimplHelper();

                objects.forEach(obj -> {
                    helper.setNomePessoa(obj[0] != null ? obj[0].toString() : null);
                    helper.setDuracaotarefa(obj[1] != null ? (BigDecimal) obj[1] : null);
                });

                return helper;
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }

        return null;
    }

}
