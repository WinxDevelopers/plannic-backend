package br.com.plannic.controller;

import br.com.plannic.model.Materia;
import br.com.plannic.service.MateriaService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private MateriaService materiaService;

    @Autowired
    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping("/cadastro")
    @ApiOperation(value = "Realiza o cadastro de materias")
    public ResponseEntity<Materia> save(@Valid @RequestBody Materia materia){
        try {
            MDC.put("name", materia.getNomeMateria());
            MDC.put("fluxo", "POST save");
            materiaService.save(materia);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Realiza a atualização de materias")
    public ResponseEntity update(@RequestBody Materia materia) {
        try {
            MDC.put("name", materia.getDescricao());
            MDC.put("fluxo", "PUT update");
            if(materiaService.update(materia)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Realiza a deleção de materia")
    public ResponseEntity delete(@PathVariable("id") int id) {
        try {
//            MDC.put("name", materia.getDescricao());
//            MDC.put("fluxo", "DELETE delete");
            if (materiaService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ApiOperation(value = "Realiza a busca de todas as materias")
    public ResponseEntity getAll() {
        try{
            MDC.put("fluxo", "GET materia");
            return new ResponseEntity<>(materiaService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("/base")
    @ApiOperation(value = "Realiza a busca de todas as materias base")
    public ResponseEntity getAllBase() {
        try{
            MDC.put("fluxo", "GET materia");
            return new ResponseEntity<>(materiaService.getAllBase(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
