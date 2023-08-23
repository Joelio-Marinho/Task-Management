package com.joelio.taskManagement.Controller;

import com.joelio.taskManagement.DTO.DepartamentoDTO;
import com.joelio.taskManagement.DTO.PessoaDTO;
import com.joelio.taskManagement.Services.DepartamentoService;
import com.joelio.taskManagement.Services.PessoaService;
import com.joelio.taskManagement.exception.BusinessException;
import com.joelio.taskManagement.model.Departamento;
import com.joelio.taskManagement.model.Pessoa;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    private DepartamentoService departamentoService;
    private ModelMapper modelMapper;

    public DepartamentoController(DepartamentoService departamentoService, ModelMapper modelMapper) {
        this.departamentoService = departamentoService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartamentoDTO create(@RequestBody @Valid DepartamentoDTO dto) throws BusinessException {
        Departamento entity = modelMapper.map(dto, Departamento.class);

        entity = departamentoService.save(entity);

        return modelMapper.map(entity,DepartamentoDTO.class);
    }
}
