package com.joelio.taskManagement.Controller;

import com.joelio.taskManagement.DTO.DepartamentoDTO;
import com.joelio.taskManagement.Services.DepartamentoService;
import com.joelio.taskManagement.exception.BusinessException;
import com.joelio.taskManagement.helper.DepartamentoHelper;
import com.joelio.taskManagement.model.Departamento;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private DepartamentoService departamentoService;
    private ModelMapper modelMapper;

    public DepartamentoController(DepartamentoService departamentoService, ModelMapper modelMapper) {
        this.departamentoService = departamentoService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity< DepartamentoDTO> create(@RequestBody @Valid DepartamentoDTO dto) throws BusinessException {
        Departamento entity = modelMapper.map(dto, Departamento.class);
        entity = departamentoService.save(entity);
        DepartamentoDTO departamentoDTO =  modelMapper.map(entity,DepartamentoDTO.class);
        return new ResponseEntity<>(departamentoDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoHelper>> getQPessoasQTarefasByDepartamento() {
        List<DepartamentoHelper> helper = departamentoService.getQPessoasQTarefasByDepartamento();
        return new ResponseEntity<>(helper, HttpStatus.OK);
    }
}
