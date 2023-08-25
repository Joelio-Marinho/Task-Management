package com.joelio.taskManagement.Controller;

import com.joelio.taskManagement.DTO.PessoaDTO;
import com.joelio.taskManagement.DTO.TarefaDTO;
import com.joelio.taskManagement.DTO.TarefaTrasnfDTO;
import com.joelio.taskManagement.Services.TarefaService;
import com.joelio.taskManagement.exception.BusinessException;
import com.joelio.taskManagement.helper.TarefaHelper;
import com.joelio.taskManagement.model.Pessoa;
import com.joelio.taskManagement.model.Tarefa;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private TarefaService tarefaService;
    private ModelMapper modelMapper;

    public TarefaController(TarefaService tarefaService, ModelMapper modelMapper) {
        this.tarefaService = tarefaService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    public ResponseEntity<TarefaDTO> create(@RequestBody @Valid TarefaTrasnfDTO dto) throws ParseException {
        Tarefa entity = modelMapper.map(tarefaService.tarefaTransform(dto), Tarefa.class);
        entity = tarefaService.create(entity);
        TarefaDTO tarefaDTO = modelMapper.map(entity,TarefaDTO.class);
        return new ResponseEntity<>(tarefaDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/alocar/{id}", produces = "Application/json")
    public ResponseEntity<TarefaDTO> update(  @Valid
                                              @PathVariable ("id")  Integer id,
                                              @RequestBody PessoaDTO dto) throws BusinessException {
        Pessoa entity = modelMapper.map(dto, Pessoa.class);
        Tarefa tarefa = tarefaService.alocar(id,entity);
        TarefaDTO tarefaDTO = modelMapper.map(tarefa,TarefaDTO.class);
        return new ResponseEntity<>(tarefaDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/finalizar/{id}", produces = "Application/json")
    public ResponseEntity<TarefaDTO> finalizar(  @Valid
                                              @PathVariable ("id")  Integer id) throws BusinessException {
        Tarefa tarefa = tarefaService.finalizar(id);
        TarefaDTO tarefaDTO = modelMapper.map(tarefa,TarefaDTO.class);
        return new ResponseEntity<>(tarefaDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Integer id) throws BusinessException {
        tarefaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/pendentes", produces = "Application/json")
    public ResponseEntity<List<TarefaHelper>> retornaTresTarefasMaisAntigasSemAlocacao()  {
        List<TarefaHelper> helpers = this.tarefaService.retornaTresTarefasMaisAntigasSemAlocacao();
        return new ResponseEntity<>(helpers, HttpStatus.OK);
    }
}
