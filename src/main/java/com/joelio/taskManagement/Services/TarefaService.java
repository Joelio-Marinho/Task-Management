package com.joelio.taskManagement.Services;

import com.joelio.taskManagement.DTO.TarefaDTO;
import com.joelio.taskManagement.DTO.TarefaTrasnfDTO;
import com.joelio.taskManagement.Repository.TarefaRepository;
import com.joelio.taskManagement.exception.BusinessException;
import com.joelio.taskManagement.model.Enum.TarefaStaus;
import com.joelio.taskManagement.model.Pessoa;
import com.joelio.taskManagement.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private TarefaRepository repository;

    private PessoaService pessoaService;

    @Autowired
    public TarefaService(TarefaRepository repository, PessoaService pessoaService) {
        this.repository = repository;
        this.pessoaService = pessoaService;
    }
    public TarefaRepository getRepository (){
        return  this.repository;
    }

    public Optional<Tarefa> getById(Integer id){
        return this.repository.findById(id);
    }
    public Tarefa create(Tarefa tarefa){
        Tarefa T = tarefa;
        tarefa.setFinalizado(TarefaStaus.PENDING);
        return repository.save(T);
    }

    public void delete(Integer id) throws BusinessException{
        if (!repository.existsById(id)){
            throw new BusinessException("tarefa.invalid", new ResponseStatusException(HttpStatus.PRECONDITION_FAILED));
        }
        Tarefa tarefa = getById(id).get();
        repository.delete(tarefa);

    }
    public Tarefa alocar(Integer id, Pessoa pessoa) throws BusinessException{
        if (!pessoaService.existsById(pessoa.getId())){
            throw new BusinessException("pessoa.not.exist", new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        if (!repository.existsById(id)){
            throw new BusinessException("tarefa.not.exist", new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        Pessoa pessoaAdd = pessoaService.getById(pessoa.getId()).get();
        Tarefa tarefa = getById(id).get();
        tarefa.setPessoa(pessoaAdd);
        return repository.save(tarefa);
    }

    public Tarefa finalizar(Integer id) throws BusinessException {
        if (!repository.existsById(id)){
            throw new BusinessException("tarefa.not.exist", new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        Tarefa tarefa = getById(id).get();
        tarefa.setFinalizado(TarefaStaus.FINISHED);
        return repository.save(tarefa);
    }
    public TarefaDTO tarefaTransform(TarefaTrasnfDTO dto) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dto.getPrazo(), formato);

        TarefaDTO tarefaDTO = new TarefaDTO(dto.getId(),dto.getTitulo(),dto.getDescricao(),data,
                dto.getDepartamento(),dto.getDuracao(),dto.getPessoa(),dto.getFinalizado());
        return tarefaDTO;
    }

    public List<Tarefa> findByPendente() {
        return repository.findAllByPessoaEmptyAndOrderByPrazoAsc();
    }
}
