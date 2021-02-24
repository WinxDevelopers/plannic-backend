package br.com.plannic.controller;

import br.com.plannic.model.NotasMateria;
import br.com.plannic.model.Usuario;
import br.com.plannic.service.NotasMateriaService;
import org.apache.log4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notasMateria")
public class NotasMateriaController {


    private NotasMateriaService notasMateriaService;

    @PostMapping
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

    @DeleteMapping
    public ResponseEntity delete(@RequestBody NotasMateria notasMateria) {
        try {
            MDC.put("user_id", notasMateria.getIdUsuario());
            MDC.put("nota", notasMateria.getNotaMateria());
            MDC.put("fluxo", "DELETE delete");
            if (notasMateriaService.delete(notasMateria)) {
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
