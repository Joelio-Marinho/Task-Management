package com.joelio.taskManagement.Controller;


import com.joelio.taskManagement.DTO.PessoaDTO;
import com.joelio.taskManagement.Services.PessoaService;
import com.joelio.taskManagement.exception.BusinessException;
import com.joelio.taskManagement.model.Pessoa;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaService pessoaService;
    private ModelMapper modelMapper;

    public PessoaController(PessoaService pessoaService, ModelMapper modelMapper) {
        this.pessoaService = pessoaService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    public  ResponseEntity<PessoaDTO> create(@RequestBody @Valid PessoaDTO dto) throws BusinessException {
        Pessoa entity = modelMapper.map(dto, Pessoa.class);
        entity = pessoaService.save(entity);
        PessoaDTO PessoaDTO = modelMapper.map(entity,PessoaDTO.class);
        return new ResponseEntity<>(PessoaDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = "Application/json")
    public ResponseEntity<PessoaDTO> update(  @Valid
                                  @PathVariable ("id")  Integer id,
                              @RequestBody PessoaDTO dto) throws BusinessException {
        Pessoa entity = modelMapper.map(dto, Pessoa.class);
        entity = pessoaService.update(id,entity);
        PessoaDTO PessoaDTO = modelMapper.map(entity,PessoaDTO.class);
        return new ResponseEntity<>(PessoaDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Integer id) throws BusinessException {
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
