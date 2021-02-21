package br.com.plannic.controller;

import br.com.plannic.model.Materia;
import br.com.plannic.model.Usuario;
import br.com.plannic.service.MateriaService;
import br.com.plannic.service.UsuarioService;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class MateriaController {

    private MateriaService materiaService;

    @Autowired
    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping("/cadastroMateria")
    public ResponseEntity<Materia> save(@Valid @RequestBody Materia materia){
        try {
            MDC.put("name", materia.getMateria());
            MDC.put("fluxo", "POST save");
            materiaService.save(materia);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
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

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Materia materia) {
        try {
            MDC.put("name", materia.getDescricao());
            MDC.put("fluxo", "DELETE delete");
            if (materiaService.delete(materia)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity getAll() {
        try{
            MDC.put("fluxo", "GET materia");
            return new ResponseEntity<>(materiaService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
