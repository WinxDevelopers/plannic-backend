package br.com.plannic.controller;

import br.com.plannic.model.Materia;
import br.com.plannic.model.Material;
import br.com.plannic.repository.MateriaRepository;
import br.com.plannic.service.MateriaService;
import br.com.plannic.service.MaterialService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/material")
public class MaterialController {

    private MateriaService materiaService;
    private MaterialService materialService;
    private MateriaRepository materiaRepository;

    @Autowired
    public MaterialController(MateriaService materiaService, MaterialService materialService, MateriaRepository materiaRepository) {
        this.materiaService = materiaService;
        this.materialService = materialService;
        this.materiaRepository = materiaRepository;
    }

    @PostMapping("/cadastro")
    @ApiOperation(value = "Realiza o cadastro de material")
    public ResponseEntity<Material> saveMaterial(@Valid @RequestBody Material material){
        try {
            MDC.put("name", material.getIdMaterial());
            MDC.put("fluxo", "POST save");
            Materia materia = this.materiaRepository.findById(material.getIdMateria());
            material.setIdMateriaBase(materia.getIdMateriaBase());
            materialService.save(material);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Realiza a atualização de materiais")
    public ResponseEntity updateMaterial(@RequestBody Material material) {
        try {
            MDC.put("name", material.getNomeMaterial());
            MDC.put("fluxo", "PUT update");
            Materia materia = this.materiaRepository.findById(material.getIdMateria());
            material.setIdMateriaBase(materia.getIdMateriaBase());
            if(materialService.update(material)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Realiza a deleção de material")
    public ResponseEntity deleteMaterial(@PathVariable("id") int id) {
        try {
//            MDC.put("name", materia.getDescricao());
//            MDC.put("fluxo", "DELETE delete");
            if (materialService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/publico/{id}/{materiaId}")
    @ApiOperation(value = "Realiza a busca de todos os materiais por idMateria")
    public ResponseEntity getAllMateriaisByMateria(@PathVariable("id") int id, @PathVariable("materiaId") int materiaId) {
        try{
            MDC.put("fluxo", "GET material por matéria");
            return new ResponseEntity<>(materialService.getMaterialByMateria(id, materiaId), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("/{id}/{materiaId}")
    @ApiOperation(value = "Realiza a busca de todos os meus materiais por idMateria")
    public ResponseEntity getAllMyMateriaisByMateria(@PathVariable("id") int id, @PathVariable("materiaId") int materiaId) {
        try{
            MDC.put("fluxo", "GET material por matéria");
            return new ResponseEntity<>(materialService.getMyMaterialByMateria(id, materiaId), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Realiza a busca de todos os meus materiais")
    public ResponseEntity getAllMyMateriais(@PathVariable("id") int id) {
        try{
            MDC.put("fluxo", "GET material por matéria");
            return new ResponseEntity<>(materialService.getMyMaterial(id), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("/download/{idMaterial}")
    @ApiOperation(value = "Realiza a busca de material por id")
    public ResponseEntity getMaterialById(@PathVariable("idMaterial") int idMaterial) {
        try{
            MDC.put("fluxo", "GET material por matéria");
            return new ResponseEntity<>(materialService.getMaterialById(idMaterial), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
