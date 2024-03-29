package com.joelio.taskManagement.Services;

import com.joelio.taskManagement.Repository.PessoaCustomRepository;
import com.joelio.taskManagement.Repository.PessoaRepository;
import com.joelio.taskManagement.exception.BusinessException;
import com.joelio.taskManagement.helper.PessoaHelper;
import com.joelio.taskManagement.helper.PessoaSimplHelper;
import com.joelio.taskManagement.model.Pessoa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private PessoaRepository repository;

    private DepartamentoService departamentoService;

    private PessoaCustomRepository pessoaCustomRepository;

    @Autowired
    public PessoaService(PessoaRepository repository, DepartamentoService departamentoService, PessoaCustomRepository pessoaCustomRepository) {
        this.repository = repository;
        this.departamentoService = departamentoService;
        this.pessoaCustomRepository = pessoaCustomRepository;
    }

    public Optional<Pessoa> getById(Integer id){
        return this.repository.findById(id);
    }
    public boolean existsById(Integer id){
        if(this.repository.existsById(id)) {
            return true;
        }
        return false;
    }

    @Transactional
    public Pessoa save(Pessoa pessoa) throws BusinessException {
        if (repository.existsByNome(pessoa.getNome())){
            throw new BusinessException("pessoa.exist",new ResponseStatusException(HttpStatus.BAD_REQUEST));
        }
        pessoa.setDepartamento(this.departamentoService.getById(pessoa.getDepartamento().getId()));
        return repository.save(pessoa);
    }

    public void delete(Integer id) throws BusinessException{
        if (!repository.existsById(id)){
            throw new BusinessException("pessoa.invalid", new ResponseStatusException(HttpStatus.PRECONDITION_FAILED));
        }
        Pessoa pessoa = getById(id).get();
        repository.delete(pessoa);

    }
    public Pessoa update(Integer id, Pessoa pessoa) throws BusinessException{
        if (!repository.existsById(id)){
            throw new BusinessException("pessoa.not.exist", new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        Pessoa pUpdate = getById(id).get();
        pUpdate.setNome(pessoa.getNome());
        pUpdate.setDepartamento(pessoa.getDepartamento());
        return repository.save(pUpdate);
    }

    public List<PessoaHelper> retornaNomePessoaDepartamentoTotalHorasGastasPorTarefa(){
        return pessoaCustomRepository.retornaNomePessoaDepartamentoTotalHorasGastasPorTarefa();
    }

    public PessoaSimplHelper findByNomeAndPeriodo(String nomePessoa, LocalDate prazoInicial, LocalDate prazoFinal) throws BusinessException {

        if (!repository.existsByNome(nomePessoa)){
            throw new BusinessException("pessoa.not.exist", new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        return pessoaCustomRepository.retornaNomeEPeriodo(nomePessoa,prazoInicial, prazoFinal);
    }
}
