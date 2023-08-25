package com.joelio.taskManagement.Services;

import com.joelio.taskManagement.Repository.DepartamentoCustomRepository;
import com.joelio.taskManagement.Repository.DepartamentoRepository;
import com.joelio.taskManagement.exception.BusinessException;
import com.joelio.taskManagement.helper.PessoaTarefaDepartamentoHelper;
import com.joelio.taskManagement.model.Departamento;
import com.joelio.taskManagement.model.Pessoa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    @Autowired
    private DepartamentoCustomRepository departamentoCustomRepository;

    public Departamento getById(Integer id)throws BusinessException{
        Optional<Departamento> departamento = this.repository.findById(id);
       return departamento.orElseThrow(()-> new BusinessException("departamento não existe", new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Transactional
    public Departamento save(Departamento departamento) throws BusinessException {
        if (repository.existsByTitulo(departamento.getTitulo())){
            throw new BusinessException("pessoa.exist",new ResponseStatusException(HttpStatus.BAD_REQUEST));
        }
        return repository.save(departamento);
    }

    public List<PessoaTarefaDepartamentoHelper> retornaDepartamentoQPessoasQTarefas(){
        return departamentoCustomRepository.retornaDepartamentoQPessoasQTarefas();
    }
}
