package br.com.plannic.controller;

import br.com.plannic.model.NotasTutor;
import br.com.plannic.model.Usuario;
import br.com.plannic.service.NotasTutorService;
import org.apache.log4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notasTutor")
public class NotasTutorController {

    private NotasTutorService notasTutorService;

    @PostMapping
    public ResponseEntity<NotasTutor> save(@Valid @RequestBody NotasTutor notasTutor){
        try {
            MDC.put("Id user", notasTutor.getIdUsuario());
            MDC.put("fluxo", "POST save");
            notasTutorService.save(notasTutor);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity update(@RequestBody NotasTutor notasTutor) {
        try {
            MDC.put("user_id", notasTutor.getIdUsuario());
            MDC.put("nota", notasTutor.getIdNotaUsuario());
            MDC.put("fluxo", "PUT update");
            if(notasTutorService.update(notasTutor)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody NotasTutor notasTutor) {
        try {
            MDC.put("user_id", notasTutor.getIdUsuarioTutor());
            MDC.put("nota", notasTutor.getNotaTutor());
            MDC.put("fluxo", "DELETE delete");
            if (notasTutorService.delete(notasTutor)) {
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
            MDC.put("fluxo", "GET notas tutor");
            return new ResponseEntity<>(notasTutorService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
