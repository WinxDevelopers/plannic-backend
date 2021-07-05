package br.com.plannic.controller;

import br.com.plannic.model.Materia;
import br.com.plannic.model.MateriaBase;
import br.com.plannic.model.SugestoesMateria;
import br.com.plannic.service.MateriaService;
import br.com.plannic.service.MaterialService;
import br.com.plannic.repository.MateriaRepository;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private MateriaService materiaService;
    private MaterialService materialService;
    private final MateriaRepository repository;

    @Autowired
    public MateriaController(MateriaService materiaService, MaterialService materialService, MateriaRepository repository) {
        this.materiaService = materiaService;
        this.materialService = materialService;
        this.repository = repository;
    }

    @PostMapping("/cadastro")
    @ApiOperation(value = "Realiza o cadastro de materias")
    public ResponseEntity<?> save(@Valid @RequestBody Materia materia){
        try {
            MDC.put("name", materia.getNomeMateria());
            MDC.put("fluxo", "POST save");
            materiaService.save(materia);
        }finally{
            MDC.clear();
        }
        HashMap jsonMessage= new HashMap();
        jsonMessage.put("idMateria", materia.getIdMateria());
        return new ResponseEntity<>(jsonMessage, HttpStatus.CREATED);
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

    // Matéria Base
    @PostMapping("/cadastro/base")
    @ApiOperation(value = "Realiza o cadastro de materias base")
    public ResponseEntity<?> saveBase(@Valid @RequestBody MateriaBase materiaBase){
        try {
            MDC.put("name", materiaBase.getMateriaBase());
            MDC.put("fluxo", "POST save");
            materiaService.saveMateriaBase(materiaBase);
        }finally{
            MDC.clear();
        }
        HashMap jsonMessage= new HashMap();
        Materia materia = this.repository.findByIdMateriaBase(materiaBase.getIdMateriaBase());
        jsonMessage.put("idMateria", materia.getIdMateria());
        return new ResponseEntity<>(jsonMessage, HttpStatus.CREATED);
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

    // Sugestões
    @PostMapping("/cadastro/sugestoes")
    @ApiOperation(value = "Realiza a sugestao de uma nova materias")
    public ResponseEntity<?> save(@Valid @RequestBody SugestoesMateria sugestoesmateria){
        try {
            MDC.put("name", sugestoesmateria.getNomeMateria());
            MDC.put("fluxo", "POST save");
            sugestoesmateria.setFaltaVotar("1_2_3_10_11");
            materiaService.saveSugestaoMateria(sugestoesmateria);
        }finally{
            MDC.clear();
        }
        HashMap jsonMessage= new HashMap();
        Materia materia = this.repository.findByIdSugestao(sugestoesmateria.getIdSugestoesMateria());
        jsonMessage.put("idMateria", materia.getIdMateria());
        return new ResponseEntity<>(jsonMessage, HttpStatus.CREATED);
    }

    @PutMapping("/sugestoes")
    @ApiOperation(value = "Realiza a atualização da Sugestão")
    public ResponseEntity updateSugestaoMateria(@RequestBody SugestoesMateria sugestoesmateria) {
        try {
            MDC.put("name", sugestoesmateria.getVotos());
            MDC.put("fluxo", "PUT update");
            if(materiaService.updateSugestaoMateria(sugestoesmateria)) {
                if (sugestoesmateria.getTotalVotos() < 5) {
                    if(sugestoesmateria.getVotos() > 2) {
                        MateriaBase novaMateria = new MateriaBase(sugestoesmateria.getNomeMateria());
                        materiaService.saveMateriaBase(novaMateria);
                        materiaService.atualizarMateriaAceita(sugestoesmateria, novaMateria);
                        materiaService.deleteSugestaoMateria(sugestoesmateria.getIdSugestoesMateria());
                    } else {
                        materiaService.updateSugestaoMateria(sugestoesmateria);
                    }
                } else {
                    if (sugestoesmateria.getTotalVotos() == 5) {
                        if(sugestoesmateria.getVotos() > 2) {
                            MateriaBase novaMateria = new MateriaBase(sugestoesmateria.getNomeMateria());
                            materiaService.saveMateriaBase(novaMateria);
                            materiaService.atualizarMateriaAceita(sugestoesmateria, novaMateria);
                            materiaService.deleteSugestaoMateria(sugestoesmateria.getIdSugestoesMateria());
                        } else {
                            materiaService.deleteSugestaoMateria(sugestoesmateria.getIdSugestoesMateria());
                        }
                    }
                }
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sugestoes")
    @ApiOperation(value = "Realiza a busca de todas as sugestões de materias")
    public ResponseEntity getAllSugestoes() {
        try{
            MDC.put("fluxo", "GET materia");
            return new ResponseEntity<>(materiaService.getAllSugestoes(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
