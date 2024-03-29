package com.joelio.taskManagement.Controller;


import com.joelio.taskManagement.DTO.PessoaDTO;
import com.joelio.taskManagement.Services.PessoaService;
import com.joelio.taskManagement.exception.BusinessException;
import com.joelio.taskManagement.helper.PessoaHelper;
import com.joelio.taskManagement.helper.PessoaSimplHelper;
import com.joelio.taskManagement.model.Pessoa;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody @Valid PessoaDTO dto) throws BusinessException {
        Pessoa entity = modelMapper.map(dto, Pessoa.class);
        entity = pessoaService.save(entity);
        PessoaDTO PessoaDTO = modelMapper.map(entity, PessoaDTO.class);
        return new ResponseEntity<>(PessoaDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = "Application/json")
    public ResponseEntity<PessoaDTO> update(@Valid
                                            @PathVariable("id") Integer id,
                                            @RequestBody PessoaDTO dto) throws BusinessException {
        Pessoa entity = modelMapper.map(dto, Pessoa.class);
        entity = pessoaService.update(id, entity);
        PessoaDTO PessoaDTO = modelMapper.map(entity, PessoaDTO.class);
        return new ResponseEntity<>(PessoaDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Integer id) throws BusinessException {
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<PessoaHelper>> findAllPessoaAndDepartamentoAndTotalHoras() {
        List<PessoaHelper> helper = pessoaService.retornaNomePessoaDepartamentoTotalHorasGastasPorTarefa();
        return new ResponseEntity<>(helper, HttpStatus.OK);
    }

    @GetMapping(value = "/gastos", produces = "Application/json")
    public ResponseEntity<PessoaSimplHelper> buscaNomeEPeriodo(@RequestParam String nomePessoa,
                                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate prazoInicial,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate prazoFinal) throws BusinessException { //ALTERAR NOME DEPOIS
        PessoaSimplHelper helpers = pessoaService.findByNomeAndPeriodo(nomePessoa ,prazoInicial, prazoFinal);
        return new ResponseEntity<>(helpers, HttpStatus.OK);
    }

}
