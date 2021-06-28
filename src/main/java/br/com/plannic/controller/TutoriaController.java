package br.com.plannic.controller;

import br.com.plannic.model.Tutoria;
import br.com.plannic.service.TutoriaService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/tutoria")
public class TutoriaController {

    private TutoriaService tutoriaService;

    @Autowired
    public TutoriaController(TutoriaService tutoriaService) {
        this.tutoriaService = tutoriaService;
    }

    @PostMapping("/cadastro")
    @ApiOperation(value = "Realiza o cadastro de tutorias")
    public ResponseEntity<Tutoria> save(@Valid @RequestBody Tutoria tutoria){
        try {
            MDC.put("name", tutoria.getIdTutoria());
            MDC.put("fluxo", "POST save");
            tutoriaService.save(tutoria);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
    @ApiOperation(value = "Realiza a atualização de tutorias")
    public ResponseEntity update(@RequestBody Tutoria tutoria) {
        try {
            MDC.put("tutoria_id", tutoria.getIdTutoria());
            MDC.put("tutor_id", tutoria.getUsuarioTutor());
            MDC.put("aluno_id", tutoria.getUsuarioAluno());
            MDC.put("fluxo", "PUT update");
            if(tutoriaService.update(tutoria)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Realiza a deleção de tutorias")
    public ResponseEntity delete(@PathVariable("id") int id) {
        try {
            MDC.put("fluxo", "DELETE delete");
            if (tutoriaService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ApiOperation(value = "Realiza a busca de todas as tutorias")
    public ResponseEntity getAll() {
        try{
            MDC.put("fluxo", "GET tutorias");
            return new ResponseEntity<>(tutoriaService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    //Tutor
    @GetMapping("tutor/{id}")
    @ApiOperation(value = "Realiza a busca de tutorias do usuário")
    public ResponseEntity getTutor(@PathVariable("id") int id) {
        try{
            MDC.put("fluxo", "GET tutoria");
            return new ResponseEntity<>(tutoriaService.getTutor(id), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    //Tutor
    @GetMapping("aluno/{id}")
    @ApiOperation(value = "Realiza a busca de tutorias como aluno")
    public ResponseEntity getAluno(@PathVariable("id") int id) {
        try{
            MDC.put("fluxo", "GET aluno");
            return new ResponseEntity<>(tutoriaService.getAluno(id), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
