package br.com.plannic.controller;


import br.com.plannic.model.NotasMateria;
import br.com.plannic.model.Usuario;
import br.com.plannic.service.MateriaService;
import br.com.plannic.service.NotasMateriaService;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notasMateria")
public class NotasMateriaController {

    private NotasMateriaService notasMateriaService;

    @Autowired
    public NotasMateriaController(NotasMateriaService notasMateriaService) {
        this.notasMateriaService = notasMateriaService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<NotasMateria> save(@Valid @RequestBody NotasMateria notasMateria){
        try {
            MDC.put("nota", notasMateria.getNotaMateria());
            MDC.put("fluxo", "POST save");
            notasMateriaService.save(notasMateria);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity update(@RequestBody NotasMateria notasMateria) {
        try {
            MDC.put("user_id", notasMateria.getIdUsuario());
            MDC.put("nota", notasMateria.getNotaMateria());
            MDC.put("fluxo", "PUT update");
            if(notasMateriaService.update(notasMateria)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        try {
//            MDC.put("user_id", notasMateria.getIdUsuario());
//            MDC.put("nota", notasMateria.getNotaMateria());
            MDC.put("fluxo", "DELETE delete");
            if (notasMateriaService.delete(id)) {
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
            MDC.put("fluxo", "GET notas materias");
            return new ResponseEntity<>(notasMateriaService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

}
