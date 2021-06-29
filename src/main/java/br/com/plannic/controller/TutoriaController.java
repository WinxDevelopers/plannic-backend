package br.com.plannic.controller;

import br.com.plannic.model.Aluno;
import br.com.plannic.model.Tutor;
import br.com.plannic.model.Tutoria;
import br.com.plannic.service.AlunoService;
import br.com.plannic.service.TutorService;
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
    private AlunoService alunoService;
    private TutorService tutorService;

    @Autowired
    public TutoriaController(TutoriaService tutoriaService, AlunoService alunoService, TutorService tutorService) {
        this.tutoriaService = tutoriaService;
        this.alunoService = alunoService;
        this.tutorService = tutorService;
    }

    @PostMapping("/aluno")
    @ApiOperation(value = "Realiza o cadastro de alunos")
    public ResponseEntity<Aluno> save(@Valid @RequestBody Aluno aluno){
        try {
            MDC.put("name", aluno.getIdAluno());
            MDC.put("fluxo", "POST save");
            alunoService.save(aluno);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/aluno")
    @ApiOperation(value = "Realiza a busca de todos os alunos")
    public ResponseEntity getAlunos() {
        try{
            MDC.put("fluxo", "GET alunos");
            return new ResponseEntity<>(alunoService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @DeleteMapping("/aluno/{id}")
    @ApiOperation(value = "Realiza a deleção de alunos")
    public ResponseEntity deleteAluno(@PathVariable("id") int id) {
        try {
            MDC.put("fluxo", "DELETE aluno");
            if (alunoService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/aluno/{id}/materia/{idMateria}")
    @ApiOperation(value = "Realiza a busca de todos os alunos por matéria")
    public ResponseEntity getAlunosByMateria(@PathVariable("id") int id, @PathVariable("idMateria") int idMateria) {
        try {
            MDC.put("fluxo", "GET alunos por matéria");
            return new ResponseEntity<>(alunoService.getByMateria(id, idMateria), HttpStatus.OK);
        } finally {
            MDC.clear();
        }
    }

    @PostMapping("/tutor")
    @ApiOperation(value = "Realiza o cadastro de tutores")
    public ResponseEntity<Tutor> save(@Valid @RequestBody Tutor tutor){
        try {
            MDC.put("name", tutor.getIdTutor());
            MDC.put("fluxo", "POST save");
            tutorService.save(tutor);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/tutor")
    @ApiOperation(value = "Realiza a busca de todos os tutores")
    public ResponseEntity getTutores() {
        try{
            MDC.put("fluxo", "GET tutores");
            return new ResponseEntity<>(tutorService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @DeleteMapping("/tutor/{id}")
    @ApiOperation(value = "Realiza a deleção de tutores")
    public ResponseEntity deleteTutor(@PathVariable("id") int id) {
        try {
            MDC.put("fluxo", "DELETE aluno");
            if (tutorService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutor/{id}/materia/{idMateria}")
    @ApiOperation(value = "Realiza a busca de todos os tutores")
    public ResponseEntity getTutoresByMateria(@PathVariable("id") int id,  @PathVariable("idMateria") int idMateria){
        try {
            MDC.put("fluxo", "GET tutores por matéria");
            return new ResponseEntity<>(tutorService.getByMateria(id, idMateria), HttpStatus.OK);
        } finally {
            MDC.clear();
        }
    }

    @PostMapping("/cadastro")
    @ApiOperation(value = "Realiza o cadastro de tutorias")
    public ResponseEntity<Tutoria> save(@Valid @RequestBody Tutoria tutoria){
        try {
            MDC.put("name", tutoria.getIdTutoria());
            MDC.put("fluxo", "POST save");
            tutoriaService.save(tutoria);
            alunoService.deleteAfterTutoria(tutoria.getIdUsuarioAluno(), tutoria.getIdMateriaBase());
            tutorService.deleteAfterTutoria(tutoria.getIdUsuarioTutor(), tutoria.getIdMateriaBase());
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/cadastro")
    @ApiOperation(value = "Realiza a busca de todas as tutorias")
    public ResponseEntity getTutorias() {
        try{
            MDC.put("fluxo", "GET tutorias");
            return new ResponseEntity<>(tutoriaService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @DeleteMapping("/cadastro/{id}")
    @ApiOperation(value = "Realiza a deleção de tutorias")
    public ResponseEntity deleteTutoria(@PathVariable("id") int id) {
        try {
            MDC.put("fluxo", "DELETE aluno");
            if (tutoriaService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cadastro/aluno/{id}")
    @ApiOperation(value = "Realiza a busca de todas as tutorias em que o usuário é aluno")
    public ResponseEntity getTutoriasByAluno(@PathVariable("id") int id) {
        try{
            MDC.put("fluxo", "GET tutorias aluno");
            return new ResponseEntity<>(tutoriaService.getAluno(id), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("/cadastro/tutor/{id}")
    @ApiOperation(value = "Realiza a busca de todas as tutorias em que o usuário é tutor")
    public ResponseEntity getTutoriasByTutor(@PathVariable("id") int id) {
        try{
            MDC.put("fluxo", "GET tutorias aluno");
            return new ResponseEntity<>(tutoriaService.getTutor(id), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

}
